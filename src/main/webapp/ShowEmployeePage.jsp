<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/23
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
</head>
<style>
    .layui-table-box{height:77.3%}
</style>
<body>

    <p>分页查看所有专员</p>

<div style="margin:10px;">
    <input type="text" class='layui-input' name='ename' placeholder="请输入员工名称" style="width:200px;display:inline-block;">
    <input type="button" onclick="findEmployee()" class="layui-btn" value="搜索">

</div>
<table id="showEmp" lay-filter="test" class="layui-table-box"></table>
<script>
    $(function(){
        findEmployee();
    })
    function findEmployee() {
        var ename = $("input[name='ename']").val();
        layui.use('table', function () {
            var table = layui.table;
            table.render({
                elem: '#showEmp',
                even: true,
                totalRow: true,
                url: 'ShowEmployeePage.action',
                page: true,
                limits: [3, 5, 7],
                method: 'post',
                where: {"ename": ename},
                cols: [[
                    {field: 'empid', title: '员工id', width: 120, sort: true, fixed: 'left', align: 'center'},
                    {field: 'username', title: '用户名', width: 120, align: 'center'},
                    {field: 'password', title: '密码', width: 330, align: 'center'},
                    {field: 'tel', title: '联系方式', width: 140, align: 'center'},
                    {field: 'ename', title: '员工姓名', width: 190, align: 'center'},
                    {field: 'email', title: '电子邮箱', width: 200, align: 'center'}
                ]]
            })
        })
    }
</script>
</body>
</html>
