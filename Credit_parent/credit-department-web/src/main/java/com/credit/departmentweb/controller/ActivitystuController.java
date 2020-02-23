package com.credit.departmentweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.credit.departmentweb.utils.AcitivityStuListener;
import com.credit.entity.PageResult;
import com.credit.entity.Result;
import com.credit.pojo.*;
import com.credit.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ActivitystuController")
public class ActivitystuController {


    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAdmin2 iAdmin2;
    //登录对象
    //private Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    
    
    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IActivitystu iActivitystu;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IStudent  iStudent;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAllClass iAllClass;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IStuMajor iStuMajor;



    //前端文档上传uploadByelsx
    @ResponseBody
    @RequestMapping("/uploadByelsx")
    private String uploadByelsx(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            if (file!=null){
                EasyExcel.read(file.getInputStream(), Stujoin.class, new AcitivityStuListener(iActivitystu)).sheet(1).headRowNumber(3).doRead();
                addStuClass();
                addStuMajor();
                addstu();
            }
            return "<h3>写入成功</h3>";
        } catch (Exception e) {
            e.printStackTrace();
            return "<h3>写入失败,格式不正确或重复写入,请检查文件</h3>";
        }
    }
    
    
    //读文件写入数据库
    @ResponseBody
    @RequestMapping("/insertUserByExcel")
    private void insertUserByExcel()throws IOException {
        // 写法1：
        String fileName = "G:\\IntelliJ IDEA 2019.2.4\\Credit_parent\\credit-teacher-web\\src\\main\\resources\\demo\\demo4.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Stujoin.class, new AcitivityStuListener(iActivitystu)).sheet(1).headRowNumber(3).doRead();
    }

    //http://localhost:9091/ActivitystuController/addStuMajor
    
    //写入班级
    @ResponseBody
    @RequestMapping("/addStuClass")
    private void addStuClass(){
        Allclass allclass=new Allclass();
        List<Student> list1=iStudent.findAll();
        for(Student student:list1){
            if (student.getStuid()!=null&&student.getStuclass()!=null){
                if (iAllClass.findone(student.getStuclass()).isEmpty()){
                    allclass.setStuclass(student.getStuclass());
                    iAllClass.insertStuClass(allclass);
                }
            }
        }

    }
    
    //写入专业
    @ResponseBody
    @RequestMapping("/addStuMajor")
    private void addStuMajor(){
        AllmajorKey allmajorKey=new AllmajorKey();
        List<Student> list1=iStudent.findAll();
        for(Student student:list1){
            if (student.getMajor()!=null){
                if (iStuMajor.findone(student.getMajor()).isEmpty()){
                    allmajorKey.setMajor(student.getMajor());
                    iStuMajor.insertStuMajor(allmajorKey);
                }
            }
        }
    }
    
    //写入学生
    @ResponseBody
    @RequestMapping("/addstu")
    private void addstu(){
        Student stu=new Student();
        List<Stujoin>  list1=iActivitystu.findAll();
        for (Stujoin stujoin:list1){
            if (stujoin.getStuid()!=null){
                if (iStudent.findOneBystuid(stujoin.getStuid()).isEmpty()){
                    stu.setStuid(stujoin.getStuid());
                    stu.setGrade(stujoin.getGrade());
                    stu.setStuname(stujoin.getStuname());
                    stu.setStuclass(stujoin.getStuclass());
                    stu.setMajor(stujoin.getMajor());
                    stu.setTeacher(stujoin.getTeacher());
                    stu.setPassword("123456");
                    iStudent.add(stu);
                }
            }
        }
        
    }
    
    //搜索分类
    @RequestMapping("/search")
    public PageResult search(@RequestBody Stujoin stujoin, int page, int rows){
        Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        stujoin.setInfo(admin2.getInfo());
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

    //增加
    @RequestMapping("/add")
    public Result add(@RequestBody Stujoin stujoin){
        try {
            Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            stujoin.setInfo(admin2.getInfo());
            iActivitystu.add(stujoin);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param stujoin
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Stujoin stujoin){
        try {
            iActivitystu.update(stujoin);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(int[] ids){
        try {
            iActivitystu.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "删除失败");
        }
    }
}
