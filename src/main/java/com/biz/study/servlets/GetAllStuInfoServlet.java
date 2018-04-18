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
import java.util.Collections;
import java.util.List;

@WebServlet(name = "GetAllStuInfoServlet")
public class GetAllStuInfoServlet extends HttpServlet {
    private StudentService studentService =new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String currentPage = request.getParameter("currentPage");
        String pagSize = request.getParameter("pagSize");
        Page page = studentService.getAllStu(currentPage, pagSize);
        //判断是否有学生信息
            if (page.getDataList().size()==0){
                request.setAttribute("pageList",page.getDataList());
            }else {
                request.setAttribute("page",page);
            }
            request.getRequestDispatcher("/showStuInfo.jsp").forward(request,response);
    }
}
