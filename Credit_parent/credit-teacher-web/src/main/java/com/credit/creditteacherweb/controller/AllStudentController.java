package com.credit.creditteacherweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.PageResult;
import com.credit.entity.Result;
import com.credit.pojo.Allclass;
import com.credit.pojo.AllmajorKey;
import com.credit.pojo.Student;
import com.credit.service.IAllClass;
import com.credit.service.IStuMajor;
import com.credit.service.IStudent;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/AllStudentController")
public class AllStudentController {

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IStudent iStudent;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IStuMajor iStuMajor;
    
    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAllClass iAllClass;


    //查询所有班级
    @ResponseBody
    @RequestMapping("/findallclass")
    public List findallinfo(){
        List des=new ArrayList();
        List<Allclass> list =iAllClass.findall();
        for(Allclass allclass:list){
            des.add(allclass.getStuclass());
        }
        return des;
    }

    //查询所有专业
    @ResponseBody
    @RequestMapping("/findallmajor")
    public List findallmajor(){
        List bes=new ArrayList();
        List<AllmajorKey> list =iStuMajor.findall();
        for(AllmajorKey allmajorKey:list){
            bes.add(allmajorKey.getMajor());
        }
        return bes;
    }
    
    
    //搜索分类
    @RequestMapping("/search")
    public PageResult search(@RequestBody Student student, int page, int rows){
        return iStudent.findPage(student, page, rows);
    }


    //分页
    @ResponseBody
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return iStudent.findPage(page, rows);
    }

    //查询活动实体findOneAcitivity 
    @ResponseBody
    @RequestMapping("/findOneAcitivity")
    public Student findOneAcitivity (int id){
        return iStudent.findOnestu(id);
    }

    //增加
    @RequestMapping("/add")
    public Result add(@RequestBody Student student){
        try {
            iStudent.add(student);
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
    public Result update(@RequestBody Student student){
        try {
            iStudent.update(student);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(int[] ids){
        try {
            iStudent.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "删除失败");
        }
    }
}
