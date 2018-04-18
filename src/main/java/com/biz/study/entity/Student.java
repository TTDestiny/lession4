package com.biz.study.entity;

import java.util.Comparator;
import java.util.Date;

public class Student implements Comparable<Student> {
    private String stu_id;//学生id
    private String stu_name;//学生名字
    private Date stu_birthday;//出生日期
    private String description;//描述
    private int avgscore;//平均分数

    public Student(String stu_id, String stu_name, Date stu_birthday, String description, int avgscore) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.stu_birthday = stu_birthday;
        this.description = description;
        this.avgscore = avgscore;
    }

    public Student() {
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public Date getStu_birthday() {
        return stu_birthday;
    }

    public void setStu_birthday(Date stu_birthday) {
        this.stu_birthday = stu_birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(int avgscore) {
        this.avgscore = avgscore;
    }

    @Override
    public int compareTo(Student o) {
        if(this.avgscore >o.getAvgscore()){
            return -1;
        }else if (this.avgscore<o.getAvgscore()){
            return 1;
        }else {
            if (this.stu_name.compareTo(o.getStu_name())>0)
                return 1;
            else
                return -1;
        }

    }
}
