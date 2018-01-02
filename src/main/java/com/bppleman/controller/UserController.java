package com.bppleman.controller;

import com.bppleman.entity.*;
import com.bppleman.enumration.UserChangeInfo;
import com.bppleman.enumration.UserChangeType;
import com.bppleman.service.CountService;
import com.bppleman.service.ProblemUserSolveService;
import com.bppleman.service.StatusService;
import com.bppleman.service.UserService;
import com.bppleman.tool.CookieTool;
import com.bppleman.tool.TokenTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private String prefix = "/user";
    private String register = "/register";
    private String login_success = "/login_success";
    private String register_success = "/register_success";
    private String register_failed = "/user/register";

    public static final String LOGIN_SUCCESS = "login_success";
    public static final String LOGIN_USERNAME_UN_EXIST = "login_username_un_exist";
    public static final String LOGIN_PASSWORD_ERROR = "login_password_error";

    public static final String REGISTER_SUCCESS = "register_success";
    public static final String REGISTER_ERROR = "register_error";
    public static final String REGISTER_EXIST = "register_exist";

    @Resource
    private UserService userService = null;

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user, boolean remember, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        StringBuffer loginInfo = new StringBuffer();
        User loginUser = userService.loginUser(user, loginInfo);
        if (loginUser != null) {
            if (remember == true) {
                Cookie cookie = new Cookie("username", user.getUsername());
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
            session.setAttribute("user", loginUser);
        }
        return loginInfo.toString();
    }

    @RequestMapping("/login_success")
    public String loginSuccess() {
        return prefix + login_success;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        CookieTool.removeCookie(request, response);
        session.removeAttribute("user");
        return "logout_success";
    }

    @RequestMapping("/register")
    public String register(HttpSession session) {
        String register_token = TokenTool.getInstance().makeToken();
        session.setAttribute("register_token", register_token);
        return prefix + register;
    }

    @RequestMapping("/register_submit")
    @ResponseBody
    public String registerSubmit(User user, boolean needLogin, String token, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String register_token = (String) session.getAttribute("register_token");
        if (register_token != null && register_token.equals(token)) {
            user.setType(User.Normal);
            String result = userService.registerUser(user);
            if (result.equals(REGISTER_SUCCESS)) {
                if (needLogin) {
                    login(user, needLogin, request, session, response);
                }
//                注册成功则跳转到首页
//                request.setAttribute("jumpPath", );
                return REGISTER_SUCCESS;
            } else if (result.equals(REGISTER_EXIST)) {
                return REGISTER_EXIST;
            }
        }
        return REGISTER_ERROR;
    }

    @RequestMapping("/register_success")
    public String register_success() {
        return prefix + register_success;
    }

    @RequestMapping("/register_failed")
    public String register_failed() {
        return prefix + register_failed;
    }


    /******************************************
     * 账号信息
     ******************************************/

    private String infoPrefix = "/user/information";
    private String mainInfo = "/main_info";

    @RequestMapping("/information/main_info")
    public String mainInfo(HttpServletRequest request, HttpSession session) {
        request.setAttribute("lastPath", "main_info");
        return infoPrefix + mainInfo;
    }

    @RequestMapping("/information/change_info")
    @ResponseBody
    public String changInfo(@RequestParam(value = "tp", required = false) String type,
                            @RequestParam(value = "val", required = false) String value,
                            @RequestParam(value = "oldP", required = false) String oldPassword,
                            HttpSession session) {
        String result = UserChangeInfo.SUCCESS.getValue();
        User user = (User) session.getAttribute("user");
        UserChangeType userChangeType = UserChangeType.valueOf(type);
        switch (userChangeType) {
            case EMAIL: {
                user.setEmail(value);
                break;
            }
            case NICKNAME: {
                user.setNickname(value);
                break;
            }
            case PASSWORD: {
                if (oldPassword.equals(user.getPassword())) {
                    user.setPassword(value);
                } else {
                    result = UserChangeInfo.PASSWORD_ERROR.getValue();
                }
                break;
            }
            case TELEPHONE: {
                user.setTelephone(value);
                break;
            }
        }
        if (result.equals(UserChangeInfo.SUCCESS.getValue()) &&
                userService.updateUser(user)) {
            result = UserChangeInfo.SUCCESS.getValue();
        } else {
            result = UserChangeInfo.ERROR.getValue();
        }
        return result;
    }

    @RequestMapping("/information/verification_code")
    @ResponseBody
    public String getVerificationCode(@RequestParam(value = "tp", required = false) String type) {
        String success = "success";
        String result = success;
        UserChangeType userChangeType = UserChangeType.valueOf(type);
        switch (userChangeType) {
            case EMAIL: {
                result = success;
            }
            case TELEPHONE: {
//                如果修改的类型是邮箱，那么需要发送邮箱验证码
            }
        }
        return result;
    }

    /******************************************
     * 答题信息
     ******************************************/

    @Resource
    private ProblemUserSolveService problemUserSolveService;

    @Resource
    private CountService countService;

    @Resource
    private StatusService statusService;

    private static final String userSolveInfo = "/user_solve_info";

    @RequestMapping("/information/user_solve_info")
    public String userSolveInfo(@RequestParam(value = "page", required = false) Integer pageNumber,
                                @RequestParam(value = "tp", required = false) String type,
                                @RequestParam(value = "rt") Integer requestTime,
                                HttpServletRequest request, HttpSession session) {
//        两个table的当前页码
        Map<String, Integer> userSolveCurrentPageMap;
        if (requestTime == 1) {
//            如果是第一次访问该页面，那么当前页都是1
            session.setAttribute("requestTime", ++requestTime);
            userSolveCurrentPageMap = new HashMap<>();
            userSolveCurrentPageMap.put(UserSolve.SOLVED, 1);
            userSolveCurrentPageMap.put(UserSolve.UN_SOLVED, 1);
            session.setAttribute("userSolveCurrentPageMap", userSolveCurrentPageMap);
        } else {
//            如果不是第一次，那么应该从session中取出map
            session.setAttribute("requestTime", ++requestTime);
            userSolveCurrentPageMap = (Map<String, Integer>) session.getAttribute("userSolveCurrentPageMap");
//            根据type设置当前页
            userSolveCurrentPageMap.put(type, pageNumber);
        }
        User user = (User) session.getAttribute("user");
        Integer counterPerPage = 5;
        Map<String, List<Problem>> userSolveProblemsMap = problemUserSolveService.getUserSolveProblemsMap(userSolveCurrentPageMap, counterPerPage, user.getId());
        Map<String, Integer> userSolveTotalPageMap = problemUserSolveService.getUserSolveTotalPageMap(user.getId(), (double) counterPerPage);
        Map<Integer, Integer> problemSubmittedTimesMap = new HashMap<>();
        List<Integer> userSubmittedProblemIds = problemUserSolveService.getUserSubmittedProblemIds(user.getId());
        for (Integer id : userSubmittedProblemIds) {
            problemSubmittedTimesMap.put(id, statusService.getProblemXXTimesByUserId(new IDParam(user.getId(), id, -1, -1), null));
        }
        Map<Integer, Integer> problemACTimesMap = new HashMap<>();
        for (Integer id : userSubmittedProblemIds) {
            Integer times = statusService.getProblemXXTimesByUserId(new IDParam(user.getId(), id, -1, -1), Status.Accepted);
            problemACTimesMap.put(id, times);
        }
        request.setAttribute("countPerPage", counterPerPage);
        request.setAttribute("userSolveProblemsMap", userSolveProblemsMap);
        request.setAttribute("userSolveTotalPageMap", userSolveTotalPageMap);
        request.setAttribute("problemSubmittedTimesMap", problemSubmittedTimesMap);
        request.setAttribute("problemACTimesMap", problemACTimesMap);
        return infoPrefix + userSolveInfo;
    }

    /******************************************
     * 竞赛信息
     ******************************************/

    @RequestMapping("/information/contest_info")
    public String contestInfo(HttpServletRequest request) {

        request.setAttribute("lastPath", "contest_info");
        return infoPrefix + mainInfo;
    }


    /******************************************
     * 考试信息
     ******************************************/

    @RequestMapping("/information/exam_info")
    public String examInfo(HttpServletRequest request) {

        request.setAttribute("lastPath", "exam_info");
        return infoPrefix + mainInfo;
    }
}
