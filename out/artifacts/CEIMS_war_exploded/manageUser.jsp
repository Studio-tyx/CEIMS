<%@ page import="edu.njust.entity.Admin" %>
<%@ page import="edu.njust.service.UserService" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.njust.entity.User" %><%--
  Created by IntelliJ IDEA.
  User:
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>

    <link rel="stylesheet" href="css/massage.css">
    <link rel="stylesheet" href="css/layui/css/layui.css">
    <link rel="stylesheet" href="css/icheck/minimal/red.css">
    <link rel="stylesheet" href="css/everyReport.css">
    <link rel="stylesheet" href="css/bootstrap-dialog.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script type="text/javascript" src="js/bootstrap-dialog.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
<%
    Object obj=session.getAttribute("admin");
    Admin admin=(Admin)obj;
    UserService userService=new UserService();
    List<User> users=userService.findAllUser();
%>
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
                <a class="menu-item active" href="manageUser.jsp"><i class="icon fas fa-check-square icon-space"></i>账号管理</a>
                <br />
                <a class="menu-item" href="change.jsp"><i class="icon fas fa-compass icon-space"></i>修改数据</a>
                <br />
                <a class="menu-item" href="adminMessage.jsp"><i class="icon fas fa-comment icon-space"></i>回复留言</a>
                <br />
                <a class="menu-item" href="adminIndex.jsp"><i class="icon fas fa-step-backward icon-space"></i>回到首页</a>
            </ul>
        </div>
    </div>

    <div class="layui-layout layui-layout-admin">

        <div class="layui-body">
            <!-- 内容主体区域 -->
            <div style="padding-left: 260px;padding-top:50px;margin-right: 150px;">
                <fieldset class="layui-elem-field">
                    <legend>
                        <font class="in-in-title">
                            用户管理 - 用户列表
                        </font>
                    </legend>
                    <div class="layui-field-box">
                        <form class="layui-form" action="">
                            <div class="layui-form-item" style="text-align:right;">
                                <div class="layui-inline" align="right">
                                    <div class="layui-input-inline">
                                        <input autocomplete="off" class="layui-input input1" placeholder="请输入查询内容" type="text">
                                    </div>
                                </div>
                                <div class="layui-inline" style="text-align:right;">
                                    <div class="layui-input-inline">
                                        <button class="layui-btn">
                                            <i class="layui-icon">&#xe615;</i>
                                            查询
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <hr>
                        <div class="layui-btn-group">
                            <button id="btn_add" class="layui-btn layui-btn-xs">
                                <i class="layui-icon">&#xe654;</i>批量修改密码
                            </button>
                            <%--<button class="layui-btn layui-btn-xs layui-btn-danger dw-batch-delete" dw-url="" id="btn_delete">
                                <i class="layui-icon">&#xe640;</i>删除
                            </button>--%>
                            <button class="layui-btn layui-btn-xs" dw-url="" id="btn_edit">
                                <i class="layui-icon">&#xe640;</i>修改
                            </button>
                        </div>
                        <hr>
                        <%
                        if(users.isEmpty()){
                        %>
                            <p>并无用户！</p>
                        <%
                        }
                        else{
                        %>
                        <table class="layue-table">
                            <colgroup>
                                <%--<col width="70">--%>
                                <col width="150">
                                <col width="160">
                                <col width="700">
                                <col width="150">
                                <col width="100">
                                <%--<col width="150">--%>
                            </colgroup>
                            <thead>
                            <tr>
                               <%-- <th class="selectAll" align="left"><input type="checkbox"></th>--%>
                                <th align="left">ID</th>
                                <th align="left">姓名</th>
                                <th align="left">学院</th>
                                <th align="left">密码</th>
                                <th align="left">管理员</th>
                                <<%--th style="text-align:center;">操作</th>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <form method="post" action="/CEIMS/servlet/UserDeleteServlet" id="deleteForm">
                        <%
                            int countUser=0;
                            User user=null;
                            for(;countUser<users.size();countUser++){
                                user=users.get(countUser);
                        %>
                            <tr>
                                <%--<td><input type="checkbox" name="id2" value="<%=user.getID()%>"></td>--%>
                                <td><font class="cha1"><%=user.getID()%></font></td>
                                <td><font class="cha1"><%=user.getName()%></font></td>
                                <td><font class="cha1"><%=user.getDepartment()%></font></td>
                                <td><font class="cha1"><%=user.getPassword()%></font></td>
                                <td><font class="cha1"><%=new UserService().isAdmin(user)?"是":"否"%></font></td>
                                <%--<td class="text-center">
                                    <div class="layui-btn-group">
                                        &lt;%&ndash;&lt;%&ndash;
                                        <button class="layui-btn layui-btn-xs layui-btn-normal " id="btn_edit">
                                            <i class="layui-icon">&#xe642;</i>编辑
                                        </button>&ndash;%&gt;&lt;%&ndash;&ndash;%&gt;&ndash;%&gt;
                                        <button class="layui-btn layui-btn-xs layui-btn-danger " id="btn_delete" onclick="return delete(<%=user.getID()%>);">
                                            <i class="layui-icon">&#xe640;</i>删除
                                        </button>
                                    </div>
                                </td>--%>
                            </tr>
                        </form>
                        <%
                            }
                            countUser=0;
                        }
                        %>
                            </tbody>
                        </table>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>

