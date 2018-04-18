package com.biz.study.servlets;

import com.biz.study.entity.Student;
import com.biz.study.service.Impl.StudentServiceImpl;
import com.biz.study.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetStuInfoByIdServlet")
public class GetStuInfoByIdServlet extends HttpServlet {
    private StudentService service = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数id
        String stu_id = request.getParameter("stu_id");
        //根据id查找学生信息
        Student stuById = service.getStuById(stu_id);
        request.setAttribute("student",stuById);
        request.getRequestDispatcher("/updateStuInfo.jsp").forward(request,response);
    }
}
