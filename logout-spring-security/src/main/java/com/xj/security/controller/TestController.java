package com.xj.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/index")
    public String index(){
        return "hello spring security";
    }

    @GetMapping("/logout/success")
    public String logout(){
        return "退出成功";
    }

    @GetMapping("/auth/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String admin(){
        return "您拥有admin权限，可以查看";
    }
}
