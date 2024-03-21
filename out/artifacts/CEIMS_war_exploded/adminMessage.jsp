<%@ page import="edu.njust.entity.EverydayReport" %>
<%@ page import="edu.njust.service.EverydayReportService" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.njust.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>回复信息</title>
    <link rel="stylesheet" href="css/x-admin.css" media="all">
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<!--图标库-->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>
<link rel="stylesheet" href="css/x-admin.css"/>
<link rel="stylesheet" href="./lib/layui/css/layui.css">
<link rel="stylesheet" href="css/everyReport.css">
<body>

<div class="nav flex  ">

    <div class="light-mode  nav-background nav-with nav-title ">
        <div>
            <div align="center" class="title" style="font-size: 18px;">南京理工大学</div>
            <br/>
            <div align="center" class="title" style="font-size: 18px;">疫情信息管理平台</div>

            <ul class="menu li-space">
                <br/>
                <a class="menu-item" href="health.jsp"><i class="icon fas fa-user icon-space"></i>查看用户健康情况</a>
                <br/>
                <a class="menu-item" href="track.jsp"><i class="icon fas fa-star icon-space"></i>查看用户运动轨迹</a>
                <br/>
                <a class="menu-item" href="manageUser.jsp"><i class="icon fas fa-check-square icon-space"></i>账号管理</a>
                <br/>
                <a class="menu-item" href="change.jsp"><i class="icon fas fa-compass icon-space"></i>修改数据</a>
                <br/>
                <a class="menu-item active" href="adminMessage.jsp"><i class="icon fas fa-comment icon-space"></i>回复留言</a>
                <br />
                <a class="menu-item" href="adminIndex.jsp"><i class="icon fas fa-step-backward icon-space"></i>回到首页</a>
            </ul>
        </div>
    </div>


    <div class="x-body" style="margin-left: 200px;">
        <xblock>
            <form action="servlet/RespondServlet" method="post" id="msgForm" class="form-control">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th style="align-content: center">ID</th>
                        <th align="center">姓名</th>
                        <th>发送时间</th>
                        <th style="width: 300px">信息内容</th>
                        <th>回复内容</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<EverydayReport> reports = new EverydayReportService().findUnansweredRemark();
                        EverydayReport everydayReport = null;
                        if (!reports.isEmpty()) {
                            int size = reports.size();
                            int index = 1; //starting from 1
                            while (size > 0) {
                                everydayReport = (EverydayReport) reports.get(size - 1);
                                if (everydayReport != null) {
                    %>
                    <tr>
                        <td align="center"><%=everydayReport.getID()%>
                        </td>
                        <td align="center"><%=new UserService().findUser(everydayReport.getID()).getName()%>
                        </td>
                        <td align="center"><%=everydayReport.getSubmitTime()%>
                        </td>
                        <td align="center"><%=everydayReport.getRemark()%>
                        </td>
                        <td align="center">
                            <textarea id="message" name="message" style="height: 50px;width: 300px;font-size: 14px;" placeholder="回复用户" class="msg form-control"></textarea>
                        </td>
                        </td>
                        <td class="td-manage">
                            <input type="submit" id="msgBtn" value="回复" class="btn" style="font-size: 14px;align-content: center;width: 40px;padding-left: 4px;"/>
                        </td>
                        <input type="text" name="ID" hidden="true" value="<%=everydayReport.getID()%>"/>
                        <input type="text" name="name" hidden="true"
                               value="<%=new UserService().findUser(everydayReport.getID()).getName()%>"/>
                        <input type="text" name="time" hidden="true" value="<%=everydayReport.getSubmitTime()%>"/>
                        <input type="text" name="remark" hidden="true" value="<%=everydayReport.getRemark()%>"/>
                    </tr>
                    <%

                                }
                                index = index + 1;
                                size = size - 1;
                            }
                        }
                    %>
                    </tbody>
                </table>
            </form>
        </xblock>
    </div>

</div>

<!--<script src="js/script.js"></script>-->
<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script src="./js/x-layui.js" charset="utf-8"></script>
<script>
    layui.use(['laydate', 'element', 'laypage', 'layer'], function () {
        $ = layui.jquery; //jquery
        laydate = layui.laydate; //日期插件
        lement = layui.element(); //面包导航
        laypage = layui.laypage; //分页
        layer = layui.layer; //弹出层

        //以上模块根据需要引入
        laypage({
            cont: 'page',
            pages: 100,
            first: 1,
            last: 100,
            prev: '<em><</em>',
            next: '<em>></em>'
        });
    });

    function question_show(argument) {
        layer.msg('唯一标识', {
            icon: 1,
            time: 1000
        });
    }

    //查看
    function person_look(title, url, id, w, h) {
        x_admin_show(title, url, w, h);
    }
</script>
<script>
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
</html>
