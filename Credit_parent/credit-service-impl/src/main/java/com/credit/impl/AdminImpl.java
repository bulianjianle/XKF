package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.mapper.AdminMapper;
import com.credit.pojo.Admin;
import com.credit.pojo.AdminExample;
import com.credit.service.Iadmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class AdminImpl implements Iadmin {
    
    @Autowired
    private AdminMapper adminMapper;
    
    @Override
    public Admin findOneByUsername(String username) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(example);
        for (Admin admin:admins){
            return admin;
        }
        return null;
    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKey(admin);
    }

    @Override
    public Admin findOne(int id) {
        return adminMapper.selectByPrimaryKey(id);
    }
}
