package com.credit.creditteacherweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.Result;
import com.credit.pojo.Admin;
import com.credit.pojo.Student;
import com.credit.service.Iadmin;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/indexController")
public class indexController {


    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private Iadmin iadmin;
    
    @ResponseBody
    @RequestMapping("/name")
    public Map name(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin =iadmin.findOneByUsername(name);
        Map map=new HashMap<>();
        map.put("loginName", admin.getName());
        return map;
    }

    @ResponseBody
    @RequestMapping("/belong")
    public Map belong(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin =iadmin.findOneByUsername(name);
        Map map=new HashMap<>();
        map.put("loginbelong", admin.getBelong());
        return map;
    }
    

    @RequestMapping("/update")
    public Result update(@RequestBody Admin admin){
        try {
            iadmin.update(admin);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Admin findOne (){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);
        Admin admin =iadmin.findOneByUsername(name);
        return iadmin.findOne(admin.getId());
    }
}
