package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.mapper.AllmajorMapper;
import com.credit.pojo.AllmajorExample;
import com.credit.pojo.AllmajorKey;
import com.credit.service.IStuMajor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class StuMajorImpl implements IStuMajor {

    @Autowired
    private AllmajorMapper allmajorMapper;


    @Override
    public List<AllmajorKey> findone(String stuclass) {
        if (stuclass!=null){
            AllmajorExample example=new AllmajorExample();
            example.createCriteria().andMajorEqualTo(stuclass);
            return allmajorMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public void insertStuMajor(AllmajorKey allmajorKey) {
        allmajorMapper.insert(allmajorKey);
    }

    @Override
    public List<AllmajorKey> findall() {
        return allmajorMapper.selectByExample(null);
    }


}
