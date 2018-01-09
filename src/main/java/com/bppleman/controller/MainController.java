package com.bppleman.controller;

import com.bppleman.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by BppleMan on 17/6/15.
 */
@Controller
@RequestMapping("/")
public class MainController {
    private String prefix = "/";
    private String home = "home";
    private String status = "status";
    private String teacher_contests = "teacher_contests";
    private String diy_contests = "diy_contests";
    private String exams = "exams";
    private String register = "register";


    @Resource
    private UserService userService = null;

    @RequestMapping("")
    public String home(HttpServletRequest request, HttpSession session) {
        return prefix + home;
    }

    @RequestMapping("home")
    public String backHome(HttpServletRequest request, HttpSession session) {
        System.out.println("MainController.backHome");
        return prefix + home;
    }

//    @RequestMapping("status")
//    public String status(HttpServletRequest request, HttpSession session) {
//        CookieTool.checkCookie(userService, request, session);
//        return prefix + status;
//    }

    @RequestMapping("teacher_contests")
    public String teacher_contests(HttpServletRequest request, HttpSession session) {
        return prefix + teacher_contests;
    }

    @RequestMapping("diy_contests")
    public String diy_contests(HttpServletRequest request, HttpSession session) {
        return prefix + diy_contests;
    }

    @RequestMapping("exams")
    public String exams(HttpServletRequest request, HttpSession session) {
        return prefix + exams;
    }

//    @RequestMapping("login")
//    public String login(User user, boolean rememberMe, HttpServletRequest request) {
//        System.out.println(user);
//        System.out.println(rememberMe);
//        System.out.println(request.getServletPath());
//        return "sth";
//    }

    @RequestMapping("upload")
    public String upload(MultipartFile uploadFile, HttpSession session, HttpServletRequest request) {
        if (uploadFile.getSize() > 0) {
            String username = (String) session.getAttribute("username");
        }
        return "redirect:"+status;
    }
}
