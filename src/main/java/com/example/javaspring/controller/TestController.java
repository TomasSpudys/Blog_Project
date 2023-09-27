package com.example.javaspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginPost() {
        return "redirect:/login";

    }

    @RequestMapping({"/home", "/"})
    public String home() {
        return "home";
    }

    @PostMapping("/hello")
    public String hello() {
        return "redirect:/topics";
    }

    @GetMapping("/hello")
    public String helloTest() {
        return "hello";
    }

    @PostMapping("/logout")
    public String logoutTest() {
        return "hello";
    }

    @GetMapping("/logout")
    public String logout() {
        return "hello";
    }


}

