package com.credit.creditteacherweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class errorUI {

    @RequestMapping("/errorUI")
    public String errorUI(){
        return "/error";
    }
}
