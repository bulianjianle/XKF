package com.credit.studentweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.PageResult;
import com.credit.entity.Result;
import com.credit.pojo.Student;
import com.credit.pojo.Stujoin;
import com.credit.service.IActivitystu;
import com.credit.service.IAllClass;
import com.credit.service.IStuMajor;
import com.credit.service.IStudent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ActivitystuController")
public class ActivitystuController {
    
    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IActivitystu iActivitystu;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IStudent  iStudent;



    
    
    //搜索分类
    @RequestMapping("/search")
    public PageResult search(@RequestBody Stujoin stujoin, int page, int rows){
        Student student =iStudent.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        stujoin.setStuname(student.getStuname());
        return iActivitystu.findPage(stujoin, page, rows);
    }


    //分页
    @ResponseBody
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return iActivitystu.findPage(page, rows);
    }

    //查询活动实体findOneAcitivity 
    @ResponseBody
    @RequestMapping("/findOneAcitivity")
    public Stujoin findOneAcitivity (int id){
        return iActivitystu.findOnestujoin(id);
    }

    
}
