<%@ page import="edu.njust.entity.User" %>
<%@ page import="edu.njust.entity.Admin" %>
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
    <style>
        select{
            width: 125px;
        }
    </style>
    <title>申请出校</title>
    <link rel="stylesheet" href="css/everyReport.css" type="text/css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">

    <%--<script src="js/laydate/laydate.js"></script>--%>
    <script src="js/jquery.js"></script>
    <script src="addr.json"></script>
<%--    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>--%>
<%--    <script type="text/javascript" src="js/angular.min.js"></script>--%>
<%--    <script type="text/javascript" src="js/wui-date.js" charset="utf-8"></script>--%>
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
    if(outBackService.existUser(user.getID())){
        if(outBackService.isOut(user.getID())){
%>
<script type="text/javascript">
    alert("您尚未销假！");
    document.location.href="userIndex.jsp";
</script>
<%
    }
}else
%>
<div class="nav flex">
    <div class="light-mode  nav-background nav-with nav-title ">
        <div>
            <div align="center" class="title" style="font-size: 18px;">南京理工大学</div>
            <br />
            <div align="center" class="title11" style="font-size: 18px;">疫情信息管理平台</div>
            <ul class="menu li-space">
                <br />
                <a class="menu-item" href="exeryReport.jsp"><i class="icon fas fa-user icon-space"></i>每日信息申报</a>
                <br />
                <a class="menu-item active" href="askOut.jsp"><i class="icon fas fa-star icon-space"></i>出校申请 &nbsp;</a>
                <br />
                <a class="menu-item" href="replyInfo.jsp"><i class="icon fas fa-check-square icon-space"></i>返校反馈</a>
                <br />
                <a class="menu-item" href="currentSituation.jsp"><i class="icon fas fa-compass icon-space"></i>疫情情况</a>
                <br />
                <a class="menu-item" href="userMessage.jsp"><i class="icon fas fa-comment icon-space"></i>收到消息</a>
                <br />
                <a class="menu-item" href="userIndex.jsp"><i class="icon fas fa-step-backward icon-space"></i>回到首页</a>
            </ul>
        </div>
    </div>
    <!--</div>-->
<%--    <h2 class="in-title">申请出校</h2>--%>
    <div class="format all">
        <form class="info" method="post" action="/CEIMS/servlet/OutInServlet" id="outForm" style="margin-left: 260px;margin-top: 100px;">
            <table border="1" cellspacing="0" class="table-hover everTable" style="margin-left: 100px;width: 900px;margin-top: 220px;">
                <!--申请信息-->
                <tr height="60px">
                    <td colspan="4" align="center" class="everyTitle">申请信息</td>
                </tr>

                <!--姓名 学院-->
                <tr height="55px">
                    <td width="70px" align="center" class="flet-col1">姓名</td>
                    <td><%=user.getName()%></td>
                    <td align="center" width="100px" class="flet-col1">学院</td>
                    <td align="200px" style="width: 400px;"><%=user.getDepartment()%></td>
                </tr>

                <!--学号联系方式-->
                <tr height="55px">
                    <td width="100px" align="center" class="flet-col1">学号</td>
                    <td><%=user.getID()%></td>
                    <td align="center" class="flet-col1">联系方式</td>
                    <td><input type="tel" placeholder="请输入电话" name="tele" class="temperature form-control"/></td>
                </tr>

                <!--申请出校时间-->
                <tr height="55px">
                    <td width="100px" align="center" class="flet-col1">申请出校时间</td>
                    <td >
                        <input type="text" id='test1' name="outTime" placeholder="出校时间 如：2020-09-25" class="form-control">
                    </td>
                    <td width="100px" align="center" class="flet-col1">预计返校时间</td>
                    <td >
                        <input type="text" id='test2' name="plannedBack" placeholder="返校时间 如：2020-09-25" class="form-control">
                    </td>
                </tr>

                <!--预计返校时间-->
<%--                <tr height="55px">--%>
<%--                    <td width="100px" align="center">预计返校时间</td>--%>
<%--                    <td colspan="3">--%>
<%--                        <input type="text" id='test2' name="plannedBack" placeholder="返校时间 如：2020-09-25" class="form-control">--%>
<%--                    </td>--%>
<%--                </tr>--%>

                <!--外出地点-->
                <tr height="55px" align="center">
                    <td width="100px" class="flet-col1">外出地点</td>
                    <td colspan="3" width="600px">
                        <font class="addr2">省</font>
                        <select id="prov" name="prov" class="form-control"></select>
                        <font class="addr2">市</font>
                        <select id="city" name="city" class="form-control"></select>
                        <font class="addr2">区</font>
                        <select id="area" name="area" class="form-control"></select>
                        <input type="text" placeholder="详细地址" name="addr" style="width: 300px;" height="30px" class="addr form-control"/>
                    </td>
                </tr>
            </table>
            <button type="button" class="btn btn-primary " id="outBtn" style="margin-left: 460px;margin-top: 50px;">提交</button>
            <button type="button" class="btn btn-primary " style="margin-left: 90px;margin-top: 50px;">保存</button>
        </form>
    </div>
</div>


<script>
    laydate.render({
        elem: '#test1',
        type: 'datetime'
    });
    laydate.render({
        elem: '#test2',
        type: 'datetime'
    });
</script>

<script src="js/jquery.js"></script>
<script>
    $(document).ready(function(){
        var addressInfo = null;
        var cityInfo = null;
        $.ajax({
            url:"addr.json",
            type: "get",
            dataType: "json",
            success: function(data){
                //保存省份信息
                addressInfo = data;
                //将省份的name添加到prov下拉框中
                $.each(data,function(index,item){
                    $("<option>").html(item.name).val(item.name).appendTo("#prov")
                })
            }
        })

        $("#prov").change(function(){
            //获得当前下拉框的内容
            var add1 = $(this).val();
            //遍历所有的省份
            $.each(addressInfo,function(index,item){
                var add2 = item.name;
                //如果与下拉框中选择的省份相同
                if(add1 == add2){
                    //保存城市信息
                    cityInfo = item.city;
                    //清空城市下拉框列表
                    $("#city").empty();
                    //循环当前省份的城市信息
                    $.each(item.city,function(index,item){
                        //将城市的name添加到city下拉框中
                        $("<option>").html(item.name).val(item.name).appendTo("#city")
                    })
                }
            })

            //自动触发city的下拉框的change时间
            $("#city").trigger("change");
        })

        $("#city").change(function(){
            //获得当前下拉框的内容
            var add1 = $(this).val();
            //遍历所有的城市信息
            $.each(cityInfo,function(index,item){
                //获得城市的名字
                var add2 = item.name;
                //如果与下拉框中选择的城市相同
                if(add1 == add2){
                    //将地区下拉框清空
                    $("#area").empty();
                    //遍历地区信息
                    $.each(item.area,function(index,item){
                        //将地区的信息加入到下拉框中
                        $("<option>").html(item).val(item).appendTo("#area")
                    })
                }
            })
        })
    })
</script>

<script type="text/javascript">
    $("#outBtn").click(function (){
        <%
        System.out.println("clicked!");
        %>
        $("#outForm").submit();
    })
</script>

</body>
</html>
