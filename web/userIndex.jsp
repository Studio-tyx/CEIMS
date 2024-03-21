<%@ page import="edu.njust.entity.User" %>
<%@ page import="edu.njust.entity.Admin" %>
<%@ page import="edu.njust.dao.UserDAO" %><%--
  Created by IntelliJ IDEA.
  User: 14948
  Date: 2020/9/11
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/adminIndex.css" rel="stylesheet">
<html>
<head>
    <title>南京理工大学疫情管理平台</title>
</head>
<body>
<%
    User user = null;
    Object obj = session.getAttribute("user");
    if (obj != null) {
        user = (User) obj;
    } else {
        obj = session.getAttribute("admin");
        Admin admin = (Admin) obj;
        user = (User) admin;
    }
    if (new UserDAO().isAdmin(user.getID())) {
%>
<div>
    <a class="identity identity1" href="adminIndex.jsp" >管理</a>

</div>
<div>
    <a class="identity identity2" href="userIndex.jsp">用户</a>
</div>
<%
    } else {

    }
%>
<div class="section-news">

    <div class="title">
        <h1>南京理工大学疫情信息管理平台</h1>
    </div>
    <div class="section-wrapper">
        <div class="news-body">
            <div class="personal_information">
                <a class="personal_information-card" href="#">
                    <div class="personal_information-card-bg"
                         style="background-image: url('src/image/index_person.png')"></div>
                    <div class="personal_information-card-top">
                        <div class="news-play-btn">
                            <span class="cui-icon cui-icon-play"></span>
                        </div>
                    </div>
                    <div class="personal_information-card-bottom">
                        <h2 class="h2" align="center">个人信息</h2>
                        <br/>
                        <p class="p">姓名：<%=user.getName()%>
                        </p>
                        <br/>
                        <br/>
                        <p class="p">学号：<%=user.getID()%>
                        </p>
                    </div>
                </a>
            </div>
            <div class="chose">
                <ul class="news-list-v show">
                    <li>
                        <a href="exeryReport.jsp" class="block_set">
                            <h3 class="h3">每日信息填报</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                    <li>
                        <a href="askOut.jsp" class="block_set">
                            <h3 class="h3">申请出校</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                    <li>
                        <a href="replyInfo.jsp" class="block_set">
                            <h3 class="h3">返校反馈</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                    <li>
                        <a href="currentSituation.jsp" class="block_set">
                            <h3 class="h3">每日感染情况</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                    <li>
                        <a href="userMessage.jsp" class="block_set">
                            <h3 class="h3">收到消息</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                    <li>
                        <a href="login.jsp" class="block_set">
                            <h3 class="h3">退出登录</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
