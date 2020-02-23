package com.credit.departmentweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.credit.departmentweb.utils.AcitivityListener;
import com.credit.entity.PageResult;
import com.credit.entity.Result;
import com.credit.pojo.Activity;
import com.credit.pojo.Admin2;
import com.credit.pojo.Department;
import com.credit.service.IAdmin2;
import com.credit.service.IAllAcitivity;
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
    private IAllAcitivity iAllAcitivity;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private com.credit.service.department department;
    
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

    //前端文档上传uploadByelsx
    @ResponseBody
    @RequestMapping("/uploadByelsx")
    private String uploadByelsx(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            if (file!=null){
                EasyExcel.read(file.getInputStream(), Activity.class, new AcitivityListener(iAllAcitivity)).sheet().headRowNumber(3).doRead();
                addinfo();
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
        System.out.println(iAllAcitivity);
        // 写法1：
        String fileName = "G:\\IntelliJ IDEA 2019.2.4\\Credit_parent\\credit-teacher-web\\src\\main\\resources\\demo\\demo9.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Activity.class, new AcitivityListener(iAllAcitivity)).sheet().headRowNumber(3).doRead();
        
    }

    //写入部门
    @ResponseBody
    @RequestMapping("/addinfo")
    private void addinfo() {
        Department department2=new Department();
        
        List<Activity> list1=iAllAcitivity.findAll();
        for (Activity activity:list1) {
            System.out.println(activity);
            System.out.println(department.findByInfo(activity.getInfo()));
            if (department.findByInfo(activity.getInfo()).isEmpty()){
                System.out.println("执行了");
                department2.setStuid("2016210939");
                department2.setStuname(activity.getIdea());
                department2.setStuclass("02111601");
                department2.setGrade("2017");
                department2.setMajor(activity.getCompany());
                department2.setTeacher(activity.getTeacher());
                department2.setInfo(activity.getInfo());
                department.add2(department2);
            }
        }

    }

    //搜索分类
    @RequestMapping("/search")
    public PageResult search(@RequestBody Activity activity, int page, int rows){
        Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        activity.setInfo(admin2.getInfo());
        return iAllAcitivity.findPage(activity, page, rows);
    }
    
    
    //分页
    @ResponseBody
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return iAllAcitivity.findPage(page, rows);
    }
    
    //查询活动实体findOneAcitivity 
    @ResponseBody
    @RequestMapping("/findOneAcitivity")
    public Activity findOneAcitivity (int id){
        return iAllAcitivity.findOneAcitivity(id);
    }

    //增加
    @RequestMapping("/add")
    public Result add(@RequestBody Activity activity){
        try {
            Admin2 admin2 =iAdmin2.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            activity.setInfo(admin2.getInfo());
            activity.setStatus(2);
            iAllAcitivity.add(activity);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param activity
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Activity activity){
        try {
            iAllAcitivity.update(activity);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(int[] ids){
        try {
            iAllAcitivity.delete(ids);
            System.out.println("删除了ids");
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "删除失败");
        }
    }
    
}
