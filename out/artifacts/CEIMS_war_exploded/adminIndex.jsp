<%@ page import="edu.njust.entity.Admin" %><%--
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
    <title>南京理工大学疫情管理平台(管理员)</title>
</head>
<body>
<%
    Object obj=session.getAttribute("admin");
    Admin admin=(Admin)obj;
%>
<div >
    <a class="identity identity1" href="adminIndex.jsp">管理</a>

</div>
<div >
    <a class="identity identity2" href="userIndex.jsp">用户</a>
</div>
<div class="section-news" >

    <div class="title">
        <h1>南京理工大学疫情信息管理平台(管理端)</h1>
    </div>
    <div class="section-wrapper">
        <div class="news-body">
            <div class="personal_information">
                <a class="personal_information-card" href="#">
                    <div class="personal_information-card-bg" style="background-image: url(src/image/index_person.png)" > </div>

                    <div class="personal_information-card-top">
                        <div class="news-play-btn">
                            <span class="cui-icon cui-icon-play"></span>
                        </div>
                    </div>
                    <div class="personal_information-card-bottom">
                        <h2 class="h2" align="center">个人信息</h2>
                        <br />
                        <p class="p">姓名：<%=admin.getName()%></p>
                        <br />
                        <br />
                        <p class="p">学号：<%=admin.getID()%></p>
                    </div>
                </a>
            </div>
            <div class="chose">

                <ul class="news-list-v show">

                    <li>
                        <a href="health.jsp" class="block_set">
                            <h3 class="h3">查看用户健康情况</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                    <li>
                        <a href="track.jsp" class="block_set">
                            <h3 class="h3">查看用户运动轨迹</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                    <li>
                        <a href="manageUser.jsp" class="block_set">
                            <h3 class="h3">账号管理</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                    <li>
                        <a href="change.jsp" class="block_set">
                            <h3 class="h3">修改数据</h3>
                            <p class="p"></p>
                        </a>
                    </li>

                    <li>
                        <a href="adminMessage.jsp" class="block_set">
                            <h3 class="h3">回复消息</h3>
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