</div>

<%--<script type="text/javascript">
function delete(){

}
</script>--%>


<!--添加用户-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">用户管理 - 新增用户</h4>
            </div>
            <form class="modal-body" method="post" action="/CEIMS/servlet/UserAddServlet" id="addUserForm">
            <%--<div class="modal-body">--%>
                <%--<div class="form-group">
                    &lt;%&ndash;@declare id="txt_parentdepartment"&ndash;%&gt;<label for="txt_parentdepartment">姓名</label>
                    <!--输入密码-->
                    <input type="text" name="name" class="form-control" id="name" placeholder="姓名">
                </div>
                <div class="form-group" >
                    &lt;%&ndash;@declare id="txt_departmentname"&ndash;%&gt;<label for="txt_departmentname">ID</label>
                    <!--输入ID-->
                    <input type="text" name="ID" class="form-control" id="ID" placeholder="用户ID">
                </div>--%>
                <div class="form-group">
                    <%--@declare id="txt_parentdepartment"--%><label for="txt_parentdepartment">密码</label>
                    <!--输入密码-->
                    <input type="text" name="pswd" class="form-control" id="pwsd" placeholder="密码">
                </div>
                <div class="form-group">
                    <%--@declare id="txt_departmentlevel"--%><label for="txt_departmentlevel">学院</label>
                    <!--选择学院-->
                    <select name="department" lay-verify="required" class="form-control">
                        <option value=""></option>
                        <option value="机械工程学院">机械工程学院</option>
                        <option value="化工学院">化工学院</option>
                        <option value="电子工程与光电技术学院">电子工程与光电技术学院</option>
                        <option value="计算机科学与工程学院/人工智能学院">计算机科学与工程学院/人工智能学院</option>
                        <option value="经济管理学院">经济管理学院</option>
                        <option value="能源与动力工程学院">能源与动力工程学院</option>
                        <option value="自动化学院">自动化学院</option>
                        <option value="理学院">理学院</option>
                        <option value="外国语学院">外国语学院</option>
                        <option value="公共事务学院">公共事务学院</option>
                        <option value="材料科学与工程学院/格莱特研究院">材料科学与工程学院/格莱特研究院</option>
                        <option value="环境与生物工程学院">环境与生物工程学院</option>
                        <option value="设计艺术与传媒学院">设计艺术与传媒学院</option>
                        <option value="知识产权学院">知识产权学院</option>
                        <option value="马克思主义学院">马克思主义学院</option>
                        <option value="国际教育学院">国际教育学院</option>
                        <option value="创新创业教育学院">创新创业教育学院</option>
                        <option value="继续教育学院">继续教育学院</option>
                        <option value="中法工程师学院">中法工程师学院</option>
                        <option value="紫金学院">紫金学院</option>
                        <option value="泰州科技学院">泰州科技学院</option>
                    </select>
                </div>
                <%--<div class="form-control">
                    <label for="txt_departmentlevel">管理员</label>
                    <input type="radio" name="demo1" id="temp1" /><label for="temp1">是</label>
                    <input type="radio" name="demo2" id="temp2" /><label for="temp2">否</label>
                </div>--%>
            <%--</div>--%>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span  aria-hidden="true"></span>关闭</button>
                <button type="button" id="btn_submit2" class="btn btn-primary" data-dismiss="modal"><span  aria-hidden="true"></span>提交</button>
            </div>
        </div>
    </div>
