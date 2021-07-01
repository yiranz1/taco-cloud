package com.yiran.tacocloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Let Component Scanner treat this class as a component, this will create a bean
@Controller
public class HomeController {
    @GetMapping("/")
    public String getHome() {
        return "home";
    }
}
