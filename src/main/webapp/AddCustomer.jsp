<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/17
  Time: 16:07
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
    input {padding: 10px}
    select{padding: 10px;}
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
            errorElement: "span",
            rules: {
                cusname: "required",
                address: "required",
                contact: "required",
                tel: {
                    required: true,
                    tel: true
                },
                email: {
                    required: true,
                    email: true
                },
            },
                messages: {
                    cusname: {
                        required: "请输入客户的名字"
                    },
                    address: {
                        required: "请输入客户地址"
                    },
                    contact: {
                        required: "请输入联系人"
                    },
                    tel: {
                        required: "请输入联系方式",
                        tel: "长度为11的数字"
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
        $("#AddCus").click(function () {

            /*var cusname=$("input[name='cusname']").val();
            var address=$("input[name='address']").val();
            var contact=$("input[name='contact']").val();
            var tel=$("input[name='tel']").val();
            var email=$("input[name='email']").val();
            var empid=$("select[name='empid']").val();*/
            if ($("#signupForm").valid()==true){
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/InsertCustomer.action",
                data:$("#signupForm").serialize(),
                /*{"cusname":cusname,
                    "address":address,
                    "contact":contact,
                    "tel":tel,
                    "email":email,
                    "empid":empid
                },*/
                dataType:"json",
                success:function (data) {
                    console.log(data);
                    if(data.code==200){
                        alert(data.msg);
                        location.href="ShowAllCustomer1.action"
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
  <p style="text-align: center;font-size: 35px;color: white">增加客户页面</p>
  <br/>
<form id="signupForm" class="form">

    <div class="form-group">
        <label for="cusname"></label>
        <br/>

        <input id="cusname" type="text" class="form-control" name="cusname" placeholder="请输入你的名字">
    </div>
    <div class="form-group">
        <label for="address"></label>
        <br/>

        <input id="address" type="text" class="form-control" name="address" placeholder="请输入地址">
    </div>
    <div class="form-group" >
        <label for="contact"></label>
        <br/>
        <input id="contact" type="text" class="form-control" name="contact" placeholder="请输入联系人">
    </div>
    <div class="form-group">
        <label for="tel"></label>
        <br/>
        <input id="tel" type="text" class="form-control" name="tel" placeholder="请输入联系方式">
    </div>
    <div class="form-group">
        <label for="email"></label>
        <br/>
        <input id="email" type="text" class="form-control" name="email" placeholder="请输入邮箱">
    </div>
    <br/>
    <div class="form-group">

        <select  name="empid" class="form-control" required>
            <option value="">请选择</option>
            <c:forEach items="${listEmployee}" var="e">
                <option value="${e.empid}">${e.ename}</option>
            </c:forEach>
        </select>
    </div>
    <div style="">
        <button id="AddCus" type="button"  class="layui-btn" style="background-color: #6A6AFF">确认增加</button>
    </div>

</form>
</body>
</html>
