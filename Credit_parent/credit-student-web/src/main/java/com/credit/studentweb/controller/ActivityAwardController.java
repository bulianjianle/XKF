package com.credit.studentweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.PageResult;
import com.credit.pojo.Prize;
import com.credit.pojo.Student;
import com.credit.pojo.Stujoin;
import com.credit.service.IActivityAward;
import com.credit.service.IActivitystu;
import com.credit.service.IStudent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/ActivityAwardController")
public class ActivityAwardController {

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IActivityAward iActivityAward;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IStudent iStudent;

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IActivitystu iActivitystu;

    
    //搜索分类
    @RequestMapping("/search")
    public PageResult search(@RequestBody Prize prize, int page, int rows){
        Student student =iStudent.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        prize.setStuname(student.getStuname());
        return iActivityAward.findPage(prize, page, rows);
    }


    //分页
    @ResponseBody
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return iActivityAward.findPage(page, rows);
    }

    
    //double类型数据相加工具方法
    private Double sun(Double num, Double credit) {
        BigDecimal bd1 = new BigDecimal(Double.toString(num));
        BigDecimal bd2 = new BigDecimal(Double.toString(credit));
        return bd1.add(bd2).doubleValue();
    }

    
    //返回艺术板块学分
    @ResponseBody
    @RequestMapping("/getysxy")
    public String getysxy(){
        Student student =iStudent.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Double num=0.0;
        List<Prize> prizes=iActivityAward.findcredit(student.getStuname(),"艺术修养与实践艺术修养与实践");
        for (Prize prize:prizes){
            num=sun(num,prize.getCredit());
        }
        List<Stujoin> stujoins=iActivitystu.findcredit(student.getStuname(),"艺术修养与实践艺术修养与实践");
        for (Stujoin stujoin:stujoins){
            num=sun(num,stujoin.getCredit());
        }
        return Double.toString(num);
    }



    //返回竞技体育板块学分
    @ResponseBody
    @RequestMapping("/getjjty")
    public String getjjty(){
        Student student =iStudent.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Double num=0.0;
        List<Prize> prizes=iActivityAward.findcredit(student.getStuname(),"竞技体育");
        for (Prize prize:prizes){
            num=sun(num,prize.getCredit());
        }
        List<Stujoin> stujoins=iActivitystu.findcredit(student.getStuname(),"竞技体育");
        for (Stujoin stujoin:stujoins){
            num=sun(num,stujoin.getCredit());
        }
        return Double.toString(num);
    }

    //返回志愿者服务板块学分
    @ResponseBody
    @RequestMapping("/getzyzfw")
    public String getzyzfw(){
        Student student =iStudent.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Double num=0.0;
        List<Prize> prizes=iActivityAward.findcredit(student.getStuname(),"志愿者服务");
        for (Prize prize:prizes){
            num=sun(num,prize.getCredit());
        }
        List<Stujoin> stujoins=iActivitystu.findcredit(student.getStuname(),"志愿者服务");
        for (Stujoin stujoin:stujoins){
            num=sun(num,stujoin.getCredit());
        }
        return Double.toString(num);
    }

    //返回志愿者服务板块学分
    @ResponseBody
    @RequestMapping("/getsthd")
    public String getsthd(){
        Student student =iStudent.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Double num=0.0;
        List<Prize> prizes=iActivityAward.findcredit(student.getStuname(),"校园社团活动");
        for (Prize prize:prizes){
            num=sun(num,prize.getCredit());
        }
        List<Stujoin> stujoins=iActivitystu.findcredit(student.getStuname(),"校园社团活动");
        for (Stujoin stujoin:stujoins){
            num=sun(num,stujoin.getCredit());
        }
        return Double.toString(num);
    }
    
    //返回所有学分
    @ResponseBody
    @RequestMapping("/getall")
    public String getall(){
        Double num=0.0;
        num=sun(num,Double.parseDouble(getsthd()));
        num=sun(num,Double.parseDouble(getysxy()));
        num=sun(num,Double.parseDouble(getjjty()));
        num=sun(num,Double.parseDouble(getzyzfw()));
        return Double.toString(num);
    }
    
}
