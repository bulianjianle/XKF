package com.credit.creditteacherweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginUI {


    @RequestMapping("/login")
    public String loginUI(){
        return "/login";
    }
    
}
