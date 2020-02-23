package com.credit.departmentweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.Result;
import com.credit.pojo.Admin;
import com.credit.pojo.Admin2;
import com.credit.pojo.Messages;
import com.credit.service.IAdmin2;
import com.credit.service.IMessages;
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
    private IAdmin2 iAdmin2;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IMessages iMessages;
    
    
    @ResponseBody
    @RequestMapping("/name")
    public Map name(){
        //登录对象
        Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Map map=new HashMap<>();
        map.put("loginName", admin2.getName());
        return map;
    }

    @ResponseBody
    @RequestMapping("/info")
    public Map info(){
        //登录对象
        Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Map map=new HashMap<>();
        map.put("logininfo", admin2.getInfo());
        return map;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Admin2 admin2){
        try {
                iAdmin2.update(admin2);
                return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Admin2 findOne (){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin2 admin2 =iAdmin2.findOneByUsername(name);
        return iAdmin2.findOne(admin2.getId());
    }

    //查询未读的信息
    @ResponseBody
    @RequestMapping("/messages")
    public List<Messages> messages (){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin2 admin2 =iAdmin2.findOneByUsername(name);
        return iMessages.findbyinfo(admin2.getInfo(),2);
    }
    
    //设置已读
    @ResponseBody
    @RequestMapping("/updatestatus")
    public Result updatestatus (){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin2 admin2 =iAdmin2.findOneByUsername(name);
        List<Messages> messagesList= iMessages.findbyinfo(admin2.getInfo(),2);
        for (Messages messages:messagesList){
            messages.setStatus(1);
            iMessages.updatemessages(messages);
        }
        return new Result(false, "已全部设为已读");
    }
}
