package com.credit.service;

import com.credit.pojo.Activity;

import java.util.List;

public interface IAcitivity {
    //查询未审核的部门账号
    public List<Activity> findAll();

    //查询未审核的单个实体
    public Activity findOneBystatus(int id);


    //修改审核状态
    void updateStatus(int id, Integer status);
    
    
}
