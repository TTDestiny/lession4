package com.biz.study.servlets;

import com.biz.study.entity.Page;
import com.biz.study.entity.Student;
import com.biz.study.service.Impl.StudentServiceImpl;
import com.biz.study.service.StudentService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "InsertStuInfoServlet")
public class InsertStuInfoServlet extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式为utf-8
        request.setCharacterEncoding("utf-8");
        //获取参数
        String stu_name = request.getParameter("stu_name");
        String birthday = request.getParameter("stu_birthday");
        String decription = request.getParameter("decription");
        String avgscore = request.getParameter("avgscore");
        String currentPage = request.getParameter("currentPage");
        String pagSize = request.getParameter("pagSize");
        try {
            //执行信息添加
            studentService.insertStu(stu_name,birthday,decription,avgscore);
            //添加完成之后之执行查询
            Page page= studentService.getAllStu(currentPage,pagSize);
            request.setAttribute("page",page);
            request.getRequestDispatcher("/showStuInfo.jsp").forward(request,response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
