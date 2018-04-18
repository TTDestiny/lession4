package com.biz.study.servlets;

import com.biz.study.entity.Page;
import com.biz.study.entity.Student;
import com.biz.study.service.Impl.StudentServiceImpl;
import com.biz.study.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateStuInfoServlet")
public class UpdateStuInfoServlet extends HttpServlet {
    private StudentService service = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码为utf-8
        request.setCharacterEncoding("utf-8");
        //获取参数
        String stu_id = request.getParameter("stu_id");
        String stu_name = request.getParameter("stu_name");
        String stu_birthday = request.getParameter("stu_birthday");
        String stu_description = request.getParameter("stu_description");
        String stu_avgscore = request.getParameter("stu_avgscore");
        String currentPage = request.getParameter("currentPage");
        String pagSize = request.getParameter("pagSize");
        //执行信息修改
        String isUpdate = service.updateStuInfo(stu_id, stu_name, stu_birthday, stu_description, stu_avgscore);
        if (isUpdate != null){//修改成功
            //查询所有学生信息
           Page page = service.getAllStu(currentPage,pagSize);
            request.setAttribute("page",page);
            request.getRequestDispatcher("/showStuInfo.jsp").forward(request,response);
        }else {
            request.setAttribute("errmsg","修改失败");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
