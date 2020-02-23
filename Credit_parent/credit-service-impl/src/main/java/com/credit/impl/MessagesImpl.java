package com.credit.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.credit.mapper.MessagesMapper;
import com.credit.pojo.Messages;
import com.credit.pojo.MessagesExample;
import com.credit.service.IMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",timeout = 3000)
@Component
public class MessagesImpl implements IMessages {
    
    @Autowired
    private MessagesMapper messagesMapper;
    
    @Override
    public List<Messages> findone(String stuid, String stuname, String aname) {
        MessagesExample example=new MessagesExample();
        example.createCriteria().andStuidEqualTo(stuid).andStunameEqualTo(stuname).andAnameEqualTo(aname);
        return messagesMapper.selectByExample(example);
    }

    @Override
    public void add(Messages messages) {
        messagesMapper.insert(messages);
    }

    @Override
    public List<Messages> findbyinfo(String info,Integer status) {
        MessagesExample example=new MessagesExample();
        example.createCriteria().andInfoEqualTo(info).andStatusEqualTo(status);
        return messagesMapper.selectByExample(example);
    }

    @Override
    public void updatemessages(Messages messages) {
        messagesMapper.updateByPrimaryKey(messages);
    }

    @Override
    public List<Messages> findbystuname(String stuid,String stuname,String mes,int status) {
        MessagesExample example=new MessagesExample();
        example.createCriteria().andStuidEqualTo(stuid).andStunameEqualTo(stuname).andStatusEqualTo(status).andMesEqualTo(mes);
        return messagesMapper.selectByExample(example);
    }
}
