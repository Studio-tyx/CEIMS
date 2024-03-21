<%@ page import="edu.njust.entity.User" %>
<%@ page import="edu.njust.entity.Admin" %>
<%@ page import="edu.njust.servlet.OutInServlet" %>
<%@ page import="edu.njust.service.OutBackService" %><%--
  Created by IntelliJ IDEA.
  User: 14948
  Date: 2020/9/11
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>返校反馈</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>

<%--    <link rel="stylesheet" href="css/massage.css">--%>
    <link rel="stylesheet" href="css/layui/css/layui.css">
    <link rel="stylesheet" href="css/icheck/minimal/red.css">
    <link rel="stylesheet" href="css/replyInfo.css">
    <link rel="stylesheet" href="css/bootstrap-dialog.css" />
<%--    <link rel="stylesheet" href="css/bootstrap.min.css" />--%>
    <link rel="stylesheet" href="css/everyReport.css">

    <script type="text/javascript" src="js/bootstrap-dialog.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <script src="js/jquery.js"></script>
    <script src="addr.json"></script>
</head>

<body>
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
    OutBackService outBackService=new OutBackService();
    if(!outBackService.isOut(user.getID())){
%>
<script type="text/javascript">
    alert("您尚未提交出校申请！");
    document.location.href="userIndex.jsp";
</script>
<%
}else{
%>
<div class="nav flex">
    <div class="light-mode  nav-background nav-with nav-title ">
        <div>
            <div align="center" class="title" style="font-size: 18px;">南京理工大学</div>
            <br />
            <div align="center" class="title" style="font-size: 18px;"> 疫情信息管理平台</div>
            <ul class="menu li-space">
                <br />
                <a class="menu-item active" href="exeryReport.jsp"><i class="icon fas fa-user icon-space"></i>每日信息申报      </a>
                <br />
                <a class="menu-item" href="askOut.jsp"><i class="icon fas fa-star icon-space"></i>出校申请</a>
                <br />
                <a class="menu-item" href="replyInfo.jsp"><i class="icon fas fa-check-square icon-space"></i>返校反馈</a>
                <br />
                <a class="menu-item" href="currentSituation.jsp"><i class="icon fas fa-compass icon-space"></i>疫情情况</a>
                <br />
                <a class="menu-item" href="userMessage.jsp"><i class="icon fas fa-comment icon-space"></i>收到回复</a>
                <br />
                <a class="menu-item" href="userIndex.jsp"><i class="icon fas fa-step-backward icon-space"></i>回到首页</a>
            </ul>
        </div>
    </div>
    <form action="/CEIMS/servlet/OutInServlet" method="get" id="backForm">
    <table border="1" cellspacing="0" bordercolor="purple" id="table1" width="800px" style="margin-top: 60px;margin-left: 360px;">

        <tr height="40px">
            <td colspan="4" align="center" class="everyTitle">返校反馈</td>
        </tr>

        <tr height="40px">
            <td width="10%">姓名</td>
            <td width="30%"><%=user.getName()%></td>
            <td width=15%">ID</td>
            <td width="45%"><%=user.getID()%></td>
        </tr>
        <tr height="40px">
            <td>学院</td>
            <td colspan="3"><%=user.getDepartment()%></td>
        </tr>
        <tr height="40px">
            <td>返校时间</td>
            <td colspan="3"><input type="text" placeholder="返校时间 如:2020-09-26" name="backDate" class="temperature1 form-control" style="font-size: 14px;"/></td>
        </tr>


        </table>

        <table border="1" cellspacing="0" bordercolor="purple" id="table2" style="width: 800px;margin-top: 70px;margin-left: 360px;">
            <tr height="40px">
                <td colspan="12" align="center" class="everyTitle">途径地点
                    <button type="button" id="btn_add" class="layui-btn layui-btn-xs">
                        <i class="layui-icon">&#xe654;</i>新增
                    </button>
                </td>
            </tr>
            <tr height="40px">
                <td colspan="2" width="6%">序号</td>
                <td colspan="2" width="10%">省</td>
                <td colspan="2" width="10%">市</td>
                <td colspan="2" width="10%">区</td>
                <td colspan="2" width="36%">具体地址</td>
                <td colspan="2" width="28%">经过时间</td>
            </tr>
            <tr height="40px">
                <td colspan="2"><input type="text" name="序号1"  value="1" class="temperature form-control" /></td>
                <td colspan="2"><input type="text" placeholder="省" name="prov1" class="temperature form-control" style="font-size: 14px;" /></td>
                <td colspan="2"><input type="text" placeholder="市" name="city1" class="temperature form-control" style="font-size: 14px;"/></td>
                <td colspan="2"><input type="text" placeholder="区" name="area1" class="temperature form-control" style="font-size: 14px;"/></td>
                <td colspan="2"><input type="text" placeholder="地址" name="addr1" class="temperature form-control" style="font-size: 14px;"/></td>
                <td colspan="2"><input type="text" placeholder="到达时间 如:2020-09-26 13:30:00" name="time1" class="temperature form-control" style="font-size: 10px;"/></td>
            </tr>

        </table>
        <button id="btn_submit2" class="btn btn-primary" data-dismiss="modal" style="margin-left: 650px;margin-top: 30px;"><span aria-hidden="true"></span>提交</button>
    </form>

</div>
<%
    }
%>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="css/layui/layui.js"></script>
    <script type="text/javascript" src="css/icheck/icheck.js"></script>
    <script type="text/javascript" src="js/dw.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
    $("#btn_submit2").click(function (){
        <%
        System.out.println("hey!");
        %>
        $("#backForm").submit();
        return;
    });
</script>
    <script>
        var count=1;
        $("#btn_add").click(function() {

            count=count+1;
            var tr="<tr height=\"40px\">\n" +
                "            <td colspan=\"2\"><input type=\"text\"  name=\"序号"+count+"\" value=\""+count+"\" class=\"temperature form-control\" style=\"font-size: 14px;\" /></td>\n" +
                "            <td colspan=\"2\"><input type=\"text\" placeholder=\"省\" name=\"prov"+count+"\" class=\"temperature form-control\" style=\"font-size: 14px;\" /></td>\n" +
                "            <td colspan=\"2\"><input type=\"text\" placeholder=\"市\" name=\"city"+count+"\" class=\"temperature form-control\" style=\"font-size: 14px;\" /></td>\n" +
                "            <td colspan=\"2\"><input type=\"text\" placeholder=\"区\" name=\"area"+count+"\" class=\"temperature form-control\"  style=\"font-size: 14px;\"/></td>\n" +
                "            <td colspan=\"2\"><input type=\"text\" placeholder=\"地址\" name=\"addr"+count+"\" class=\"temperature form-control\"  style=\"font-size: 14px;\"/></td>" +
                "            <td colspan=\"2\"><input type=\"text\" placeholder=\"到达时间 如:2020-09-26 13:30:00\" name=\"time"+count+"\" class=\"temperature form-control\"  style=\"font-size: 10px;\"/></td>\n" +
                "        </tr>";
            $("#table2").append(tr);
            return ;

        });
    </script>


</body>
</html>
