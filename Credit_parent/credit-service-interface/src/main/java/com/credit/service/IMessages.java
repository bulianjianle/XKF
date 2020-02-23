package com.credit.service;

import com.credit.pojo.Messages;

import java.util.List;

public interface IMessages {
    
    List<Messages> findone(String stuid,String stuname,String aname);

    void add(Messages messages);

    List<Messages> findbyinfo(String info,Integer status);

    void updatemessages(Messages messages);

    List<Messages> findbystuname(String stuid, String stuname,String mes,int status);
}