</div>

<!--修改用户信息-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
<%--                    <span aria-hidden="true">×</span></button>--%>
                <h4 class="modal-title" id="myModalLabel2">用户管理 - 修改信息</h4>
            </div>
            <form class="modal-body" method="post" action="/CEIMS/servlet/UserModifyServlet" id="modifyUserForm">
                <%--<div class="modal-body">--%>
                <div class="form-group">
                    <%--@declare id="txt_parentdepartment"--%><label for="txt_parentdepartment">姓名</label>
                    <!--输入密码-->
                    <input type="text" name="name" class="form-control" id="name" placeholder="姓名">
                </div>
                <div class="form-group" >
                    <%--@declare id="txt_departmentname"--%><label for="txt_departmentname">ID</label>
                    <!--输入ID-->
                    <input type="text" name="ID" class="form-control" id="ID" placeholder="用户ID">
                </div>
                <div class="form-group">
                    <%--@declare id="txt_parentdepartment"--%><label for="txt_parentdepartment">密码</label>
                    <!--输入密码-->
                    <input type="text" name="pswd" class="form-control" id="pwsd" placeholder="密码">
                </div>
                <div class="form-group">
                    <%--@declare id="txt_departmentlevel"--%><label for="txt_departmentlevel">学院</label>
                    <!--选择学院-->
                    <select name="department" lay-verify="required" class="form-control">
                        <option value=""></option>
                        <option value="机械工程学院">机械工程学院</option>
                        <option value="化工学院">化工学院</option>
                        <option value="电子工程与光电技术学院">电子工程与光电技术学院</option>
                        <option value="计算机科学与工程学院/人工智能学院">计算机科学与工程学院/人工智能学院</option>
                        <option value="经济管理学院">经济管理学院</option>
                        <option value="能源与动力工程学院">能源与动力工程学院</option>
                        <option value="自动化学院">自动化学院</option>
                        <option value="理学院">理学院</option>
                        <option value="外国语学院">外国语学院</option>
                        <option value="公共事务学院">公共事务学院</option>
                        <option value="材料科学与工程学院/格莱特研究院">材料科学与工程学院/格莱特研究院</option>
                        <option value="环境与生物工程学院">环境与生物工程学院</option>
                        <option value="设计艺术与传媒学院">设计艺术与传媒学院</option>
                        <option value="知识产权学院">知识产权学院</option>
                        <option value="马克思主义学院">马克思主义学院</option>
                        <option value="国际教育学院">国际教育学院</option>
                        <option value="创新创业教育学院">创新创业教育学院</option>
                        <option value="继续教育学院">继续教育学院</option>
                        <option value="中法工程师学院">中法工程师学院</option>
                        <option value="紫金学院">紫金学院</option>
                        <option value="泰州科技学院">泰州科技学院</option>
                    </select>
                </div>
                <div class="form-control">
                    <%--@declare id="temp1"--%><%--@declare id="temp2"--%><label for="txt_departmentlevel">管理员</label>
                    <input type="radio" name="demo3" id="temp3" /><label for="temp1">是</label>
                    <input type="radio" name="demo4" id="temp4" /><label for="temp2">否</label>
                </div>
                <%--</div>--%>
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><span  aria-hidden="true"></span>关闭</button>
                <button type="button" id="btn_submit1" class="btn btn-primary" data-dismiss="modal"><span  aria-hidden="true"></span>保存</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="css/layui/layui.js"></script>
<script type="text/javascript" src="css/icheck/icheck.js"></script>
<script type="text/javascript" src="js/dw.js"></script>

<script type="text/javascript" src="js/bootstrap.js"></script>
<script>
    $("#btn_add").click(function() {
        $("#myModalLabel").text("新增");
        $('#myModal').modal();
    });

    $("#btn_edit").click(function() {
        $("#myModalLabel2").text("修改");
        $('#myModal2').modal();
    });
</script>
<script type="text/javascript">
    $("#btn_submit1").click(function (){
        $("#modifyUserForm").submit();
        return;
    });
</script>

<script type="text/javascript">
    $("#btn_submit2").click(function (){
        $("#addUserForm").submit();
        return;
    });
</script>

<script type="text/javascript">
    $("#btn_delete").click(function (){
        $("#deleteForm").submit();
    })
</script>

</body>
</html>
