package com.credit.pojo;

import com.credit.entity.PageResult;

import java.io.Serializable;

public class Department implements Serializable {
    private Integer id;

    private String stuid;

    private String stuname;

    private String stuclass;

    private String grade;

    private String major;

    private String teacher;

    private String info;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass == null ? null : stuclass.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", stuid='" + stuid + '\'' +
                ", stuname='" + stuname + '\'' +
                ", stuclass='" + stuclass + '\'' +
                ", grade='" + grade + '\'' +
                ", major='" + major + '\'' +
                ", teacher='" + teacher + '\'' +
                ", info='" + info + '\'' +
                ", status=" + status +
                '}';
    }
}