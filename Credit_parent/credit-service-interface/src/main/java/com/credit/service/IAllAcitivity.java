package com.credit.service;

import com.credit.entity.PageResult;
import com.credit.pojo.Activity;

import java.util.List;

public interface IAllAcitivity {
    
    
    public List<Activity> findAll();

    public PageResult findPage(int pageNum, int pageSize);

    public PageResult findPage(Activity activity, int pageNum, int pageSize);

    List<Activity> findByBelong(String belong);

    void insertUserByExcel(List<Activity> list);

    Activity findOneAcitivity(int id);

    void update(Activity activity);

    void add(Activity activity);

    void delete(int[] ids);

    //根据活动名查询
    List<Activity> findByAname(String Aname);

    
}
