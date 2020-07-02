package com.ms.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }


    // @Autowired
    // private MyUserDetailsService myUserDetailsService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/member")
    public Principal user(Principal member) {
        return member;
    }

    // @DeleteMapping(value = "/exit")
    // public Result revokeToken(String access_token) {
    //     Result result = new Result();
    //     if (consumerTokenServices.revokeToken(access_token)) {
    //         result.setCode(ResultCode.SUCCESS.getCode());
    //         result.setMessage("注销成功");
    //     } else {
    //         result.setCode(ResultCode.FAILED.getCode());
    //         result.setMessage("注销失败");
    //     }
    //     return result;
    // }
}
