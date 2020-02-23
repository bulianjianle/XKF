package com.credit.service;

import com.credit.pojo.Admin;

public interface Iadmin {

    //查询单个实体
    public Admin findOneByUsername(String username);

    void update(Admin admin);

    public Admin findOne(int id);
}
