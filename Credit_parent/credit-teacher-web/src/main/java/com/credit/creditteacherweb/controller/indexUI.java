package com.credit.creditteacherweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexUI {

    @RequestMapping("/index")
    public String indexUI(){
        return "/index";
    }
}
