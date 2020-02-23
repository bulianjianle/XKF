package com.credit.studentweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.PageResult;
import com.credit.entity.Result;
import com.credit.pojo.Admin2;
import com.credit.pojo.Department;
import com.credit.pojo.Messages;
import com.credit.pojo.Student;
import com.credit.service.IMessages;
import com.credit.service.IStudent;
import com.credit.service.department;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/department")
public class departmentController {
    
    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private department department;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IStudent iStudent;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IMessages imessages;
    
    //所有部门(包括未审核)
    @ResponseBody
    @RequestMapping("/findall")
    public List<Admin2> findall(){
        List<Admin2> list =department.findAll();
        return list;
    }


    //分页
    @ResponseBody
    @RequestMapping("/findPage")
    public PageResult  findPage(int page,int rows){
        return department.findPage(page, rows);
    }


    //搜索分页部门成员
    @RequestMapping("/search")
    public PageResult search(@RequestBody Department depart, int page, int rows){
        return department.findPage(depart,page, rows);
    }

    //搜索分页部门
    @RequestMapping("/search2")
    public PageResult search2(@RequestBody Admin2 admin2, int page, int rows){
        return department.findPage2(admin2,page, rows);
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

    //报名entry
    @ResponseBody
    @RequestMapping("/entry")
    public Result entry(int id){
        Department dep=new Department();
        Admin2 admin2=department.findOneBystatus(id);
        Student student =iStudent.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        dep.setStuid(student.getStuid());
        dep.setStuname(student.getStuname());
        dep.setStuclass(student.getStuclass());
        dep.setGrade(student.getGrade());        
        dep.setMajor(student.getMajor());
        dep.setTeacher(student.getTeacher());
        dep.setInfo(admin2.getInfo());
        dep.setStatus(2);
        if (department.findOnedepartmentByinfo(student.getStuid(),student.getStuname(),admin2.getInfo()).isEmpty()){
            department.add2(dep);
            return new Result(true, "报名成功");
        }else {
            return new Result(true, "已经报名,请勿重复报名");
        }
    }
    
}
