package com.credit.departmentweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class errorUI {


    @RequestMapping("/errorUI")
    public String loginUI(){
        return "/error";
    }
    
}
