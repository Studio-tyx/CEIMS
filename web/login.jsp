<%@ page import="edu.njust.entity.User" %>
<%@ page import="edu.njust.service.UserService" %>
<%@ page import="edu.njust.entity.Admin" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User:
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/login2.css" rel="stylesheet">
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if(session.getAttribute("user")!=null){
        session.setAttribute("user",null);
    }
    if(session.getAttribute("admin")!=null){
        session.setAttribute("admin",null);
    }
%>
<div align="center" class="title_top">南京理工大学疫情信息管理平台</div>
<div class="container">
    <div class="shadow len_top">
        <div class="len_inside">
            <div class="text-center">
                <h1>登录</h1>
            </div>
            <form class="user" action="/CEIMS/servlet/LoginServlet" method="post" id="loginForm">
                <!--
                 action="/CEIMS/servlet/LoginServlet"
                -->
                <div class="form-group">
                    <input type="text" class="control control-user" name="login_account" placeholder="请输入账号" id="uname">
                </div>
                <div class="form-group">
                    <input type="password" class="control control-user" name="login_pwd" placeholder="请输入密码" id="upwd">
                </div>
                <div class="form-group">
                    <span id="msg" style="font-size: 15px; color: red"></span> <br>
                </div>
                <button type="button" class="btn btn-primary btn-user btn-block" id="loginBtn">登录</button>
                <hr>
            </form>
            <hr>
            <div class="text-center">
                <a class="small" href="register.jsp">注册账号!</a>
            </div>
        </div>
    </div>
</div>
</body>


<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">

    $("#loginBtn").click(function (){
        //获取用户ID、密码
        var uname = $("#uname").val();
        var upwd = $("#upwd").val();

        if(isEmpty(uname)){
            $("#msg").html("信息不能为空！");
            return ;
        }

        if(isEmpty(upwd)){
            $("#msg").html("信息不能为空！");
            return ;
        }
        $("#loginForm").submit();
    });


    //判断字符串是否为空
    function isEmpty(str){
        if(str==null ||str.trim()==""){
            return true;
        }
        else return false;
    }

</script>


</html>
