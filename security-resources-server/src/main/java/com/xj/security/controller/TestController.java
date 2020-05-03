package com.xj.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("getCode")
    public String getCode(){
        return "获取授权码";
    }

    @GetMapping("index")
    public Object index(Authentication authentication){
        return authentication;
    }
}
