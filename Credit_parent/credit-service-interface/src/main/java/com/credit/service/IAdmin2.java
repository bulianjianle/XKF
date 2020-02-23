package com.credit.service;

import com.credit.pojo.Admin2;

public interface IAdmin2 {

    //查询单个实体
    public Admin2 findOneByUsername(String username);

    void update(Admin2 admin);

    public Admin2 findOne(int id);

    void add(Admin2 admin2);
}
