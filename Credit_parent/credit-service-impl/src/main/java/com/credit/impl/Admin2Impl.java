package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.mapper.Admin2Mapper;
import com.credit.pojo.Admin2;
import com.credit.pojo.Admin2Example;
import com.credit.service.IAdmin2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class Admin2Impl implements IAdmin2 {
    
    @Autowired
    private Admin2Mapper admin2Mapper;
    
    
    @Override
    public Admin2 findOneByUsername(String username) {
        Admin2Example example = new Admin2Example();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin2> admins = admin2Mapper.selectByExample(example);
        for (Admin2 admin2:admins){
            return admin2;
        }
        return null;
    }

    @Override
    public void add(Admin2 admin2) {
        admin2Mapper.insert(admin2);
    }
    
    @Override
    public void update(Admin2 admin) {
        admin2Mapper.updateByPrimaryKey(admin);
    }

    @Override
    public Admin2 findOne(int id) {
        return admin2Mapper.selectByPrimaryKey(id);
    }
}
