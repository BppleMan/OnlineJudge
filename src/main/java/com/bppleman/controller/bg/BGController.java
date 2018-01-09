package com.bppleman.controller.bg;

import com.bppleman.entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BGController {
    private String prefix = "/bg";
    private String mainPage = "/main_page";

    @RequestMapping("/bg")
    public String mainPage(HttpServletRequest request, HttpSession session) {
        return prefix + mainPage;
    }
}
