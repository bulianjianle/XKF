package com.credit.pojo;

import java.io.Serializable;

public class AllmajorKey implements Serializable {
    private Integer id;

    private String major;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }
}