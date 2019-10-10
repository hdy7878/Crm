<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/21
  Time: 10:38
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
<style>
    .layui-table-box{height:77.3%}
</style>
<div class="layui-elem-quote">
    <p>分页查看所有客户</p>
</div>
<div style="margin:10px;">
    <input type="text" class='layui-input' name='cusname' placeholder="请输入客户名称" style="width:200px;display:inline-block;">
    <input type="button" onclick="findCustomer()" class="layui-btn" value="搜索">
</div>
<table id="showCus" lay-filter="test"></table>
<script>

    $(function(){
        findCustomer();
    })

    function findCustomer(){
        var cusname = $("input[name='cusname']").val();
        layui.use('table',function () {
            var table=layui.table;
            table.render({
                elem:'#showCus',
                even:true,
                totalRow:true,
                url:'ShowCustomerPage.action',
                page:true,
                height:500,
                limits: [3,5,7],
                method : 'post',
                where : {"cusname":cusname},
                cols:[[
                    {field:'cid',title:'客户id',width:120,sort:true,fixed:'left',align:'center'},
                    {field:'cusname',title:'客户名字',width:120,align:'center'},
                    {field:'address',title:'客户地址',width:130,align:'center'},
                    {field:'contact',title:'联系人',width:120,align:'center'},
                    {field:'tel',title:'客户联系方式',width:300,align:'center'},
                    {field:'email',title:'客户电子邮箱',width:305,align:'center'}
                ]]
            })
        })
    }


</script>
</body>
</html>
