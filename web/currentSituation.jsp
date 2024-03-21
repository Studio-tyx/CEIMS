<%@ page import="java.sql.Date" %>
<%@ page import="edu.njust.service.NumberService" %>
<%@ page import="edu.njust.entity.EpidemicNumber" %><%--
  Created by IntelliJ IDEA.
  User: 14948
  Date: 2020/9/11
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>疫情情况</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>
    <link rel="stylesheet" href="css/situation.css">
</head>
<body>
<%
    NumberService numberService=new NumberService();
    numberService.updateNational();
    EpidemicNumber nation=numberService.getCurrentNational();
    Date date=new Date(System.currentTimeMillis());

%>
<!--导航栏-->
<div class="nav flex">
    <div class="light-mode  nav-background nav-with nav-title ">
        <div>
            <div align="center" class="title" style="font-size: 18px;">南京理工大学</div>
            <br />
            <div align="center" class="title" style="font-size: 18px;">疫情信息管理平台</div>
            <ul class="menu li-space">
                <br />
                <a class="menu-item" href="exeryReport.jsp"><i class="icon fas fa-user icon-space"></i>每日信息申报</a>
                <br />
                <a class="menu-item" href="askOut.jsp"><i class="icon fas fa-star icon-space"></i>出校申请</a>
                <br />
                <a class="menu-item" href="replyInfo.jsp"><i class="icon fas fa-check-square icon-space"></i>返校反馈</a>
                <br />
                <a class="menu-item active" href="currentSituation.jsp"><i class="icon fas fa-compass icon-space"></i>疫情情况</a>
                <br />
                <a class="menu-item" href="userMessage.jsp"><i class="icon fas fa-comment icon-space"></i>收到消息</a>
                <br />
                <a class="menu-item" href="userIndex.jsp"><i class="icon fas fa-step-backward icon-space"></i>回到首页</a>
            </ul>
        </div>
    </div>



    <div class="all2">

        <!--全国疫情-->



        <div class="in-title3">
            <font color="#1A2B81">全国疫情情况</font>
        </div>

        <table border="0" cellspacing="0" bordercolor="purple" class="table1" style="margin-top: 50px;">
            <tr class="tr1">
                <td align="center" class="td2">现有确诊</td>
                <td rowspan="7" class="td1" align="center"></td>
                <td class="td2" align="center">无症状</td>
                <td rowspan="7" class="td1" align="center"></td>
                <td class="td2" align="center">现有疑似</td>
                <td rowspan="7" class="td1" align="center"></td>
                <td class="td2" align="center">现有重症</td>
            </tr>
            <!--数据-->
            <tr class="tr2">
                <!--确诊-->
                <td align="center" class="font-base">
                    <font color="#ff6a57"><%=nation.getXYQZ()%></font>
                </td>
                <!--无症状-->
                <td align="center" class="font-base">
                    <font color="#e86d48"><%=nation.getWZZ()%></font>
                </td>
                <!--疑似-->
                <td align="center" class="font-base">
                    <font color="#ec9217"><%=nation.getXYYS()%></font>
                </td>
                <!--重症-->
                <td align="center" class="font-base">
                    <font color="#545499"><%=nation.getXYZZ()%></font>
                </td>
            </tr>
            <tr>
                <!--确诊-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#ff6a57"><%=nation.getdXYQZ()>=0?"+":""%><%=nation.getdXYQZ()%></font>
                </td>
                <!--无症状-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#ee8448"><%=nation.getdWZZ()>=0?"+":""%><%=nation.getdWZZ()%></font>
                </td>
                <!--疑似-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#f3af17"><%=nation.getdXYYS()>=0?"+":""%><%=nation.getdXYYS()%></font>
                </td>
                <!--重症-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#7a54a6"><%=nation.getdXYZZ()>=0?"+":""%><%=nation.getdXYZZ()%></font>
                </td>
            </tr>
            <tr height="60px">
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
                    <font color="#e83132"><%=nation.getLJQZ()%></font>
                </td>
                <!--境外输入-->
                <td align="center" class="font-base">
                    <font color="#476da0"><%=nation.getJWSR()%></font>
                </td>
                <!--累计治愈-->
                <td align="center" class="font-base">
                    <font color="#10aeb5"><%=nation.getLJZY()%></font>
                </td>
                <!--累计死亡-->
                <td align="center" class="font-base">
                    <font color="#4d5054"><%=nation.getLJSW()%></font>
                </td>
            </tr>
            <tr>
                <!--累计确诊-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#e9318e"><%=nation.getdLJQZ()>=0?"+":""%><%=nation.getdLJQZ()%></font>
                </td>
                <!--境外输入-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#476da0"><%=nation.getdJWSR()>=0?"+":""%><%=nation.getdJWSR()%></font>
                </td>
                <!--累计治愈-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#10c0db"><%=nation.getdLJZY()>=0?"+":""%><%=nation.getdLJZY()%></font>
                </td>
                <!--累计死亡-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#4d98d3"><%=nation.getdLJSW()>=0?"+":""%><%=nation.getdLJSW()%></font>
                </td>
            </tr>
        </table>
        <%
            EpidemicNumber campus=numberService.getCampus(date);
            if(campus==null){
        %>
        <script type="text/javascript">
            alert("管理员尚未添加今日学校疫情数据！");
        </script>
        <%
        }else{
        %>
        <div class="in-title2" style="margin-top: 350px;">
            <font color="#1A2B81">南京理工大学疫情情况</font>
        </div>
        <table border="0" cellspacing="0" bordercolor="purple" class="table1" style="margin-top: 50px;">
            <tr class="tr1">
                <td align="center" class="td2">现有确诊</td>
                <td rowspan="7" class="td1" align="center"></td>
                <td class="td2" align="center">无症状</td>
                <td rowspan="7" class="td1" align="center"></td>
                <td class="td2" align="center">现有疑似</td>
                <td rowspan="7" class="td1" align="center"></td>
                <td class="td2" align="center">现有重症</td>
            </tr>
            <!--数据-->
            <tr class="tr2">
                <!--确诊-->
                <td align="center" class="font-base">
                    <font color="#ff6a57"><%=campus.getXYQZ()%></font>
                </td>
                <!--无症状-->
                <td align="center" class="font-base">
                    <font color="#e86d48"><%=campus.getWZZ()%></font>
                </td>
                <!--疑似-->
                <td align="center" class="font-base">
                    <font color="#ec9217"><%=campus.getXYYS()%></font>
                </td>
                <!--重症-->
                <td align="center" class="font-base">
                    <font color="#545499"><%=campus.getXYZZ()%></font>
                </td>
            </tr>
            <tr>
                <!--确诊-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#ff6a57"><%=campus.getdXYQZ()>=0?"+":""%><%=campus.getdXYQZ()%></font>
                </td>
                <!--无症状-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#ee8448"><%=campus.getdWZZ()>=0?"+":""%><%=campus.getdWZZ()%></font>
                </td>
                <!--疑似-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#f3af17"><%=campus.getdXYYS()>=0?"+":""%><%=campus.getdXYYS()%></font>
                </td>
                <!--重症-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#7a54a6"><%=campus.getdXYZZ()>=0?"+":""%><%=campus.getdXYZZ()%></font>
                </td>
            </tr>
            <tr height="60px">
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
                    <font color="#e83132"><%=campus.getLJQZ()%></font>
                </td>
                <!--境外输入-->
                <td align="center" class="font-base">
                    <font color="#476da0"><%=campus.getJWSR()%></font>
                </td>
                <!--累计治愈-->
                <td align="center" class="font-base">
                    <font color="#10aeb5"><%=campus.getLJZY()%></font>
                </td>
                <!--累计死亡-->
                <td align="center" class="font-base">
                    <font color="#4d5054"><%=campus.getLJSW()%></font>
                </td>
            </tr>
            <tr>
                <!--累计确诊-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#e9318e"><%=campus.getdLJQZ()>=0?"+":""%><%=campus.getdLJQZ()%></font>
                </td>
                <!--境外输入-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#476da0"><%=campus.getdJWSR()>=0?"+":""%><%=campus.getdJWSR()%></font>
                </td>
                <!--累计治愈-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#10c0db"><%=campus.getdLJZY()>=0?"+":""%><%=campus.getdLJZY()%></font>
                </td>
                <!--累计死亡-->
                <td align="center">
                    <font color="#999999">昨日</font>
                    <font color="#4d98d3"><%=campus.getdLJSW()>=0?"+":""%><%=campus.getdLJSW()%></font>
                </td>
            </tr>
        </table>
<%
    }
%>


    </div>


</div>
<!--主体-->

<script src="js/script.js"></script>

</body>
</html>
