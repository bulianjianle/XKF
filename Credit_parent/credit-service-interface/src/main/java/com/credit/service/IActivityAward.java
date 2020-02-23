package com.credit.service;

import com.credit.entity.PageResult;
import com.credit.pojo.Prize;

import java.util.List;

public interface IActivityAward {

    public List<Prize> findAll() ;

    public PageResult findPage(int pageNum, int pageSize);

    public PageResult findPage(Prize prize, int pageNum, int pageSize);

    List<Prize> findByBelong(String belong);

    void insertUserByExcel(List<Prize> list);

    Prize findOnestuprize(int id);

    void update(Prize prize);

    void add(Prize prize);

    void delete(int[] ids);

    List<Prize> findAnameAndStuName(String aname,String stuname);

    List<Prize> findcredit(String stuname, String belong);

}
