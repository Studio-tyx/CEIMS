<%@ page import="edu.njust.service.NumberService" %>
<%@ page import="edu.njust.entity.EpidemicNumber" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: 14948
  Date: 2020/9/11
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改信息</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>
    <link rel="stylesheet" href="css/situation.css">
    <link rel="stylesheet" type="text/css" href="css/everyReport.css">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script type="text/javascript" src="js/bootstrap.js"></script>
</head>
<body>
<!--导航栏-->
<div class="nav flex">
    <div class="light-mode  nav-background nav-with nav-title ">
        <div>
            <div align="center" class="title" style="font-size: 18px;">南京理工大学</div>
            <br />
            <div align="center" class="title" style="font-size: 18px;">疫情信息管理平台</div>

            <ul class="menu li-space">
                <br />
                <a class="menu-item" href="health.jsp"><i class="icon fas fa-user icon-space"></i>查看用户健康情况</a>
                <br />
                <a class="menu-item" href="track.jsp"><i class="icon fas fa-star icon-space"></i>查看用户运动轨迹</a>
                <br />
                <a class="menu-item" href="manageUser.jsp"><i class="icon fas fa-check-square icon-space"></i>账号管理</a>
                <br />
                <a class="menu-item active" href="change.jsp"><i class="icon fas fa-compass icon-space"></i>修改数据</a>
                <br />
                <a class="menu-item" href="adminMessage.jsp"><i class="icon fas fa-comment icon-space"></i>回复留言</a>
                <br />
                <a class="menu-item" href="adminIndex.jsp"><i class="icon fas fa-step-backward icon-space"></i>回到首页</a>
            </ul>
        </div>
    </div>


    <div class="all2" style="margin-left: 100px">
        <div class="in-title3">
            <font color="#1A2B81">南京理工大学疫情情况</font>
        </div>
        <table border="0" cellspacing="0" bordercolor="purple" class="table1" style="margin-top: 80px;margin-left: 15px;">
            <form action="/CEIMS/servlet/NumberServlet" method="post" id="numberForm">
                <input type="submit" id="btn_submit2" value="提交" class="btn" style="background-color: #2a6496;color: white;margin-top: 30px;">
            <tr class="tr1">
                <td align="center" class="td2">现有确诊</td>
                <td rowspan="7" class="td3" align="center"></td>
                <td class="td2" align="center">无症状</td>
                <td rowspan="7" class="td3" align="center"></td>
                <td class="td2" align="center">现有疑似</td>
                <td rowspan="7" class="td3" align="center"></td>
                <td class="td2" align="center">现有重症</td>
            </tr>
            <!--数据-->
            <tr class="tr2">
                <!--确诊-->
                <td align="center" class="font-base">
                    <font color="#ff6a57">
                        <input type="text" placeholder="999" name="XYQZ" class="form-control" width="50%">
                    </font>
                </td>
                <!--无症状-->
                <td align="center" class="font-base">
                    <font color="#e86d48">
                        <input type="text" placeholder="999" name="WZZ" class="form-control" width="50px">
                    </font>
                </td>
                <!--疑似-->
                <td align="center" class="font-base">
                    <font color="#ec9217">
                        <input type="text" placeholder="999" name="XYYS" class="form-control" width="50px">
                    </font>
                </td>
                <!--重症-->
                <td align="center" class="font-base">
                    <font color="#545499">
                        <input type="text" placeholder="999" name="XYZZ" class="form-control" width="50px">
                    </font>
                </td>
            </tr>
                <br/>
            <tr>
                <!--确诊-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#ff6a57">
                        <input type="text" placeholder="+7" name="dXYQZ" class="form-control" width="50px">
                    </font>
                </td>
                <!--无症状-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#ee8448">
                        <input type="text" placeholder="+7" name="dWZZ" class="form-control" width="50px">
                    </font>
                </td>
                <!--疑似-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#f3af17">
                        <input type="text" placeholder="+7" name="dXYYS" class="form-control" width="50px">
                    </font>
                </td>
                <!--重症-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#7a54a6">
                        <input type="text" placeholder="+7" name="dXYZZ" class="form-control" width="50px">
                    </font>
                </td>
            </tr>
            <tr height="80px">
                <!--空行-->
            </tr>
            <tr class="tr1">
                <td align="center" class="td2">累计确诊</td>

                <td align="center" class="td2">境外输入</td>

                <td align="center" class="td2">累计治愈</td>

                <td align="center" class="td2">累计死亡</td>

            </tr>
            <tr class="tr2">
                <!--累计确诊-->
                <td align="center" class="font-base">
                    <font color="#e83132">
                        <input type="text" placeholder="909" name="LJQZ" class="form-control" width="50px">
                    </font>
                </td>
                <!--境外输入-->
                <td align="center" class="font-base">
                    <font color="#476da0">
                        <input type="text" placeholder="909" name="JWSR" class="form-control" width="50px">
                    </font>
                </td>
                <!--累计治愈-->
                <td align="center" class="font-base">
                    <font color="#10aeb5">
                        <input type="text" placeholder="909" name="LJZY" class="form-control" width="50px">
                    </font>
                </td>
                <!--累计死亡-->
                <td align="center" class="font-base">
                    <font color="#4d5054">
                        <input type="text" placeholder="909" name="LJSW" class="form-control" width="50px">
                    </font>
                </td>
            </tr>
            <tr>
                <!--累计确诊-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#e9318e">
                        <input type="text" placeholder="+7" name="dLJQZ" class="form-control" width="50px">
                    </font>
                </td>
                <!--境外输入-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#476da0">
                        <input type="text" placeholder="+7" name="dJWSR" class="form-control" width="50px">
                    </font>
                </td>
                <!--累计治愈-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#10c0db">
                        <input type="text" placeholder="+7" name="dLJZY" class="form-control" width="50px">
                    </font>
                </td>
                <!--累计死亡-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#4d98d3">
                        <input type="text" placeholder="+7" name="dLJSW" class="form-control" width="50px">
                    </font>
                </td>
            </tr>

            </form>

        </table>

</div>

</div>

<!--主体-->

</body>
</html>
