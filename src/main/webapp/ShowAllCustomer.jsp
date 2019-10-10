<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/17
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .error{
        color: red;
    }
</style>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css " rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap-table.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/js/locale/bootstrap-table-zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.js"></script>

<script type="text/javascript">
    /*删除客户*/
    function subCus(cid) {
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/SubCustomer.action",
            data:{"cid":cid},
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
/*修改客户赋值*/
    function updateCus(cid) {
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/UpdateCustomerById.action",
            data:{"cid":cid},
            dateType:"json",
            success:function (data) {
                console.log(data);
                if(data.code==200){
                    /*赋值*/
                    $("input[name='cid']").val(data.customer.cid);
                    $("input[name='cusname']").val(data.customer.cusname);
                    $("input[name='address']").val(data.customer.address);
                    $("input[name='contact']").val(data.customer.contact);
                    $("input[name='tel']").val(data.customer.tel);
                    $("input[name='email']").val(data.customer.email);
                }else{
                    alert(data.msg);
                }
            }
        })
    }
    /*表单验证*/
    $().ready(function() {
        $("#signupForm").validate({
            debug:true,
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
    /*修改*/
    $(function () {
        $("#updateBtn").click(function () {
            var cid=$("input[name='cid']").val();
            var cusname=$("input[name='cusname']").val();
            var address=$("input[name='address']").val();
            var contact=$("input[name='contact']").val();
            var tel=$("input[name='tel']").val();
            var email=$("input[name='email']").val();
            if ($("#signupForm").valid()==true){
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/UpdateCustomer.action",
                data:{
                    "cid":cid,
                    "cusname":cusname,
                    "address":address,
                    "contact":contact,
                    "tel":tel,
                    "email":email
                },
                dataType:"json",
                success:function (data) {
                    console.log(data);
                    if(data.code==200){
                        alert(data.msg);
                        $('#myModal').modal('hide');
                        $("table tr").each(function () {
                            if($(this).find("td").eq(0).text()==cid){
                                $(this).find("td").eq(1).text(cusname);
                                $(this).find("td").eq(2).text(address);
                                $(this).find("td").eq(3).text(contact);
                                $(this).find("td").eq(4).text(tel);
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
    <p>所有客户</p>
</div>
<a class="btn btn-block btn-info" href="downExcel.action">一键下载客户信息</a>
</br>
<table class="layui-table" border="1px">
    <tr>
        <td>客户id</td>
        <td>客户名字</td>
        <td>地址</td>
        <td>联系人</td>
        <td>联系方式</td>
        <td>邮箱</td>
        <td>操作</td>
    </tr>

    <c:forEach items="${list}" var="q">
        <tr>
            <td>${q.cid}</td>
            <td>${q.cusname}</td>
            <td>${q.address}</td>
            <td>${q.contact}</td>
            <td>${q.tel}</td>
            <td>${q.email}</td>
            <td><a onclick="subCus(${q.cid})" type="button"
                   class="layui-btn" style="background-color: #f15555">删除客户信息</a>&nbsp;


                <a
                    onclick="updateCus(${q.cid})" data-toggle="modal"
                    data-target="#myModal" type="button" class="layui-btn" style="background-color: #2bafd7">
                修改客户信息 </a></td>
        </tr>
    </c:forEach>
</table>



<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
            aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="signupForm">
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <span style="font-size: 30px; color: black">修改客户信息页面</span>
                    <h4 class="modal-title" id="myModalLabel">
                        要修改的客户id</br> <input type="text" class="form-control" align="center"
                                             name="cid" readonly="readonly" />
                    </h4>
                </div>
                <div class="modal-body">
                    请输入要修改的客户名字</br> <input id="cusname" type="text" class="form-control" name="cusname" required/>
                </div>
                <div class="modal-body">
                    请输入要修改的地址</br> <input id="address" type="text" class="form-control" name="address" />
                </div>
                <div class="modal-body">
                    请输入修改的联系人</br> <input id="contact" type="text" class="form-control" name="contact" />
                </div>
                <div class="modal-body">
                    请输入修改的联系方式</br> <input id="tel" type="text" class="form-control" name="tel" />
                </div>
                <div class="modal-body">
                    请输入修改的邮箱</br> <input id="email" type="text" class="form-control" name="email" />
                </div>
                <div class="modal-footer form-inline">
                    <button type="button" class="btn btn-danger btn-lg"
                            data-dismiss="modal">关闭</button>

                    <button id="updateBtn" type="button"
                            class="btn btn-success btn-lg">修改</button>

                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
</body>
</html>
