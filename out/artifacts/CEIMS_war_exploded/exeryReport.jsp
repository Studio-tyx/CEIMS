<%@ page import="edu.njust.entity.User" %>
<%@ page import="edu.njust.entity.Admin" %>
<%@ page import="sun.font.Script" %>
<%@ page import="edu.njust.entity.EverydayReport" %>
<%@ page import="edu.njust.service.EverydayReportService" %><%--
  Created by IntelliJ IDEA.
  User: 14948
  Date: 2020/9/11
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>每日申报</title>
    <link rel="stylesheet" type="text/css" href="css/everyReport.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
		
    <script src="js/jquery.js"></script>
    <script src="addr.json"></script>
    <style>
        select {
            width: 70px;
            height: 30px;
        }
    </style>
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
	EverydayReportService everydayReportService=new EverydayReportService();
	if(everydayReportService.hasSubmitEveryReport(user.getID())){
%>
<script type="text/javascript">
	alert("您今日已提交每日申报！");
	document.location.href="userIndex.jsp";
</script>
<%
}else
%>
	<div class="nav flex">
			<div class="light-mode  nav-background nav-with nav-title ">
				<div>
					<div align="center" class="title" style="font-size: 18px;">南京理工大学</div>
					<br />
					<div align="center" class="title" style="font-size: 18px;">疫情信息管理平台</div>

					<ul class="menu li-space">
						<br />
						<a class="menu-item active" href="exeryReport.jsp"><i class="icon fas fa-user icon-space"></i>每日信息申报</a>
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

			<!--<h2 class="in-title">每日信息申报</h2>-->

			<form class="all" method="post" action="/CEIMS/servlet/EveryServlet" id="everyForm">
				<table border="1" cellspacing="0" class="table-hover everTable" style="margin-left: 70px;">
					<tr class="">
						<td colspan="4" align="center" class="everyTitle">基本信息</td>
					</tr>
					<tr>
						<td class="flet-col" align="center">姓名</td>
						<td width="150px">
							<%=user.getName()%>
						</td>
						<td class="flet-col" align="center">ID</td>
						<td width="390px">
							<%=user.getID()%>
						</td>
					</tr>
					<tr>
						<td class="flet-col" align="center">学院</td>
						<td colspan="3">
							<%=user.getDepartment()%>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="everyTitle" align="center">填报信息</td>
					</tr>
					<tr>
						<td class="flet-col" align="center">体温</td>
						<td>
							<input type="text" placeholder="请输入体温" name="temperature" class="temperature form-control" />
						</td>
						<td class="flet-col" align="center">电话</td>
						<td>
							<input type="tel" placeholder="请输入电话" name="tele" class="temperature form-control" />
						</td>
					</tr>
					<tr>
						<td class="flet-col" align="center">地址</td>
						<td colspan="3">
							<font class="addr2">省</font>
							<select id="prov" name="prov" class="form-control everyAddr1"></select>
							<font class="addr2">市</font>
							<select id="city" name="city" class="form-control everyAddr1"></select>
							<font class="addr2">区</font>
							<select id="area" name="area" class="form-control everyAddr1"></select>
							<input type="text" placeholder="详细地址" name="addr" class="addr form-control everyAddr" />
						</td>
					</tr>
					<tr>
						<td class="msg" align="center">留言</td>
						<td colspan="3">
							<textarea id="message" name="message" placeholder="你有任何需求都可以联系我们" class="form-control everymsg"></textarea>
						</td>
					</tr>
				</table>
				<button type="button" class="btn btn-primary submit2 " id="submitBtn" style="margin-left: 125px;">提交</button>
				<button type="button" class="btn btn-primary save2">保存</button>
			</form>

		</div>
		<!--主体-->

		<script src="js/script.js"></script>
		<script src="js/jquery.js"></script>
		<script>
			$(document).ready(function() {
				var addressInfo = null;
				var cityInfo = null;
				$.ajax({
					url: "addr.json",
					type: "get",
					dataType: "json",
					success: function(data) {
						//保存省份信息
						addressInfo = data;
						//将省份的name添加到prov下拉框中
						$.each(data, function(index, item) {
							$("<option>").html(item.name).val(item.name).appendTo("#prov")
						})
					}
				})

				$("#prov").change(function() {
					//获得当前下拉框的内容
					var add1 = $(this).val();
					//遍历所有的省份
					$.each(addressInfo, function(index, item) {
						var add2 = item.name;
						//如果与下拉框中选择的省份相同
						if(add1 == add2) {
							//保存城市信息
							cityInfo = item.city;
							//清空城市下拉框列表
							$("#city").empty();
							//循环当前省份的城市信息
							$.each(item.city, function(index, item) {
								//将城市的name添加到city下拉框中
								$("<option>").html(item.name).val(item.name).appendTo("#city")
							})
						}
					})

					//自动触发city的下拉框的change时间
					$("#city").trigger("change");
				})

				$("#city").change(function() {
					//获得当前下拉框的内容
					var add1 = $(this).val();
					//遍历所有的城市信息
					$.each(cityInfo, function(index, item) {
						//获得城市的名字
						var add2 = item.name;
						//如果与下拉框中选择的城市相同
						if(add1 == add2) {
							//将地区下拉框清空
							$("#area").empty();
							//遍历地区信息
							$.each(item.area, function(index, item) {
								//将地区的信息加入到下拉框中
								$("<option>").html(item).val(item).appendTo("#area")
							})
						}
					})
				})
			})
		</script>
		<script type="text/javascript">
			$("#submitBtn").click(function() {
				$("#everyForm").submit();
			})
		</script>
</body>
</html>
