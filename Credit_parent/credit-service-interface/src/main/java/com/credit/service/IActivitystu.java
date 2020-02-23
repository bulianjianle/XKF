package com.credit.service;

import com.credit.entity.PageResult;
import com.credit.pojo.Stujoin;

import java.util.List;

public interface IActivitystu {
    public List<Stujoin> findAll() ;

    public PageResult findPage(int pageNum, int pageSize);

    public PageResult findPage(Stujoin join, int pageNum, int pageSize);

    List<Stujoin> findByBelong(String belong);

    void insertUserByExcel(List<Stujoin> list);

    Stujoin findOnestujoin(int id);

    void update(Stujoin join);

    void add(Stujoin join);

    void delete(int[] ids);

    List<Stujoin> findAnameAndStuName(String aname,String stuname);

    List<Stujoin> findcredit(String stuname, String belong);
}
