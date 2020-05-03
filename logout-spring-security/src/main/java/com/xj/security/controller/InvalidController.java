package com.xj.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvalidController {

    @GetMapping("/session/invalid")
    public String sessionInvalid(){
        return "session is invalided";
    }
}
