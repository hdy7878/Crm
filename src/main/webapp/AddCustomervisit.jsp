<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/18
  Time: 20:07
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
    textarea{margin: 10px;padding: 10px;}
    input {margin: 10px;padding: 10px;}
    .layui-input{width: 400px;display: inline-block;}
    select{margin: 10px;padding: 10px;}
    .layui-select{width: 400px;}
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
               content:{
                    required:true,
                    minlength:5
                },
               date: "required"

            },
            messages: {

                content: {
                    required: "*请输入访问内容",
                    minlength:"*访问内容长度不能小于5个数"
                },

                date:{
                    required:"*请输入访问时间"
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
        $("#AddCustomervisit").click(function () {
            var cid=$("select[name='cid']").val();
            var empid=$("select[name='empid']").val();
            var content=$("textarea[name='content']").val();
            var date=$("input[name='date']").val();
            if ($("#signupForm").valid()==true){
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/AddCustomervisit.action",
                data:{
                    "cid":cid,
                    "empid":empid,
                    "content":content,
                    "date":date
                },
                dataType:"json",
                success:function (data) {
                    console.log(data);
                    if(data.code==200) {
                        alert(data.msg);
                        location.href="ShowAllCustomervisit.action"
                    }else {
                        alert(data.msg);
                    }

                }

            })
            }
        })
    })
</script>
<body background="img/xingxing.gif">
<p style="text-align: center;font-size: 35px;color: white">增加客户页面</p>
<br/>
<form id="signupForm">
    <div>
        <select class="layui-select" name="cid" required>
            <option value="">请选择</option>
            <c:forEach items="${listCustomer}" var="c">
                <option value="${c.cid}">${c.cusname}</option>
            </c:forEach>
        </select>
    </div>
    <br/>
    <div>
        <select class="layui-select" name="empid" required>
            <option value="">请选择</option>
            <c:forEach items="${listEmployee}" var="e">
                <option value="${e.empid}">${e.ename}</option>
            </c:forEach>
        </select>
    </div>
    <br/>
    <div >
        <label for="content"></label>
        <br/>
        <textarea id="content" class="layui-input" name="content" placeholder="请输入访问内容"></textarea>
    </div>
    <br/>
    <div >
        <label for="date"></label>
        <br/>
        <input id="date" type="date" class="layui-input" name="date" placeholder="请输入访问时间">
    </div>
    <div>
        <button id="AddCustomervisit" type="button"  class="layui-btn" style="background-color: #6A6AFF">确认增加</button>
    </div>
</form>
</body>
</html>
