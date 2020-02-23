package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.entity.PageResult;
import com.credit.mapper.StudentMapper;
import com.credit.pojo.Student;
import com.credit.pojo.StudentExample;
import com.credit.service.IStudent;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class StudentImpl implements IStudent {
    
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Student> findAll() {
        return studentMapper.selectByExample(null);
    }

    @Override
    public List<Student> findOneBystuid(String stuid) {
        if (stuid!=null){
            StudentExample example=new StudentExample();
            example.createCriteria().andStuidEqualTo(stuid);
            return studentMapper.selectByExample(example);
        }
        return null;
    }


    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        Page<Student> page = (Page<Student>)studentMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult findPage(Student student, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        StudentExample example=new  StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        if(student!=null){
            if(student.getMajor()!=null && student.getMajor().length()>0){
                criteria.andMajorLike("%"+student.getMajor()+"%");
            }
            if(student.getStuclass()!=null && student.getStuclass().length()>0){
                criteria.andStuclassLike("%"+student.getStuclass()+"%");
            }
            if(student.getStuname()!=null && student.getStuname().length()>0){
                criteria.andStunameLike("%"+student.getStuname()+"%");
            }
        }
        Page<Student> page = (Page<Student>)studentMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void insertUserByExcel(List<Student> list) {
        for(Student student:list){
            studentMapper.insert(student);
        }
    }

    @Override
    public Student findOnestu(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Student student) {
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void add(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void delete(int[] ids) {
        for(int id:ids){
            studentMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Student findOneByUsername(String username) {
        StudentExample example = new StudentExample();
        example.createCriteria().andStuidEqualTo(username);
        List<Student> students = studentMapper.selectByExample(example);
        for (Student student:students){
            return student;
        }
        return null;
    }

    @Override
    public Student findOne(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
