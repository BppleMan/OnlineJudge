package com.bppleman.tool;

import com.bppleman.entity.User;
import com.bppleman.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by BppleMan on 2017/11/15.
 */
public class CookieTool {

    public static void checkCookie(UserService userService, HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    User user = userService.getUserByName(username);
                    session.setAttribute("user", user);
                    break;
                }
            }
        }
    }

    public static void addCookid(String cookieName, String cookieValue, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }

    public static void removeCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }
}
