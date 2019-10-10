<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/23
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="layui/layui.js"></script>
</head>
<body>
<style>
    .layui-table-box{height:77.3%}
</style>
<div class="layui-elem-quote">
    <p>分页查看所有客户共享记录</p>
</div>
<div style="margin:10px;">
    <input type="text" class='layui-input' name='cusname' placeholder="请输入客户名称" style="width:200px;display:inline-block;">
    <input type="button" onclick="findCustomershare()" class="layui-btn" value="搜索">
    <script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
</div>
<table id="showCustomershare" lay-filter="test"></table>
<script type="text/html" id="titleTpl2">
    {{d.emp.ename}}
</script>
<script type="text/html" id="titleTpl1">
    {{d.cus.cusname}}
</script>
<script>
    $(function () {
        findCustomershare();
    })
    function findCustomershare() {
        var cusname = $("input[name='cusname']").val();
        layui.use('table', function () {
            var table = layui.table;
            table.render({
                elem: '#showCustomershare',
                even: true,
                totalRow: true,
                url: 'ShowCustomersharePage.action',
                height:500,
                page: true,
                limits: [3, 5, 7],
                method: 'post',
                where: {"cusname": cusname},
                cols: [[
                    {field: 'id', title: 'id', width: 200, sort: true, fixed: 'left', align: 'center'},
                    {field: '', title: '客户名字', width: 460, align: 'center', templet: '#titleTpl1'},
                    {field: '', title: '接待的员工名字', width: 438, align: 'center', templet: '#titleTpl2'},

                ]]
            })
        })
    }
</script>
</body>
</html>