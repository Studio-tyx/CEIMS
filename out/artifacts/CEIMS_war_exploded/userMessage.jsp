<%@ page import="edu.njust.entity.EverydayRespond" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.njust.service.EverydayReportService" %>
<%@ page import="edu.njust.entity.User" %>
<%@ page import="edu.njust.entity.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收到回复</title>
</head>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>

<link rel="stylesheet" href="css/everyReport.css">
<body>

<div class="nav flex  ">

    <div class="light-mode  nav-background nav-with nav-title ">
        <div>
            <div align="center" class="title" style="font-size: 18px;">南京理工大学</div>
            <br />
            <div align="center" class="title" style="font-size: 18px;">疫情信息管理平台</div>

            <ul class="menu li-space">
                <br />
                <a class="menu-item" href="exeryReport.jsp"><i class="icon fas fa-user icon-space"></i>每日信息申报      </a>
                <br />
                <a class="menu-item" href="askOut.jsp"><i class="icon fas fa-star icon-space"></i>出校申请</a>
                <br />
                <a class="menu-item" href="replyInfo.jsp"><i class="icon fas fa-check-square icon-space"></i>返校反馈</a>
                <br />
                <a class="menu-item" href="currentSituation.jsp"><i class="icon fas fa-compass icon-space"></i>疫情情况</a>
                <br />
                <a class="menu-item active" href="userMessage.jsp"><i class="icon fas fa-comment icon-space"></i>收到回复</a>
                <br />
                <a class="menu-item" href="userIndex.jsp"><i class="icon fas fa-step-backward icon-space"></i>回到首页</a>
            </ul>
        </div>
    </div>

    <table  border="1" cellspacing="0" class="table-hover everTable" width="800px" style="margin-top: 70px;margin-left: 320px;">
        <tr>
            <td colspan="4" class="everyTitle" style="align-content: center;" align="center">收到回复</td>
        </tr>

        <tr>
            <td width="10%" class="flet-col2" align="center">序号</td>
            <td width="45%" class="flet-col2" align="center">回复内容</td>
            <td width="20%" class="flet-col2" align="center">时间</td>
            <td width="25%" class="flet-col2" align="center">回复人</td>
        </tr>
        <%
            User user=null;
            Object obj=session.getAttribute("user");
            if(obj!=null){
                user=(User)obj;
            }
            else{
                obj=session.getAttribute("admin");
                Admin admin=(Admin)obj;
                user=(User)admin;
            }
            List<EverydayRespond> reports=new EverydayReportService().getEveryRespond(user.getID());
            EverydayRespond everydayRespond=null;
            if(!reports.isEmpty()){
                int size = reports.size();
                int index =1; //starting from 1
                while (size> 0){
                    everydayRespond =(EverydayRespond) reports.get(size-1);
                    if(everydayRespond != null){
        %>
        <tr height="30px">
            <td align = "center" width="8%" class="flet-col4"><%=index%></td>
            <td align = "center" width="50%" class="flet-col4"><%=everydayRespond.getMsg()%></td>
            <td align = "center" width="24%" class="flet-col4"><%=everydayRespond.getReplyTime()%></td>
            <td align = "center" width="18%" class="flet-col4"><%=everydayRespond.getAdminID()%></td>
        </tr>
        <%
                    }
                    index = index +1;
                    size = size -1;
                }
            }
        %>
    </table>
</div>
</body>
</html>
