<%--
  Created by IntelliJ IDEA.
  User: BoO
  Date: 2018/4/13
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <script src="/jQuery/jquery-1.8.3.js"></script>
    <title>学生信息</title>
    <style type="text/css">
        a{
            text-decoration: none;
        }
        a:hover{
            cursor:pointer;
        }
    </style>
    <script>
        function  del(stu_id) {
            if (confirm("确认删除吗？")){
             window.location.href="http://47.94.235.143:8080/delInfo/DelStuInfoServlet?stu_id="+stu_id;
            }
        }
        function upPage() {

            if( ${page.currentPage == 1}){
                alert("已经是第一页了！");
            }else{
                window.location.href="/getAllInfo/GetStudentInfoServlet?currentPage=${page.currentPage - 1}"
            }

        }
        function downPage() {

            if( ${page.currentPage == page.totaalPage}){
                alert("已经是最后一页了！");
            }else{
                window.location.href="/getAllInfo/GetStudentInfoServlet?currentPage=${page.currentPage + 1}"
            }

        }
    </script>
</head>
<body>
<div style="margin-left:480px;margin-top: 100px;">
    <table border="1">
        <tr>
            <th>学生姓名</th>
            <th>出生日期</th>
            <th>学生描述</th>
            <th>平均分</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${page.dataList}" var="stu" varStatus="rows">
            <c:if test="${rows.count % 2 ==0}">
                <tr bgcolor="#deb887">
                    <td>${stu.stu_name }</td>
                    <td><fmt:formatDate value="${stu.stu_birthday }" pattern="yyyy-MM-dd"/></td>
                    <td>${stu.description }</td>
                    <td>${stu.avgscore }</td>
                   <c:if test="${pageList !=null || page.dataList != null}">
                       <td><a  href="/getOneInfo/GetStuInfoByIdServlet?stu_id=${stu.stu_id}">修改</a>
                           |<a class="delstu" onclick="del('${stu.stu_id}');">删除</a></td></c:if>
                </tr>
            </c:if>
            <c:if test="${rows.count % 2 !=0}">
                <tr>
                    <td>${stu.stu_name }</td>
                    <td><fmt:formatDate value="${stu.stu_birthday }" pattern="yyyy-MM-dd"/></td>
                    <td>${stu.description }</td>
                    <td>${stu.avgscore }</td>
                    <c:if test="${pageList !=null || page.dataList != null}">
                        <td><a  href="/getOneInfo/GetStuInfoByIdServlet?stu_id=${stu.stu_id}">修改</a>
                            |<a class="delstu" onclick="del('${stu.stu_id}');">删除</a></td></c:if>
                </tr>
            </c:if>
        </c:forEach>
        <tr>
            <td colspan="5" align="center"><a href="/insertStuInfo.jsp">
                <button type="button">添加新用户</button>
            </a></td>
        </tr>
    </table>
    <div style="margin: 20px">
        <span style="margin-left: 40px;">
           <a id="upPage" onclick="upPage();">上一页</a></span>
        <span>
        <c:forEach begin="1" end="${page.totaalPage}" step="1" var="i" >
      <a href="/getAllInfo/GetStudentInfoServlet?currentPage=${i}">${i}&nbsp;</a>
        </c:forEach>
            </span>
        <span style="margin-left: 40px;"><a onclick="downPage();">下一页</a></span>
        <span> 当前第${page.currentPage }页/共${page.totaalPage }页</span>
    </div>
</div>
</body>
</html>
