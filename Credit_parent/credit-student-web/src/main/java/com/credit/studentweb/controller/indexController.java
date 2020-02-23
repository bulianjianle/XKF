package com.credit.studentweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.Result;
import com.credit.pojo.Admin;
import com.credit.pojo.Messages;
import com.credit.pojo.Student;
import com.credit.service.IMessages;
import com.credit.service.IStudent;
import com.credit.service.Iadmin;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/indexController")
public class indexController {


    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IStudent iStudent;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IMessages iMessages;
    
    @ResponseBody
    @RequestMapping("/name")
    public Map name(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student =iStudent.findOneByUsername(name);
        Map map=new HashMap<>();
        map.put("loginName", student.getStuname());
        return map;
    }

    @ResponseBody
    @RequestMapping("/major")
    public Map major(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student =iStudent.findOneByUsername(name);
        Map map=new HashMap<>();
        map.put("loginmajor", student.getMajor());
        return map;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Student student){
        try {
            iStudent.update(student);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Student findOne (){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);
        Student student =iStudent.findOneByUsername(name);
        return iStudent.findOne(student.getId());
    }

    //学生查询未读的信息
    @ResponseBody
    @RequestMapping("/messages")
    public List<Messages> messages (){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student =iStudent.findOneByUsername(name);
        List<Messages> list=new ArrayList<>();
        List<Messages> list2=iMessages.findbystuname(student.getStuid(),student.getStuname(),"已同意您加入该部门",2);
        for (Messages a:list2){
            list.add(a);
        }
        List<Messages> list3=iMessages.findbystuname(student.getStuid(),student.getStuname(),"拒绝了您的申请",2);
        for (Messages b:list3){
            list.add(b);
        }
        return list;
    }

    //设置已读
    @ResponseBody
    @RequestMapping("/updatestatus")
    public Result updatestatus (){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student =iStudent.findOneByUsername(name);
        List<Messages> messagesList= iMessages.findbystuname(student.getStuid(),student.getStuname(),"已同意您加入该部门",2);
        for (Messages messages:messagesList){
            messages.setStatus(1);
            iMessages.updatemessages(messages);
        }
        return new Result(false, "已全部设为已读");
    }
}
