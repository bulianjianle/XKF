package com.credit.departmentweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.credit.departmentweb.utils.AcitivityAwardListener;
import com.credit.entity.PageResult;
import com.credit.entity.Result;
import com.credit.pojo.Admin2;
import com.credit.pojo.Department;
import com.credit.service.IActivityAward;
import com.credit.service.IAdmin2;
import com.credit.service.department;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/DepartmentController")
public class DepartmentController {

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAdmin2 iAdmin2;

    //登录对象
    //private Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());


    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private com.credit.service.department idepartment;



    //搜索分类
    @RequestMapping("/search")
    public PageResult search(@RequestBody Department department, int page, int rows){
        Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        department.setInfo(admin2.getInfo());
        department.setInfo(admin2.getInfo());
        return idepartment.findPage(department, page, rows);
    }


    //分页
    @ResponseBody
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return idepartment.findPage(page, rows);
    }

    
    
    //查询实体
    @ResponseBody
    @RequestMapping("/findOnedepartment")
    public Department findOnedepartment (int id){
        return idepartment.findOnedepartment(id);
    }

    //增加
    @RequestMapping("/add")
    public Result add(@RequestBody Department department){
        try {
            Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            department.setInfo(admin2.getInfo());
            department.setStatus(1);
            idepartment.add2(department);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Department department){
        try {
            idepartment.update2(department);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(int[] ids){
        try {
            idepartment.delete2(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "删除失败");
        }
    }
}
