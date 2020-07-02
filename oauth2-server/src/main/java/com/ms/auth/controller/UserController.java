package com.ms.auth.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello";
    }


    @GetMapping("/admin/hello")
    public String admin(String name) {
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String user(String name) {
        return "hello user";
    }
}