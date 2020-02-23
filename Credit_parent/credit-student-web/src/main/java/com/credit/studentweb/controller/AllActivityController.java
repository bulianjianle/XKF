package com.credit.studentweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.PageResult;
import com.credit.entity.Result;
import com.credit.pojo.*;
import com.credit.service.IAdmin2;
import com.credit.service.IAllAcitivity;
import com.credit.service.IMessages;
import com.credit.service.IStudent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/AllAcitivity")
public class AllActivityController {

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAdmin2 iAdmin2;
    //登录对象
    //private Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IStudent iStudent;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAllAcitivity iAllAcitivity;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private com.credit.service.department department;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IMessages imessages;
    
    //根据版块名查询
    @ResponseBody
    @RequestMapping("/findByBelong")
    public List<Activity> findByBelong(String belong){
        List<Activity> list =iAllAcitivity.findByBelong(belong);
        return list;
    }

    //查询所有部门名
    @ResponseBody
    @RequestMapping("/findallinfo")
    public List  findallinfo(){
        List des=new ArrayList();
        List<Department> list =department.findAllDepartment();
        for(Department department:list){
            des.add(department.getInfo());
        }
        return des;
    }
    
    //findallbelong
    //查询所有板块名
    @ResponseBody
    @RequestMapping("/findallbelong")
    public List  findallbelong(){
        List bes=new ArrayList();
        bes.add("竞技体育");
        bes.add("校园社团活动");
        bes.add("艺术修养与实践");
        bes.add("志愿者服务");
        return bes;
    }

    


    //搜索分类
    @RequestMapping("/search")
    public PageResult search(@RequestBody Activity activity, int page, int rows){
        activity.setStatus(1);
        return iAllAcitivity.findPage(activity, page, rows);
    }
    
    
    //分页
    @ResponseBody
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return iAllAcitivity.findPage(page, rows);
    }
    
    //报名entry
    @ResponseBody
    @RequestMapping("/entry")
    public Result entry(int id){
        Messages messages=new Messages();
        Activity activity=iAllAcitivity.findOneAcitivity(id);
        Student student =iStudent.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (imessages.findone(student.getStuid(),student.getStuname(),activity.getAname()).isEmpty()){
            //如果不存在则存入信息
            messages.setAname(activity.getAname());
            messages.setStuid(student.getStuid());
            messages.setStuname(student.getStuname());
            messages.setInfo(activity.getInfo());
            messages.setMes("想要参加");
            messages.setStatus(2);//未阅读,当部门那点击信息后则改为已阅读
            imessages.add(messages);
            return new Result(true, "报名成功");
        }else {
            return new Result(true, "已经报名,请勿重复报名");
        }
    }
    
    
}
