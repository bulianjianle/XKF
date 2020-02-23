package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.entity.PageResult;
import com.credit.mapper.StujoinMapper;
import com.credit.pojo.Stujoin;
import com.credit.pojo.StujoinExample;
import com.credit.service.IActivitystu;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class ActivitystuImpl implements IActivitystu {
    
    @Autowired
    private StujoinMapper stujoinMapper;
    
    @Override
    public List<Stujoin> findAll() {
        return stujoinMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        Page<Stujoin> page = (Page<Stujoin>)stujoinMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult findPage(Stujoin stujoin, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        StujoinExample example=new  StujoinExample();
        StujoinExample.Criteria criteria=example.createCriteria();

        if(stujoin!=null){
            if(stujoin.getBelong()!=null && stujoin.getBelong().length()>0){
                criteria.andBelongLike("%"+stujoin.getBelong()+"%");
            }
            if(stujoin.getAname()!=null && stujoin.getAname().length()>0){
                criteria.andAnameLike("%"+stujoin.getAname()+"%");
            }
            if(stujoin.getInfo()!=null && stujoin.getInfo().length()>0){
                criteria.andInfoEqualTo(stujoin.getInfo());
            }
            if(stujoin.getStuname()!=null && stujoin.getStuname().length()>0){
                criteria.andStunameLike("%"+stujoin.getStuname()+"%");
            }
        }
        Page<Stujoin> page = (Page<Stujoin>)stujoinMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Stujoin> findByBelong(String belong) {
        return null;
    }

    @Override
    public void insertUserByExcel(List<Stujoin> list) {
        for(Stujoin stujoin:list){
            stujoinMapper.insert(stujoin);
        }
        
    }

    @Override
    public Stujoin findOnestujoin(int id) {
        return stujoinMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Stujoin stujoin) {
        stujoinMapper.updateByPrimaryKey(stujoin);
    }

    @Override
    public void add(Stujoin stujoin) {
        stujoinMapper.insert(stujoin);
    }

    @Override
    public void delete(int[] ids) {
        for(int id:ids){
            stujoinMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Stujoin> findAnameAndStuName(String aname, String stuname) {
        if (aname!=null&&stuname!=null){
            StujoinExample example=new StujoinExample();
            example.createCriteria().andAnameEqualTo(aname).andStunameEqualTo(stuname);
            return stujoinMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<Stujoin> findcredit(String stuname, String belong) {
            StujoinExample example=new StujoinExample();
            example.createCriteria().andBelongEqualTo(belong).andStunameEqualTo(stuname);
            return stujoinMapper.selectByExample(example);
    }
}
