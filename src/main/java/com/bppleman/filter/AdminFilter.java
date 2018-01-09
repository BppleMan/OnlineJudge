package com.bppleman.filter;

import com.bppleman.entity.Admin;
import com.bppleman.entity.User;
import com.bppleman.tool.CookieTool;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AdminFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String [] noFilterURI = new String[] { "/bg/admin" };
        String uri = request.getRequestURI();
        boolean doFilter = true;
        for (String s : noFilterURI) {
            if (uri.indexOf(s) != -1) {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }
        if (doFilter) {
            HttpSession session = request.getSession();
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin == null){
                session.setAttribute("wannaToURI", uri);
                response.sendRedirect(request.getContextPath() + "/bg/admin/login");
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
