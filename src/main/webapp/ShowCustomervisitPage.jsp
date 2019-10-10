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
    <script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
</head>
<body>
<div class="layui-elem-quote">
    <p>分页查看所有客户访问记录</p>
</div>
<div style="margin:10px;">
    <input type="text" class='layui-input' name='cusname' placeholder="请输入客户名称" style="width:200px;display:inline-block;">
    <input type="button" onclick="findCustomervisit()" class="layui-btn" value="搜索">
</div>

<table id="showCustomervisit" lay-filter="test"></table>
<script type="text/html" id="titleTpl2">
	{{d.emp.ename}}
</script>
<script type="text/html" id="titleTpl1">
	{{d.cus.cusname}}
</script>
<script>
    $(function () {
        findCustomervisit();
    })
    function findCustomervisit() {
        layui.use('table', function () {
            var cusname = $("input[name='cusname']").val();
            var table = layui.table;
            table.render({
                elem: '#showCustomervisit',
                even: true,
                totalRow: true,
                url: 'ShowCustomervisitPage.action',
                height:500,
                page: true,
                limits: [3, 5, 7],
                method: 'post',
                where: {"cusname": cusname},
                cols: [[
                    {field: 'id', title: 'id', width: 100, sort: true, fixed: 'left', align: 'center'},
                    {field: '', title: '客户名字', width: 130, align: 'center', templet: '#titleTpl1'},
                    {field: '', title: '接待的员工名字', width: 137, align: 'center', templet: '#titleTpl2'},
                    {field: 'content', title: '访问内容', width: 430, align: 'center'},
                    {field: 'date', title: '访问时间', width: 300, align: 'center'}
                ]]
            })
        })
    }
</script>
</body>
</html>
