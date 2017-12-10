package com.bppleman.controller;

import com.bppleman.entity.User;
import com.bppleman.service.UserService;
import com.bppleman.tool.TokenTool;
import com.bppleman.tool.URLTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        Cookie cookie = new Cookie("username", null);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
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
}
