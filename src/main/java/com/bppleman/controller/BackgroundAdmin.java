package com.bppleman.controller;

import com.bppleman.entity.Admin;
import com.bppleman.entity.User;
import com.bppleman.tool.CookieTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/background/admin")
public class BackgroundAdmin {
    private String prefix = "/background/admin";
    private String login = "/login";
    private String adminLogin = "/admin_login";

    @RequestMapping("")
    public String toAdminLogin() {
        return "redirect:" + prefix + login;
    }

    @RequestMapping("/login")
    public String adminLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.removeAttribute("user");
            CookieTool.removeCookie(request, response);
        }
        return prefix + adminLogin;
    }

    @RequestMapping("/login/submit")
    public String adminLoginSubmit(Admin admin) {
        return "";
    }
}
