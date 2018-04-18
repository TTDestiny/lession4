package com.biz.study.service;

import com.biz.study.entity.Page;
import com.biz.study.entity.Student;

import java.text.ParseException;
import java.util.List;

public interface StudentService {
    /**
     *  获取所欲学生信息
     * @return
     *
     */
   Page getAllStu(String currentPage,String pageSize);

    /**
     * 根据id查询学生信息
     * @param id
     * @return
     */
    Student getStuById(String id);

    /**
     * 插入学生信息
     * @param stu_name
     * @param stu_birthday
     * @param description
     * @param avgscore
     * @throws ParseException
     */
    void insertStu(String stu_name,String stu_birthday,String description,String avgscore) throws ParseException;

    /**
     * 修改学生信息
     * @param stu_id
     * @param stu_name
     * @param stu_birthday
     * @param description
     * @param avgscore
     * @return
     */
    String updateStuInfo(String stu_id,String stu_name,String stu_birthday,String description,String avgscore);

    /**
     * 根据id删除学生信息
     * @param stu_id
     * @return
     */
    Long delStuInfo(String stu_id);
}
