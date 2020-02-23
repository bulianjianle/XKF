package com.credit.service;

import com.credit.entity.PageResult;
import com.credit.pojo.Student;

import java.util.List;

public interface IStudent {

    public List<Student> findAll() ;
    
    public List<Student> findOneBystuid(String stuid);

    public PageResult findPage(int pageNum, int pageSize);

    public PageResult findPage(Student student, int pageNum, int pageSize);

    void insertUserByExcel(List<Student> list);

    Student findOnestu(int id);

    void update(Student student);

    void add(Student student);

    void delete(int[] ids);

    Student findOneByUsername(String username);

    Student findOne(Integer id);
}
