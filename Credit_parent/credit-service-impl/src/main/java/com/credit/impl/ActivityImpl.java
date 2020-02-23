package com.credit.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.credit.mapper.ActivityMapper;
import com.credit.pojo.Activity;
import com.credit.service.IAcitivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class ActivityImpl implements IAcitivity {
    
    @Autowired
    private ActivityMapper activityMapper;
    
    @Override
    public List<Activity> findAll() {
        return activityMapper.selectByExample(null);
    }

    @Override
    public Activity findOneBystatus(int id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateStatus(int id, Integer status) {
        Activity activity=activityMapper.selectByPrimaryKey(id);
        activity.setStatus(status);
        activityMapper.updateByPrimaryKey(activity);
    }
}
