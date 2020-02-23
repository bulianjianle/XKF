package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.mapper.AllclassMapper;
import com.credit.pojo.Allclass;
import com.credit.pojo.AllclassExample;
import com.credit.service.IAllClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Service(version = "1.0.0",timeout = 3000)
@Component
public class AllClassImpl  implements IAllClass {
    
    @Autowired
    private AllclassMapper allclassMapper;
    
    @Override
    public List<Allclass> findone(String stuclass) {
        if(stuclass!=null){
            AllclassExample example=new AllclassExample();
            example.createCriteria().andStuclassEqualTo(stuclass);
            return allclassMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public void insertStuClass(Allclass allclass) {
        allclassMapper.insert(allclass);
    }

    @Override
    public List<Allclass> findall() {
        return allclassMapper.selectByExample(null);
    }
}
