package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.entity.PageResult;
import com.credit.mapper.PrizeMapper;
import com.credit.pojo.Prize;
import com.credit.pojo.PrizeExample;
import com.credit.service.IActivityAward;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class ActivityAwardImpl implements IActivityAward {
    
    @Autowired
    private PrizeMapper prizeMapper;
    
    
    @Override
    public List<Prize> findAll() {
        return prizeMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        Page<Prize> page = (Page<Prize>)prizeMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult findPage(Prize prize, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);//分页
        PrizeExample example=new  PrizeExample();
        PrizeExample.Criteria criteria=example.createCriteria();

        if(prize!=null){
            if(prize.getBelong()!=null && prize.getBelong().length()>0){
                criteria.andBelongLike("%"+prize.getBelong()+"%");
            }
            if(prize.getAname()!=null && prize.getAname().length()>0){
                criteria.andAnameLike("%"+prize.getAname()+"%");
            }
            if(prize.getInfo()!=null && prize.getInfo().length()>0){
                criteria.andInfoEqualTo(prize.getInfo());
            }
            if(prize.getStuname()!=null && prize.getStuname().length()>0){
                criteria.andStunameLike("%"+prize.getStuname()+"%");
            }
        }
        Page<Prize> page = (Page<Prize>)prizeMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Prize> findByBelong(String belong) {
        return null;
    }

    @Override
    public void insertUserByExcel(List<Prize> list) {
        for(Prize prize:list){
            prizeMapper.insert(prize);
        }
    }

    @Override
    public Prize findOnestuprize(int id) {
        return prizeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Prize prize) {
        prizeMapper.updateByPrimaryKey(prize);
    }

    @Override
    public void add(Prize prize) {
        prizeMapper.insert(prize);
    }

    @Override
    public void delete(int[] ids) {
        for(int id:ids){
            prizeMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Prize> findAnameAndStuName(String aname, String stuname) {
        if (aname!=null&&stuname!=null){
            PrizeExample example=new PrizeExample();
            example.createCriteria().andAnameEqualTo(aname).andStunameEqualTo(stuname);
            return prizeMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<Prize> findcredit(String stuname, String belong) {
        if (stuname!=null&&belong!=null){
            PrizeExample example=new PrizeExample();
            example.createCriteria().andBelongEqualTo(belong).andStunameEqualTo(stuname);
            return prizeMapper.selectByExample(example);
        }
        return null;
    }
}
