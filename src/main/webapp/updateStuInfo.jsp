<%--
  Created by IntelliJ IDEA.
  User: BoO
  Date: 2018/4/14
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改信息</title>

</head>
<body>
<form action="/updateInfo/UpdateStuInfoServlet" method="post">
    <input type="hidden" name="stu_id" value="${student.stu_id}">
    <table>
        <tr>
            <td>学生姓名：</td>
            <td><input type="text" name="stu_name" value="${student.stu_name}"></td>
        </tr>
        <tr>
            <td>出生日期：</td>
            <td><input type="text" name="stu_birthday" value="<fmt:formatDate value="${student.stu_birthday}" pattern="yyyy-MM-dd"/>"></td>
        </tr>
        <tr>
            <td>描述：</td>
            <td><textarea name="stu_description"  >${student.description}</textarea></td>
        </tr>
        <tr>
            <td>平均分：</td>
            <td><input type="text" name="stu_avgscore" value="${student.avgscore}"></td>
        </tr>
    <tr>
        <td colspan="2" align="center"><button type="submit">确定</button><button type="reset">重置</button></td>
    </tr>
    </table>
</form>
</body>
</html>
