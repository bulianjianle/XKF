package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.entity.PageResult;
import com.credit.mapper.Admin2Mapper;
import com.credit.mapper.DepartmentMapper;
import com.credit.pojo.Admin2;
import com.credit.pojo.Admin2Example;
import com.credit.pojo.Department;
import com.credit.pojo.DepartmentExample;
import com.credit.service.department;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.pagehelper.PageHelper;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class departmentImpl implements department {
    
    @Autowired
    private Admin2Mapper admin2Mapper;

    @Autowired
    private DepartmentMapper departmentMapper;
    
    @Override
    public List<Admin2> findAll() {
        return admin2Mapper.selectByExample(null);
    }

    @Override
    public Admin2 findOneBystatus(int id) {
        return admin2Mapper.selectByPrimaryKey(id);
    }
    
    @Override
    public void updateStatus(int id, Integer status) {
        Admin2 admin2=admin2Mapper.selectByPrimaryKey(id);
        admin2.setStatus(status);
        admin2Mapper.updateByPrimaryKey(admin2);
    }

    @Override
    public void add(Admin2 admin2) {
        admin2.setStatus(1);//状态
        admin2Mapper.insert(admin2);
    }

    @Override
    public void update(Admin2 admin2) {
        admin2Mapper.updateByPrimaryKey(admin2);
    }

    @Override
    public List<Admin2> findinfo(String info) {
        if (info!=null){
            Admin2Example example=new Admin2Example();
            example.createCriteria().andInfoEqualTo(info);
            return admin2Mapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Admin2> page=   (Page<Admin2>) admin2Mapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult findPage(Department department,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        DepartmentExample example=new  DepartmentExample();
        DepartmentExample.Criteria criteria=example.createCriteria();
        if(department!=null){
            if(department.getStuid()!=null && department.getStuid().length()>0){
                criteria.andStuidLike("%"+department.getStuid()+"%");
            }
            if(department.getInfo()!=null && department.getInfo().length()>0){
                criteria.andInfoEqualTo(department.getInfo());
            }
            if(department.getStuname()!=null && department.getStuname().length()>0){
                criteria.andStunameLike("%"+department.getStuname()+"%");
            }
        }
        Page<Department> page = (Page<Department>)departmentMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }
    
    
    @Override
    public void delete(int [] ids) {
        for(int id:ids){
            admin2Mapper.deleteByPrimaryKey(id);
        }
    }

    //部门成员实体
    public Department findOnedepartment(int id){
        return departmentMapper.selectByPrimaryKey(id);
    }
    
    //所有部门成员
    @Override
    public List<Department> findAllDepartment() {
        return departmentMapper.selectByExample(null);
    }

    

    /**
     * 增加部门成员
     */
    public void add2(Department department){
        departmentMapper.insert(department);
    }


    /**
     * 修改部门成员
     */
    public void update2(Department department){
        departmentMapper.updateByPrimaryKey(department);
    }

    //批量删除部门成员
    public void delete2(int [] ids){
        for(int id:ids){
            departmentMapper.deleteByPrimaryKey(id);
        }
    }

    //所有部门成员
    public List<Department> findByInfo(String info){
        if (info!=null){
            DepartmentExample example =new DepartmentExample();
            example.createCriteria().andInfoEqualTo(info);
            return departmentMapper.selectByExample(example);    
        }
        return null;
    }

    @Override
    public PageResult findPage2(Admin2 admin2, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        Admin2Example example=new  Admin2Example();
        Admin2Example.Criteria criteria=example.createCriteria();
        if(admin2!=null){
            if(admin2.getStuid()!=null && admin2.getStuid().length()>0){
                criteria.andStuidLike("%"+admin2.getStuid()+"%");
            }
            if(admin2.getInfo()!=null && admin2.getInfo().length()>0){
                criteria.andInfoLike("%"+admin2.getInfo()+"%");
            }
            if(admin2.getStatus()!=null ){
                criteria.andStatusEqualTo(admin2.getStatus());
            }
        }
        Page<Admin2> page = (Page<Admin2>)admin2Mapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    //未审核成员
    @Override
    public List<Department> findBystatus(String info, int status) {
        DepartmentExample example =new DepartmentExample();
        example.createCriteria().andInfoEqualTo(info).andStatusEqualTo(status);
        return departmentMapper.selectByExample(example);
    }

    @Override
    public void updateStatus2(int id, Integer status) {
        Department department=departmentMapper.selectByPrimaryKey(id);
        department.setStatus(status);
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public Department findOneBystatus2(int id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> findOnedepartmentByinfo(String stuid, String stuname, String info) {
        DepartmentExample example =new DepartmentExample();
        example.createCriteria().andInfoEqualTo(info).andStuidEqualTo(stuid).andStunameEqualTo(stuname);
        return departmentMapper.selectByExample(example);
    }
}
