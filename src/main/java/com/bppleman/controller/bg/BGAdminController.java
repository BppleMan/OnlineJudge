package com.bppleman.controller.bg;

import com.bppleman.entity.Admin;
import com.bppleman.entity.User;
import com.bppleman.service.AdminService;
import com.bppleman.tool.CookieTool;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/bg/admin")
public class BGAdminController {
    private String prefix = "/bg/admin";
    private String login = "/login";

    @Resource
    private AdminService adminService;

    @RequestMapping("")
    public String toAdminLogin(HttpServletRequest request) {
        return "redirect:" + prefix + login;
    }

    @RequestMapping("/login")
    public String adminLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.removeAttribute("user");
            CookieTool.removeCookie("username", request, response);
        }
        List<Admin> admins = adminService.getAllAdmins();
        request.setAttribute("admins", admins);
        return prefix + login;
    }

    @RequestMapping("/login/submit")
    public String adminLoginSubmit(Admin admin, @RequestParam(required = false) String remember,
                                   HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if (adminService.loginAdmin(admin)) {
            if (remember.equals("remember")) {
                CookieTool.addCookid("adminName", admin.getAdminName(), request, response);
                session.setAttribute("admin", admin);
            }
            String wannaToURI = (String) session.getAttribute("wannaToURI");
            String []uris = wannaToURI.split("/");
            String uri = "";
            for (int i = 0; i < uris.length; i++) {
                if (i != 0 && i != 1)
                    uri += "/" + uris[i];
            }
            return "redirect:" + uri;
        }
        else return "redirect:" + prefix + login;
    }
}
