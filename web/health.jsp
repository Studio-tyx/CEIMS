<%@ page import="edu.njust.entity.EverydayReport" %>
<%@ page import="edu.njust.service.EverydayReportService" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.njust.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看用户健康</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>

    <link rel="stylesheet" href="css/everyReport.css">

    <script type="text/javascript" src="jquery"></script>
    <script src="./js/jquery-3.5.1.min.js" type="text/javascript" >	</script>
    <script type="text/javascript">

        function Next() {
            var current = document.forms["login"]["current"].value;
            var total = document.forms["login"]["total"].value;
            if (current==total) {
                $(document).ready(function(){
                    alert("已经到尾页！");
                })
                return false;
            }
        }
        function Back() {
            var current = document.forms["login"]["current"].value;
            if (current<=1) {
                $(document).ready(function(){
                    alert("已经到首页！");
                })
                return false;
            }
        }

        $(document).ready(function(){
            $("input").focus(function(){
                $(this).css("background-color","#cccccc");
            });
            $("input").blur(function(){
                $(this).css("background-color","#ffffff");
            });
        });
    </script>
</head>

<body>

<div class="nav flex  ">

    <div class="light-mode  nav-background nav-with nav-title ">
        <div>
            <div align="center" class="title" style="font-size: 18px;">南京理工大学</div>
            <br />
            <div align="center" class="title" style="font-size: 18px;">疫情信息管理平台</div>

            <ul class="menu li-space">
                <br />
                <a class="menu-item active" href="health.jsp"><i class="icon fas fa-user icon-space"></i>查看用户健康情况</a>
                <br />
                <a class="menu-item" href="track.jsp"><i class="icon fas fa-star icon-space"></i>查看用户运动轨迹</a>
                <br />
                <a class="menu-item" href="manageUser.jsp"><i class="icon fas fa-check-square icon-space"></i>账号管理</a>
                <br />
                <a class="menu-item" href="change.jsp"><i class="icon fas fa-compass icon-space"></i>修改数据</a>
                <br />
                <a class="menu-item" href="adminMessage.jsp"><i class="icon fas fa-comment icon-space"></i>回复留言</a>
                <br />
                <a class="menu-item" href="adminIndex.jsp"><i class="icon fas fa-step-backward icon-space"></i>回到首页</a>
            </ul>
        </div>
    </div>

    <table border="1" cellspacing="0" class="healthTable" style="margin-left: 200px;margin-top: 60px">
        <tr>
            <td colspan="7" align="center" class="everyTitle">
                用户健康
            </td>
        </tr>

        <tr class="healthtr">
            <td class="healthXH" align="center">序号</td>
            <td class="healthID" align="center">ID</td>
            <td class="healthName" align="center">姓名</td>
            <td class="healthDepart" align="center">学院</td>
            <td class="healthAddr" align="center">住址</td>
            <td class="healthTem" align="center">体温</td>
            <td class="health" align="center">健康状况</td>
        </tr>
        <%
            List<EverydayReport> reports=new EverydayReportService().findTodayReport();
            EverydayReport everydayReport=null;
            if(!reports.isEmpty()){
                int size = reports.size();
                int index =1; //starting from 1
                while (size> 0){
                    everydayReport =(EverydayReport) reports.get(size-1);
                    if(everydayReport != null){
        %>
        <tr class="healthTr">
            <td align = "center" class="healthXH"><%=index%></td>
            <td align = "center" class="healthID"><%=everydayReport.getID()%></td>
            <td align = "center" class="healthName"><%=new UserService().findUser(everydayReport.getID()).getName()%></td>
            <td align = "center" class="healthDepart"><%=new UserService().findUser(everydayReport.getID()).getDepartment()%></td>
            <td align = "center" class="healthAddr"><%=everydayReport.getAddr()%></td>
            <td align = "center" class="healthTem"><%=everydayReport.getTemperature()%></td>
            <td align = "center" class="health"><%=(everydayReport.getTemperature()>37.3 || everydayReport.getTemperature() < 36.3)? "不健康":"健康"%></td>
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
