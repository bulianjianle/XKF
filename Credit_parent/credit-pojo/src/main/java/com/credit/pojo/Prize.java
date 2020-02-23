package com.credit.pojo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

public class Prize implements Serializable {
    private Integer id;

    @ExcelProperty(index = 0)
    private String belong;

    @ExcelProperty(index = 1)
    private String aname;

    @ExcelProperty(index = 2)
    private String time;

    @ExcelProperty(index = 3)
    private String ifgroup;

    @ExcelProperty(index = 4)
    private String pname;

    @ExcelProperty(index = 5)
    private String stuname;

    @ExcelProperty(index = 6)
    private String grade;

    @ExcelProperty(index = 7)
    private String major;

    @ExcelProperty(index = 8)
    private String stuclass;

    @ExcelProperty(index = 9)
    private String stuid;

    @ExcelProperty(index = 10)
    private String teacher;

    @ExcelProperty(index = 11)
    private String info;

    @ExcelProperty(index = 12)
    private Double credit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getIfgroup() {
        return ifgroup;
    }

    public void setIfgroup(String ifgroup) {
        this.ifgroup = ifgroup == null ? null : ifgroup.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass == null ? null : stuclass.trim();
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}