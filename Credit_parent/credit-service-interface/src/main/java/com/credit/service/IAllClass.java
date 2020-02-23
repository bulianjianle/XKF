package com.credit.service;

import com.credit.pojo.Allclass;

import java.util.List;

public interface IAllClass {
    
    List<Allclass> findone(String stuclass);

    void insertStuClass(Allclass allclass);
    
    List<Allclass> findall();
}
