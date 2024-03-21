<%@ page import="edu.njust.service.OutBackService" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.njust.entity.Tour" %>
<%@ page import="edu.njust.dao.OutReportDAO" %><%--
  Created by IntelliJ IDEA.
  User: 14948
  Date: 2020/9/11
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>查看用户运动轨迹</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>
    <link rel="stylesheet" href="css/askOut.css" type="text/css">
    <link rel="stylesheet" href="css/everyReport.css" type="text/css">
    <style type="text/css">
        body,
        html {
            width: 100%;
            height: 100%;
            margin: 0;
            font-family: "微软雅黑";
            overflow: hidden;
        }

        #allmap {
            height: 100%;
            width: 100%;
            overflow: hidden;
        }

        #r-result {
            width: 100%;
            font-size: 14px;
            position: absolute;
            top: 10px;
            left: 200px
        }
    </style>
</head>
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
                <a class="menu-item active" href="track.jsp"><i class="icon fas fa-star icon-space"></i>查看用户运动轨迹</a>
                <br/>
                <a class="menu-item " href="manageUser.jsp"><i class="icon fas fa-check-square icon-space"></i>账号管理</a>
                <br/>
                <a class="menu-item" href="change.jsp"><i class="icon fas fa-compass icon-space"></i>修改数据</a>
                <br/>
                <a class="menu-item" href="adminMessage.jsp"><i class="icon fas fa-comment icon-space"></i>回复留言</a>
                <br/>
                <a class="menu-item" href="adminIndex.jsp"><i class="icon fas fa-step-backward icon-space"></i>回到首页</a>
            </ul>
        </div>
    </div>
    <%
        Object obj = session.getAttribute("tours");
        if (obj != null) {
            List<Tour> tours = (List<Tour>) obj;
    %>
    <form action="/CEIMS/servlet/TrackServlet" method="post" id="placeForm">
        <table border="1" width="400" id="courseT" cellspacing="0" style="margin-top: 60px">
            <tr>
                <td align="center">地点</td>
                <td align="center">时间</td>
            </tr>
            <%
                Tour tour = null;
                if (!tours.isEmpty()) {
                    int size = tours.size();
                    int index = 1; //starting from 1
                    while (size > 0) {
                        tour = (Tour) tours.get(size - 1);
                        if (tour != null) {
            %>
            <tr>
                <td align="center">
                    <input type="submit" name="place" class="form-control"
                           value="<%=tour.getProvince()+tour.getCity()+tour.getArea()+tour.getAddress()%>">
                    </input></td>
                <td align="center"><%=tour.getApproachTime() %>
                </td>
                <%--<td align = "center"><a href="courseManage?operation=del&cName=<%=course.getName() %>" onclick ="return delConfirm();" >删除</a></td>--%>
            </tr>
            <%
                        }
                        index = index + 1;
                        size = size - 1;
                    }
                }
            %>
        </table>
    </form>
    <%
        } else {
        }
    %>
    <!-- 地图盒子 -->
    <div id="allmap"></div>
    <!-- 搜索显示框 -->
    <div id="r-result">
        <form action="/CEIMS/servlet/TrackServlet" method="get" id="userForm" style="margin-left: 150px">
            <select name="ID" style="height: 35px; width: 180px; font-size: 10px;">
                <option value="" style="font-size: 8px;"></option>
                <%
                    int countUser = 0;
                    List<String> ids = new OutBackService().getUser();
                    for (; countUser < ids.size(); countUser++) {
                %>
                <option value=<%=ids.get(countUser)%>><%=ids.get(countUser)%>
                </option>
                <%} %>
            </select>
            <input type="submit" value="搜索" class="form-control btn" style="font-size: 10px;padding-top: 2px;" onClick="selectUser()"/>
        </form>
        <%--<form action="/CEIMS/servlet/TrackServlet" method="get" id="userForm">
        id: <input name="ID" type="text" style="width:100px; margin-left:100px;" />
        <input type="submit" value="搜索" onClick="selectUser()"/>
        &lt;%&ndash;<input type="button" value="搜索" onClick="theLocation()" />&ndash;%&gt;
        <!--经度:
        <input type="text" id="lng" />
        纬度：
        <input type="text" id="lat" />
        <button onClick="submit()">提交</button>-->
        </form>--%>
    </div>
</div>
<%
    Object p = session.getAttribute("place");
    if (p != null) {
        String place = (String) p;
        System.out.println("jsp:" + p);
%>
<input type="text" id="place" hidden="true" value="<%=place%>" style="font-size: 8px;"/>
<%
    } else {
    }
%>


<%--<script src="js/script.js"></script>--%>
<!-- js文件 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.3&services=true"></script>
<!-- 页面地图js方法 -->
<script type="text/javascript">
    // 在指定容器创建地图实例并设置最大最小缩放级别
    var map = new BMap.Map("allmap", {
        minZoom: 5,
        maxZoom: 19
    });

    // 初始化地图，设置中心点和显示级别
    map.centerAndZoom(new BMap.Point(118.85626305554197, 32.02839086398688), 19);

    // 开启鼠标滚轮缩放功能，仅对PC上有效
    map.enableScrollWheelZoom(true);

    // 将控件（平移缩放控件）添加到地图上
    map.addControl(new BMap.NavigationControl());

    // 为地图增加点击事件，为input赋值
    map.addEventListener("click", function (e) {
        document.getElementById('lat').value = e.point.lat;
        document.getElementById('lng').value = e.point.lng;

    });
    // 创建位置检索、周边检索和范围检索
    var local = new BMap.LocalSearch(map, {
        renderOptions: {
            map: map
        }
    });

    var city = document.getElementById("place").value;
    if (city != "") {
        local.search(city);
    }

    function selectUser() {
        $("#userForm").submit();
        return;
    }

    function find() {
        $("#placeForm").submit();
    }

    // 发起检索
    function theLocation() {
        var city = document.getElementById("ID").value;
        if (city != "") {
            local.search(city);
        }
    };

    // 弹出经纬度
    function submit() {
        var lat = document.getElementById('lat');
        var lng = document.getElementById('lng');
        alert("经度：" + lng.value + "\n" + "纬度：" + lat.value);
    };
</script>
</body>
</html>
