package com.credit.service;

import com.credit.pojo.AllmajorKey;

import java.util.List;

public interface IStuMajor {

    List<AllmajorKey> findone(String stumajor);

    void insertStuMajor(AllmajorKey allmajorKey);

    List<AllmajorKey> findall();
}
