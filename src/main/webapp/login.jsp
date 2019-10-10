<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/8/14
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
</head>

<style>
  body {padding: 40px;padding-top:200px;background:url("img/xingxing.gif") repeat center;}
  div {text-align: center;}
  input {margin: 10px;padding: 10px;}
  .layui-input{width: 400px;display: inline-block;}
  .error{
      color: red;
  }

</style>
<%--<embed src="css/1.mp3"  hidden="true"  autostart >
</embed>--%>
<script type="text/javascript">
    $(function () {
        $("#ChangeWord").click(function(){

            var nowWord = $(this).text();

            // 中文改英文
            if(nowWord.indexOf("系统") != -1){
                $(this).text("Customer Management System");
            }
            // 英文改日语
            if(nowWord.indexOf("System") != -1){
                $(this).text("顧客管理システム");
            }

            // 日语改韩语
            if(nowWord.indexOf("システム") != -1){
                $(this).text("고객 관리 시스템");
            }

            // 韩语改中文
            if(nowWord.indexOf("시스템") != -1){
                $(this).text("客户管理系统");
            }
        });
    })
    $().ready(function() {
        $("#signupForm").validate({
            debug:true,
            rules: {
                registUsername:{
                    required:true,
                    remote:{
                        type:"POST",
                        url:"${pageContext.request.contextPath}/CheckUsername.action",
                        data:{
                            registUsername:function(){
                                return $("#registUsername").val();
                            }
                        }
                    }
                },
                registPassword:{
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
                }
            },
            messages: {
                registUsername: {
                    required: "*请输入用户名",
                    remote:"*用户名已存在"
                },
                registPassword: {
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
    $("#denglu").click(function () {
        var username = $("input[name='loginUsername']").val();
        var password = $("input[name='loginPassword']").val();
        var remember = $("input[name='remember']").is(':checked');

    $.ajax({
        type:"POST",
        url:"${pageContext.request.contextPath}/login.action",
        data:{"username":username,"password":password,"remember":remember},
        ContentType :"application/json;charset=UTF-8",
        dataType:"json",
        success:function (data) {
            console.log(data);
            if(data.code==200){
              location.href="toMain.action"
            }else{
                alert(data.msg);
            }
        }
    })
    });

    $("#registBtn").click(function () {
       var username=$("input[name='registUsername']").val();
        var password=$("input[name='registPassword']").val();
        var tel=$("input[name='tel']").val();
        var ename=$("input[name='ename']").val();
        var email=$("input[name='email']").val();
        var roleid=$("select[name='roleid']").val();

        if ($("#signupForm").valid()==true){
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/regist.action",
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
                    location.href="toLogin.action"
                }else{
                    alert(data.msg);
                }
            }

        })
        }
    });
});

</script>

<body>
<form>
<center><p style=" font-size: 45px; color: darksalmon" id="ChangeWord" >客户管理系统</p></center>
<br/>

    <div >

  <input type="text" class="layui-input" name="loginUsername" placeholder="请输入用户名">
</div>
<div >
  <input type="password" class="layui-input" name="loginPassword" placeholder="请输入密码">
</div>
<div>
    <a id="denglu" class="btn btn-default mybtn" style="background-color: snow"><span class="glyphicon glyphicon-log-out"></span>&nbsp;登录</a>
</div>
    <br/>
    <div>
   <font style="font-size: 23px;color: white">记住我：</font> <input name="remember" type="checkbox"  style="zoom: 160%">
    </div>
</form>
<br/>
<!-- 模态框（Modal） -->
<center><a href="#" data-toggle="modal" data-target="#myModal" >
    <font style="color: white;font-size: 20px" >还没有用户嘛？点击此处注册一个</font>
</a></center>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form id="signupForm">
      <div class="modal-body">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <span style="font-size: 30px;color: black">注册页面</span>
        <h4 class="modal-title" id="myModalLabel">
          请输入要注册的用户名</br>
          <input id="registUsername" type="text"  class="form-control" align="center"  name="registUsername" />
        </h4>
      </div>
      <div class="modal-body">
        请输入要输入的密码</br>
        <input id="registPassword" type="password"  class="form-control"  name="registPassword" />
      </div>
          <div class="modal-body">
              请输入你的联系方式</br>
              <input id="tel" type="text"  class="form-control"  name="tel" />
          </div>
          <div class="modal-body">
              请输入你的大名</br>
              <input id="ename" type="text"  class="form-control"  name="ename" />
          </div>
          <div class="modal-body">
              请输入你的邮箱</br>
              <input id="email" type="text"  class="form-control"  name="email" />
          </div>
          <div class="modal-body" hidden>
              请选择用户角色</br>
              <select name="roleid" class="form-control">
                  <option value="2">合作专员</option>
              </select>
          </div>
      <div class="modal-footer form-inline" >
        <button type="button" class="btn btn-danger btn-lg" data-dismiss="modal">关闭
        </button>

        <button id="registBtn" type="button"  class="btn btn-success btn-lg">注册</button>

      </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>
</body>
</html>

