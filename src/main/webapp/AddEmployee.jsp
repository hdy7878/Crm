<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/18
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="layui/layui.js"></script>
</head>
<style>
    input {margin: 10px;padding: 10px;}
    .layui-input{width: 400px;display: inline-block;}
    .error{
        color: red;
    }
    #signupForm {text-align:center;}
    .form-control{width:300px;display:inline-block;}
</style>
<script type="text/javascript">
    $().ready(function() {
        $("#signupForm").validate({
            debug:true,
            errorPlacement: function(error, element) {
                $( element )
                    .closest( "form" )
                    .find( "label[for='" + element.attr( "id" ) + "']" )
                    .append( error );
            },
            rules: {
                username:{
                    required:true,
                    remote:{
                        type:"POST",
                        url:"${pageContext.request.contextPath}/Checkusername.action",
                        data:{
                            username:function(){
                                return $("#username").val();
                            }
                        }
                    }
                },
                password:{
                    required:true,
                    minlength:3
                },
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
                username: {
                    required: "*请输入用户名",
                    remote:"*用户名已存在"
                },
                password: {
                    required: "*请输入密码",
                    minlength:"*密码长度不能小于3个数"
                },
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

    $(function () {

        $("#AddEmp").click(function () {
            var username=$("input[name='username']").val();
            var password=$("input[name='password']").val();
            var tel=$("input[name='tel']").val();
            var ename=$("input[name='ename']").val();
            var email=$("input[name='email']").val();
            var roleid=$("input[name='roleid']").val();
            if ($("#signupForm").valid()==true){
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/InsertEmployee.action",
                data:{"username":username,
                    "password":password,
                    "tel":tel,
                    "ename":ename,
                    "email":email,
                    "roleid":roleid
                },
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
        });

    });
</script>
<body background="img/xingxing.gif">
<p style="text-align: center;font-size: 35px;color: white">增加员工页面</p>
<br/>
<form id="signupForm">
    <div >
        <label for="username"></label>
        <br/>
        <input id="username" type="text" class="form-control" name="username" placeholder="请输入用户名">
    </div>

    <div >
        <label for="password"></label>
        <br/>
        <input id="password" type="password" class="form-control" name="password" placeholder="请输入密码">
    </div>

    <div >
        <label for="tel"></label>
        <br/>
        <input id="tel" type="text" class="form-control" name="tel" placeholder="请输入联系方式">
    </div>

    <div >
       <label for="ename"></label>
        <br/>
        <input id="ename" type="text" class="form-control" name="ename" placeholder="请输入员工名字">
    </div>

    <div >
        <label for="email"></label>
        <br/>
        <input id="email" type="text" class="form-control" name="email" placeholder="请输入邮箱">
    </div>

    <div hidden>
        <input type="text" class="form-control" name="roleid"  value="2" >
    </div>
    <div>
        <button id="AddEmp" type="button"  class="layui-btn" style="background-color: #6A6AFF">确认增加</button>
    </div>
</form>
</body>
</html>