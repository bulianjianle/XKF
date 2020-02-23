package com.credit.departmentweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.Result;
import com.credit.pojo.Admin2;
import com.credit.pojo.Department;
import com.credit.pojo.Messages;
import com.credit.service.IAdmin2;
import com.credit.service.IMessages;
import com.credit.service.department;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department1")
public class Department1Controller {

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private com.credit.service.department department;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAdmin2 iAdmin2;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IMessages iMessages;
    
    //登录对象
    //private Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    
    //所有未审核
    @ResponseBody
    @RequestMapping("/findstatus")
    public List<Department> findstatus(){
        Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return department.findBystatus(admin2.getInfo(),2);
    }
    
    //未审核的单个实体
    @ResponseBody
    @RequestMapping("/findOneBystatus")
    public Department findOneBystatus(int id){
        return department.findOneBystatus2(id);
    }

    //修改审核状态
    @ResponseBody
    @RequestMapping("/updateStatus")
    public Result updateStatus(int id, Integer status){
        try{
            department.updateStatus2(id,status);
            Department dep=department.findOnedepartment(id);
            Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            Messages messages=new Messages();
            //如果不存在则存入信息
            messages.setStuid(dep.getStuid());
            messages.setStuname(dep.getStuname());
            messages.setInfo(admin2.getInfo());
            messages.setStatus(2);//未阅读,当部门那点击信息后则改为已阅读
                if (status==1){
                    messages.setMes("已同意您加入该部门");
                }else{
                    messages.setMes("拒绝了您的申请");
                }
                iMessages.add(messages);
            return new Result(true, "已允许加入");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "已拒绝加入");
        }

    }
}
