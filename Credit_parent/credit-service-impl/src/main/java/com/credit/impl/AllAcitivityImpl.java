package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.entity.PageResult;
import com.credit.mapper.ActivityMapper;
import com.credit.pojo.Activity;
import com.credit.pojo.ActivityExample;
import com.credit.service.IAllAcitivity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class AllAcitivityImpl implements IAllAcitivity {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> findAll() {
        return activityMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        Page<Activity> page = (Page<Activity>)activityMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult findPage(Activity activity, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        ActivityExample example=new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();

        if(activity!=null){
            if(activity.getStatus()!=null){
                criteria.andStatusEqualTo(activity.getStatus());
            }
            if(activity.getCompany()!=null && activity.getCompany().length()>0){
                criteria.andCompanyLike("%"+activity.getCompany()+"%");
            }
            if(activity.getBelong()!=null && activity.getBelong().length()>0){
                criteria.andBelongLike("%"+activity.getBelong()+"%");
            }
            if(activity.getAname()!=null && activity.getAname().length()>0){
                criteria.andAnameLike("%"+activity.getAname()+"%");
            }
            if(activity.getInfo()!=null && activity.getInfo().length()>0){
                criteria.andInfoEqualTo(activity.getInfo());//此处应该完全相同,不应该模糊查询
            }
            if(activity.getTeacher()!=null && activity.getTeacher().length()>0){
                criteria.andTeacherLike("%"+activity.getTeacher()+"%");
            }
            if(activity.getIdea()!=null && activity.getCompany().length()>0){
                criteria.andCompanyLike("%"+activity.getCompany()+"%");
            }
        }
        Page<Activity> page = (Page<Activity>)activityMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Activity> findByBelong(String belong) {
        if (belong!=null&&belong.length()>0){
            ActivityExample example=new ActivityExample();
            ActivityExample.Criteria criteria=example.createCriteria();
            criteria.andBelongEqualTo(belong);
            return activityMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public void insertUserByExcel(List<Activity> list) {
        for (Activity activity:list){
            activityMapper.insert(activity);
        }
    }

    @Override
    public Activity findOneAcitivity(int id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Activity activity) {
        activityMapper.updateByPrimaryKey(activity);
    }

    @Override
    public void add(Activity activity) {
        activityMapper.insert(activity);
    }

    @Override
    public void delete(int[] ids) {
        for(int id:ids){
            activityMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Activity> findByAname(String Aname) {
        if (Aname!=null){
            ActivityExample example=new ActivityExample();
            ActivityExample.Criteria criteria=example.createCriteria();
            criteria.andAnameEqualTo(Aname);
            return activityMapper.selectByExample(example);
        }
        return null;
    }
}
