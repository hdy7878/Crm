<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/23
  Time: 11:46
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
                oldPassWord: {
                    required:true,
                    remote:{
                        type:"POST",
                        url:"${pageContext.request.contextPath}/CheckOldPassWord.action",
                        data:{
                            oldPassWord:function(){
                                return $("#oldPassWord").val();
                            }
                        }
                    }
                },
                newPassWord:{
                    required:true,
                    minlength:3,
                },
                checkPassWord:{
                    required:true,
                    minlength:3,
                    equalTo:"#newPassWord"
                }

            },
            messages: {
                oldPassWord: {
                    required: "原密码不能为空",
                    remote:"请输入正确的原密码"
                },
                newPassWord: {
                    required: "请输入新密码",
                    minlength:"密码长度不能小于3个数"
                },
                checkPassWord: {
                    required: "请再次输入新密码",
                    minlength:"密码长度不能小于三个数",
                    equalTo:"两次密码输入不一致"
                },
            }
        });

    });
    $.validator.setDefaults({
        submitHandler: function(form) {
            form.submit();
        }
    });
    $(function () {
        $("#ChangePassword").click(function () {
            if ($("#signupForm").valid()==true){
            var oldPassWord=$("input[name='oldPassWord']").val();
            var newPassWord=$("input[name='newPassWord']").val();
            var checkPassWord=$("input[name='checkPassWord']").val();
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/ChangeMessage.action",
                data:{"oldPassword":oldPassWord,
                    "newPassword":newPassWord
                },
                dataType:"json",
                success:function (data) {
                    console.log(data);
                    if(data.code==200){
                        alert(data.msg);
                        window.parent.location.href="loginOut.action"
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
<p style="text-align: center;font-size: 35px;color: white">修改密码页面</p>
<br/>
<form id="signupForm">
    <div>
        <label for="oldPassWord"></label>
        <br/>
        <input id="oldPassWord" type="password" class="layui-input" name="oldPassWord" placeholder="请输入原密码">
    </div>
    <div >
        <label for="newPassWord"></label>
        <br/>
        <input id="newPassWord" type="password" class="layui-input" name="newPassWord" placeholder="请输入新密码">
    </div>
    <div >
        <label for="checkPassWord"></label>
        <br/>
        <input id="checkPassWord" type="password" class="layui-input" name="checkPassWord" placeholder="确认输入新密码">
    </div>
    <div>
        <button id="ChangePassword" type="button"  class="layui-btn" style="background-color: #6A6AFF">确认修改</button>
    </div>
</form>
</body>
</html>
