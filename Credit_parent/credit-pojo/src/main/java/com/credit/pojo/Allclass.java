package com.credit.pojo;

import java.io.Serializable;

public class Allclass implements Serializable {
    private Integer id;

    private String stuclass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass == null ? null : stuclass.trim();
    }
}