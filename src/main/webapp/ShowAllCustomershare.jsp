<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/18
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
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
        color:red;
    }
</style>
<script type="text/javascript">
    function subCustomershare(id,cid) {
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/SubCustomershare.action",
            data:{"id":id,"cid":cid},
            dataType:"json",
            success:function (data) {
                console.log(data);
                if(data.code==200){
                    alert(data.msg);
                    location.href="ShowAllCustomershare.action"
                }else{
                    alert(data.msg);
                }
            }
        })
    }
    /*表单验证*/
    $().ready(function() {
        $("#signupForm").validate({
            debug: true
        })
    })
    $.validator.setDefaults({
        submitHandler: function(form) {
            form.submit();
        }
    });
    function updateCustomershare(id) {
        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/UpdateCustomershare.action",
            data:{"id":id},
            dataType:"json",
            success:function (data) {
                $("#cid").empty();
                $("#empid").empty();
                console.log(data);
                $("input[name='id']").val(data.cusShare.id);
                for(var i in data.listCustomer){
                    var cus=data.listCustomer[i];
                    if(cus.cid==data.cusShare.cus.cid){
                        $("#cid").append("<option value='"+cus.cid+"'selected='selected'>"+cus.cusname+"</option>")
                    }else{
                        $("#cid").append("<option value='"+cus.cid+"' selecte>"+cus.cusname+"</option>")
                    }
                }
                for(var i in data.listEmployee){
                    var emp=data.listEmployee[i];
                    if(emp.empid==data.cusShare.emp.empid){
                        $("#empid").append("<option value='"+emp.empid+"'selected='selected'>"+emp.ename+"</option>")
                    }else{
                        $("#empid").append("<option value='"+emp.empid+"' selecte>"+emp.ename+"</option>")
                    }
                }

            }
        });
    }
    $(function () {
        $("#updateBtn").click(function () {
            var id=$("input[name='id']").val();
            var cid=$("#cid").val();
            var empid=$("#empid").val();
            var cusname = $("#cid").find("option:selected").text();
            var ename = $("#empid").find("option:selected").text();
          if($("#signupForm").valid()==true){
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/UpdateCusShare.action",
                data:{
                    "id":id,
                    "cid":cid,
                    "empid":empid,

                },
                dataType:"json",
                success:function (data) {
                    console.log(data);
                    if(data.code==200){
                        alert(data.msg);
                        $('#myModal').modal('hide');
                        $("table tr").each(function () {
                            if($(this).find("td").eq(0).text()==id){
                                $(this).find("td").eq(1).text(cusname);
                                $(this).find("td").eq(2).text(ename);
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
    <p>所有客户分享信息</p>
</div>
<table class="layui-table" width="85%" border="1px">
    <tr>
        <td>id</td>
        <td>客户名字</td>
        <td>负责员工名字</td>
        <td>操作</td>
    </tr>

    <c:forEach items="${list}" var="q">
        <tr>
            <td>${q.id}</td>
            <td>${q.cus.cusname}</td>
            <td>${q.emp.ename}</td>
            <td>
                <a onclick="subCustomershare(${q.id},${q.cus.cid})" class="layui-btn" style="background-color: #f15555">删除共享信息</a>&nbsp;
                <a onclick="updateCustomershare(${q.id})" data-toggle="modal" data-target="#myModal" type="button" class="layui-btn" style="background-color: #2bafd7">客户转移</a>
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
                    <span style="font-size: 30px;color: black">客户转移页面</span>
                    <h4 class="modal-title" id="myModalLabel">
                        要修改的共享记录id</br>
                        <input type="text"  class="form-control" align="center"  name="id" readonly="readonly" />
                    </h4>
                </div>
                <div class="modal-body" hidden>
                    请选择要修改的客户名字</br>
                    <select class="form-control" id="cid" required>
                        <option value="">请选择</option>
                    </select>
                </div>
                <div class="modal-body">
                    请选择要修改接待员工名字</br>
                    <select class="form-control" id="empid" required>
                        <option value="">请选择</option>
                    </select>
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
