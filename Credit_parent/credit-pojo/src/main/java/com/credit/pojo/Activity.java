package com.credit.pojo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

public class Activity implements Serializable {
    @ExcelProperty(index = 0)
    private Integer id;

    @ExcelProperty(index = 1)
    private String company;

    @ExcelProperty(index = 2)
    private String belong;

    @ExcelProperty(index = 3)
    private String time;

    @ExcelProperty(index = 4)
    private String aname;

    @ExcelProperty(index = 5)
    private String info;

    @ExcelProperty(index = 6)
    private String teacher;
    
    @ExcelProperty(index = 7)
    private String idea;

    @ExcelProperty(index = 8)
    private String organizer;

    @ExcelProperty(index = 9)
    private String num;

    @ExcelProperty(index = 10)
    private String prizenum;

    @ExcelProperty(index = 11)
    private String remarks;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong == null ? null : belong.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea == null ? null : idea.trim();
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer == null ? null : organizer.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getPrizenum() {
        return prizenum;
    }

    public void setPrizenum(String prizenum) {
        this.prizenum = prizenum == null ? null : prizenum.trim();
    }
    

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", belong='" + belong + '\'' +
                ", time='" + time + '\'' +
                ", aname='" + aname + '\'' +
                ", info='" + info + '\'' +
                ", teacher='" + teacher + '\'' +
                ", idea='" + idea + '\'' +
                ", organizer='" + organizer + '\'' +
                ", num=" + num +
                ", prizenum=" + prizenum +
                ", remarks='" + remarks + '\'' +
                ", status=" + status +
                '}';
    }
}