<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/16
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link  href="${pageContext.request.contextPath}/css/bootstrap.min.css " rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap-table.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/js/locale/bootstrap-table-zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.js"></script>
<style>
    .error{
        color: red;
    }
</style>
<script type="text/javascript">
    $().ready(function() {
        $("#signupForm").validate({
            debug:true,
            rules: {
                tel: {
                    required: true,
                    tel: true
                },
                ename:"required",
                email: {
                    required: true,
                    email: true
                },
            },
            messages: {
                tel: {
                    required: "*请输入联系方式",
                    tel: "*长度为11的数字"
                },
                ename:{
                    required:"*请输入你的大名"
                },
                email: {
                    required: "请输入一个邮箱",
                    email: "必须有@和."
                }
            }
        });

    });
    $.validator.setDefaults({
        submitHandler: function(form) {
            form.submit();
        }
    });
    function subEmp(empid) {
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/DeleteEmployee.action",
            data:{"empid":empid},
            dataType:"json",
            success:function (data) {
                console.log(data);
                if(data.code==200){
                    alert(data.msg);
                    location.href="ShowAllEmployee.action"
                }else{
                    alert(data.msg);
                }
            }
        })

    }
    function updateEmp(empid) {

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/UpdateEmployeeById.action",
            data:{"empid":empid},
            dateType:"json",
            success:function (data) {
                console.log(data);
                if(data.code==200){
                    /*赋值*/
                    $("input[name='empid']").val(data.employee[0].empid);
                    $("input[name='username']").val(data.employee[0].username);
                    $("input[name='password']").val(data.employee[0].password);
                    $("input[name='tel']").val(data.employee[0].tel);
                    $("input[name='ename']").val(data.employee[0].ename);
                    $("input[name='email']").val(data.employee[0].email);
                }else{
                    alert(data.msg);
                }
            }
        })
    }
    $(function () {
        $("#updateBtn").click(function () {
            var empid=$("input[name='empid']").val();
            var username=$("input[name='username']").val();
            var password=$("input[name='password']").val();
            var tel=$("input[name='tel']").val();
            var ename=$("input[name='ename']").val();
            var email=$("input[name='email']").val();
            if ($("#signupForm").valid()==true){
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/UpdateEmployee.action",
                data:{
                    "empid":empid,
                    "username":username,
                    "password":password,
                    "tel":tel,
                    "ename":ename,
                    "email":email
                },
                dataType:"json",
                success:function (data) {
                    console.log(data);
                    if(data.code==200){
                        alert(data.msg);
                        $('#myModal').modal('hide');
                        $("table tr").each(function () {
                            if($(this).find("td").eq(0).text()==empid){
                                $(this).find("td").eq(1).text(username);
                                $(this).find("td").eq(2).text(password);
                                $(this).find("td").eq(3).text(tel);
                                $(this).find("td").eq(4).text(ename);
                                $(this).find("td").eq(5).text(email);
                            }
                        })
                    }else{
                        alert(data.msg);
                    }
                }
            })
            }
        })
    })
</script>
<body>
<div class="layui-elem-quote">
    <p>所有专员</p>
</div>

<table class="layui-table" width="85%" >
    <tr>
        <td>员工id</td>
        <td>用户名</td>
        <td>密码</td>
        <td>员工电话</td>
        <td>员工名字</td>
        <td>员工邮箱</td>
        <td>操作</td>
    </tr>

    <c:forEach items="${list}" var="q">
        <tr>
            <td>${q.empid}</td>
            <td>${q.username}</td>
            <td>${q.password}</td>
            <td>${q.tel}</td>
            <td>${q.ename}</td>
            <td>${q.email}</td>
            <td>
                <a onclick="subEmp(${q.empid})" class="layui-btn" style="background-color: #f15555">删除员工</a>
                <a onclick="updateEmp(${q.empid})" data-toggle="modal" data-target="#myModal" class="layui-btn" style="background-color: #2bafd7">修改员工信息</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="signupForm">
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <span style="font-size: 30px;color: black">修改员工页面</span>
                    <h4 class="modal-title" id="myModalLabel">
                        要修改的员工id</br>
                        <input id="empid" type="text"  class="form-control" align="center"  name="empid" readonly="readonly" />
                    </h4>
                </div>
                <div class="modal-body">
                    用户名不能被修改</br>
                    <input id="username" type="text"  class="form-control"  name="username"  readonly="readonly"/>
                </div>
                <div class="modal-body">
                    密码请本人前往信息管理中心修改</br>
                    <input id="password"  type="text"  class="form-control"  name="password" readonly="readonly"/>
                </div>
                <div class="modal-body">
                    请输入修改的联系方式</br>
                    <input id="tel" type="text"  class="form-control"  name="tel" />
                </div>
                <div class="modal-body">
                    请修改的要留的名字</br>
                    <input id="ename" type="text"  class="form-control"  name="ename" />
                </div>
                <div class="modal-body">
                    请修改电子邮箱</br>
                    <input id="email" type="text"  class="form-control"  name="email" />
                </div>
                <div class="modal-footer form-inline" >
                    <button type="button" class="btn btn-danger btn-lg" data-dismiss="modal">关闭
                    </button>

                    <button id="updateBtn" type="button"  class="btn btn-info btn-lg">修改</button>

                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>