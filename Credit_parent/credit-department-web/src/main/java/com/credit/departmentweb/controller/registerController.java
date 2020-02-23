package com.credit.departmentweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.Result;
import com.credit.pojo.Admin2;
import com.credit.service.IAdmin2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registerController")
public class registerController {

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAdmin2 iAdmin2;

    //增加
    @RequestMapping("/add")
    public Result add(@RequestBody Admin2 admin2){
        try {
            if (admin2.getUsername()==null){
                return new Result(true, "请输入用户名");
            }
            if (admin2.getPassword()==null){
                return new Result(true, "请输入密码");
            }
            if (admin2.getName()==null){
                return new Result(true, "请输入负责人");
            }
            if (admin2.getInfo()==null){
                return new Result(true, "请输入所属部门");
            }
            if (admin2.getNum()==null){
                return new Result(true, "请输入部门人数");
            }
            if (admin2.getTel()==null){
                return new Result(true, "请输入负责人电话");
            }
            if (admin2.getStuid()==null){
                return new Result(true, "请输入负责人学号");
            }
            if (admin2.getStuclass()==null){
                return new Result(true, "请输入负责人班级");
            }
            if (admin2.getMajor()==null){
                return new Result(true, "请输入负责人专业");
            }
            if (admin2.getTeacher()==null){
                return new Result(true, "请输入负责人辅导员");
            }
            if (admin2.getGrade()==null){
                return new Result(true, "请输入负责人年级");
            }
            if(iAdmin2.findOneByUsername(admin2.getUsername())!=null){
                return new Result(true, "该账号已被注册");
            }
            if(admin2.getUsername()!=null||admin2.getPassword()!=null||admin2.getName()!=null||admin2.getInfo()!=null||admin2.getNum()!=null||admin2.getTel()!=null||admin2.getStuid()!=null||admin2.getStuclass()!=null||admin2.getMajor()!=null||admin2.getTeacher()!=null||admin2.getGrade()!=null){
                admin2.setStatus(2);
                iAdmin2.add(admin2);
                return new Result(true, "申请成功,请等待审核");    
            }
            return new Result(true, "注册失败请检查信息是否完整");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "注册失败请检查信息是否完整");
        }
    }
    
}
