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

@WebServlet(name = "DelStuInfoServlet")
public class DelStuInfoServlet extends HttpServlet {
    private StudentService service = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String stu_id = request.getParameter("stu_id");
        String currentPage = request.getParameter("currentPage");
        String pagSize = request.getParameter("pagSize");
        //执行删除
        Long delFlag = service.delStuInfo(stu_id);
        if (delFlag !=0){//成功删除
            Page page = service.getAllStu(currentPage,pagSize);
            request.setAttribute("page",page);
            request.getRequestDispatcher("/showStuInfo.jsp").forward(request,response);
        }else {//删除失败
            request.setAttribute("errmsg","信息删除失败");
            request.getRequestDispatcher("/showMsg.jsp").forward(request,response);
        }
    }
}
