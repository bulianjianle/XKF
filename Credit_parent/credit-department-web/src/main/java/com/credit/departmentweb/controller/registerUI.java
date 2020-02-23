package com.credit.departmentweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class registerUI {


    @RequestMapping("/register")
    public String loginUI(){
        return "/register";
    }

}
