package com.credit.service;

import com.credit.entity.PageResult;
import com.credit.pojo.Admin2;
import com.credit.pojo.Department;

import java.util.List;

public interface department {
    
    //查询未审核的部门账号
    public List<Admin2> findAll();
    
    //查询未审核的单个实体
    public Admin2 findOneBystatus(int id);

    
    //部门成员实体
    public Department findOnedepartment(int id);

    
    //修改审核状态
    void updateStatus(int id, Integer status);

    /**
     * 增加
     */
    public void add(Admin2 admin2);


    /**
     * 修改
     */
    public void update(Admin2 admin2);

    public List<Admin2> findinfo(String info);


    /**
     * 返回分页列表
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);

    public PageResult findPage(Department department, int pageNum, int pageSize);
    
    //批量删除部门管理账号
    public void delete(int [] ids);

    //所有部门成员
    public List<Department> findAllDepartment();

    /**
     * 增加部门成员
     */
    public void add2(Department department);


    /**
     * 修改部门成员
     */
    public void update2(Department department);

    //批量删除部门成员
    public void delete2(int [] ids);

    //所有部门成员
    public List<Department> findByInfo(String info);


    PageResult findPage2(Admin2 admin2, int pageNum, int pageSize);

    List<Department> findBystatus(String info, int status);

    //修改学生申请的审核状态
    void updateStatus2(int id, Integer status);

    Department findOneBystatus2(int id);

    List<Department> findOnedepartmentByinfo(String stuid, String stuname, String info);
}
