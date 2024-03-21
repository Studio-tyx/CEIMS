<%--
  Created by IntelliJ IDEA.
  User: 
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/login2.css" rel="stylesheet">
<html>
<head>
    <title>用户注册</title>
</head>
<body><div align="center" class="title_top">南京理工大学疫情信息管理平台</div>
<div class="container">

    <div class="shadow len_top">
        <div class="len_inside">
            <div class="text-center">
                <h1>创建账号!</h1>
            </div>
            <form class="user" method="get" action="/CEIMS/servlet/LoginServlet">
                <div class="form-group">
                    <input type="text" class="control control-user" name="regist_user" placeholder="注册账号">
                </div>
                <div class="form-group">
                    <input type="text" class="control control-user" name="regist_department" placeholder="学院">
                </div>
                <div class="form-group">
                    <input type="text" class="control control-user" name="regist_name" placeholder="姓名">
                </div>
                <div class="form-group">
                    <input type="text" class="control control-user" name="regist_pwd" placeholder="注册密码">
                </div>
                <div class="form-group">
                    <input type="text" class="control control-user" name="regist_pwd_again" placeholder="确认密码">
                </div>
                <font color="red"></font>
                <button type="submit" class="btn btn-primary btn-user btn-block" id="registsubmit">注册</button>
                <hr>
            </form>

            <!--
            1.增加了姓名一栏
            2.希望增加一个js判断注册密码与确认密码是否相同
            3.可以在jsp页面写判断是否已存在账号 方法：UserService.existUser(String id)
            -->

            <hr>
            <div class="text-center">
                <a href="login.jsp">已有账号? 登录!</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
