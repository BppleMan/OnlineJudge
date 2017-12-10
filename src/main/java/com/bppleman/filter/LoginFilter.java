package com.bppleman.filter;

import com.bppleman.entity.User;
import com.bppleman.service.UserService;
import com.bppleman.tool.CookieTool;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BppleMan on 2017/11/21.
 */
@Component
public class LoginFilter extends OncePerRequestFilter {

    @Resource
    private UserService userService = null;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String [] noFilter = new String[] { "/users" };
        String uri = request.getRequestURI();
        boolean doFilter = true;
        for (String s : noFilter) {
            if (uri.indexOf(s) != -1) {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }
        if (doFilter) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                CookieTool.checkCookie(userService, request, request.getSession());
            }
        }
        filterChain.doFilter(request, response);
    }
}
