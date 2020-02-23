package com.credit.creditteacherweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.creditteacherweb.utils.ChineseCharToEnUtil;
import com.credit.entity.PageResult;
import com.credit.entity.Result;
import com.credit.pojo.Admin2;
import com.credit.pojo.Department;
import com.credit.service.department;
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


    //增加部门
    @ResponseBody
    @RequestMapping("/addinfo")
    public void addinfo(){
        Admin2 admin2=new Admin2();
        ChineseCharToEnUtil chineseCharToEnUtil=new ChineseCharToEnUtil();
        List<Department> list =department.findAllDepartment();
        for(Department department1:list){
            System.out.println(chineseCharToEnUtil.getAllFirstLetter(department1.getInfo()));
            if (department1.getInfo()!=null&&department1.getInfo().length()!=0){
                if (department.findinfo(department1.getInfo()).isEmpty()){
                    admin2.setUsername(chineseCharToEnUtil.getAllFirstLetter(department1.getInfo()));//获得首字母
                    admin2.setPassword("123456");
                    admin2.setName(department1.getStuname());
                    admin2.setInfo(department1.getInfo());
                    admin2.setNum("0");
                    admin2.setStatus(1);
                    admin2.setTel("15915474568");
                    admin2.setStuid(department1.getStuid());
                    admin2.setStuclass(department1.getStuclass());
                    admin2.setMajor(department1.getMajor());
                    admin2.setTeacher(department1.getTeacher());
                    admin2.setGrade(department1.getGrade());
                    department.add(admin2);
                }
            }
            
            
        }
    }
    
    //所有未审核
    @ResponseBody
    @RequestMapping("/findstatus")
    public List<Admin2> findstatus(){
        List<Admin2> admin2s =department.findAll();
        List<Admin2> list =new ArrayList<Admin2>(); 
        for (Admin2 admin2:admin2s){
            if (admin2.getStatus()!=null){
                //得到未审核的部门 2-未审核
                if(admin2.getStatus()==2){
                    list.add(admin2);
                }
            }
        }
        return list;
    }

    //修改审核状态
    @ResponseBody
    @RequestMapping("/updateStatus")
    public Result updateStatus(int id, Integer status){
        try{
            department.updateStatus(id,status);
            return new Result(true, "已允许");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "已拒绝");
        }

    }
    
    //未审核的单个实体
    @ResponseBody
    @RequestMapping("/findOneBystatus")
    public Admin2 findOneBystatus(int id){
        return department.findOneBystatus(id);
    }
    
    //所有部门(包括未审核)
    @ResponseBody
    @RequestMapping("/findall")
    public List<Admin2> findall(){
        List<Admin2> list =department.findAll();
        return list;
    }


    //增加
    @RequestMapping("/add")
    public Result add(@RequestBody Admin2 admin2){
        try {
            department.add(admin2);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param admin2
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Admin2 admin2){
        try {
                department.update(admin2);
                return new Result(true, "修改成功");
            
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(int[] ids){
        try {
            department.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "删除失败");
        }
    }

    /*@ResponseBody
    @RequestMapping("/search")
    public PageResult search(@RequestBody Admin2 admin2,int page,int rows){
        return department.findPage(admin2, page, rows);

    }*/

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

    
    
    //所有部门成员
    @ResponseBody
    @RequestMapping("/findAllDepartment")
    public List<Department> findAllDepartment(){
        List<Department> list =department.findAllDepartment();
        return list;
    }
    
    //单个部门成员实体findOnedepartment
    @ResponseBody
    @RequestMapping("/findOnedepartment")
    public Department findOnedepartment(int id){
        return department.findOnedepartment(id);
    }

    
    //增加部门成员
    @RequestMapping("/add2")
    public Result add2(@RequestBody Department department1){
        try {
            department.add2(department1);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改部门成员
     * @param department1
     * @return
     */
    @RequestMapping("/update2")
    public Result update2(@RequestBody Department department1){
        try {
            department.update2(department1);
            return new Result(true, "修改成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    //删除部门成员
    @RequestMapping("/delete2")
    public Result delete2(int[] ids){
        System.out.println("删除2执行");
        try {
            department.delete2(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "删除失败");
        }
    }
    
    //根据部门查询findByInfo
    @ResponseBody
    @RequestMapping("/findByInfo")
    public List<Department> findByInfo(String info){
        List<Department> list =department.findByInfo(info);
        return list;
    }
}
