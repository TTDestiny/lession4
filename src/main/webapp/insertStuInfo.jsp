<%--
  Created by IntelliJ IDEA.
  User: BoO
  Date: 2018/4/13
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加信息</title>
    <script src="jQuery/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        //失去焦点后的姓名验证
        $(function(){
            $("#name").blur(function () {
                var name = $("#name").val();
                var gex = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
               var flag =  gex.test(name);
              if(!flag){
                $("#namemsg").html("请正确输入名字！");

              }
              if (name != ""){
                      $("#namemsg").html("");
              }
            });
            //失去焦点后的描述验证
            $("#dec").blur(function () {
                var name = $("#dec").val();
                if(name == ""){
                    $("#decmsg").html("请输入描述！");
                } else {
                    $("#decmsg").html("");
                }
            });
            //失去焦点后的日期验证
            $("#bir").blur(function () {
                var name = $("#bir").val();
                var gex = /(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)/;
                var flag =  gex.test(name);
                if(!flag){
                    $("#birmsg").html("请正确的日期！");
                } else {
                    $("#birmsg").html("");
                }
            });
            //失去焦点后的平均验证
            $("#avg").blur(function () {
                var name = $("#avg").val();
                var gex =/^[0-9]{1,3}$/;
                var flag =  gex.test(name);
                if(!flag){
                    $("#avgmsg").html("请正确的数字！");
                } else {
                    $("#avgmsg").html("");
                }
            });
        });


    </script>
</head>
<body>
<form action="/insertInfo/InsertStuInfoServlet" method="post"  >
    <table border="0">
        <tr>
            <td><label for="name">姓名：</label></td>
            <td><input id="name" type="text" name="stu_name" required  placeholder="请正确输入名字！"><span id="namemsg"></span></td>
        </tr>
        <tr>
            <td><label for="bir">出生日期：</label></td>
            <td><input type="text"  id="bir" required  name="stu_birthday"
                       pattern="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)"
                       placeholder="请输入如1977-02-02的格式" ><span id="birmsg"></span></td>
        </tr>
        <tr>
            <td><label for="dec">描述：</label></td>
            <td><textarea type="text" id="dec" required name="decription" placeholder="请输入描述！"></textarea><span id="decmsg" ></span></td>
        </tr>
        <tr>
            <td><label for="avg">平均分数：</label></td>
            <td><input id="avg" type="text"  required name="avgscore" pattern="^[0-9]{1,3}$"  placeholder="请输入数字"/><span id="avgmsg"></span></td>
        </tr>
        <tr>
            <td colspan="2" align="center"  style="position: fixed;margin-left:90px;margin-top: 20px"><input id="buto" type="submit" value="添加"></input><button type="reset">重置</button></td>
        </tr>
    </table>
</form>
</body>
</html>
