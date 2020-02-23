package com.credit.creditteacherweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.credit.entity.Result;
import com.credit.pojo.Activity;
import com.credit.service.IAcitivity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Acitivity")
public class AcitivityController {

    @Reference(version = "1.0.0",timeout = 3000,lazy = true)
    private IAcitivity iAcitivity;


    //所有未审核活动
    @ResponseBody
    @RequestMapping("/findstatus")
    public List<Activity> findstatus(){
        List<Activity> activities =iAcitivity.findAll();
        List<Activity> list =new ArrayList<Activity>();
        for (Activity activity:activities){
            //得到未审核的部门 2-未审核
            if (activity.getStatus()!=null){
                if(activity.getStatus()==2){
                    list.add(activity);
                }
            }
        }
        return list;
    }

    //未审核的单个实体
    @ResponseBody
    @RequestMapping("/findOneBystatus")
    public Activity findOneBystatus(int id){
        return iAcitivity.findOneBystatus(id);
    }


    //修改审核状态
    @ResponseBody
    @RequestMapping("/updateStatus")
    public Result updateStatus(int id, Integer status){
        try{
            iAcitivity.updateStatus(id,status);
            return new Result(true, "成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }

    }
}
