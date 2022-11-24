package com.qys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author qys
 * @description
 * @create 2022-11-24-19:25
 */
@Controller
public class SwaggerController {

    @GetMapping("/swagger-ui")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }
}
