<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/8/15
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<style>
    .mybtn{
        margin: 0px 10px 0px 10px;
    }
    .navbar navbar-inverse{
        padding-left:20px ;
    }
    .navbar-inverse h1{
        margin-bottom: 20px;
    }
</style>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<body>
<div class="container-fluid">
    <%--头--%>
    <div class="row">
        <div class="col-lg-12">
            <div class="navbar navbar-inverse" style="margin-bottom: 0px;">
                <h1 style="color: wheat; display: inline-block;margin-left: 500px">客户管理系统</h1>
                <div style="float: right;margin-top: 20px">
                <a href="lunBo.jsp" class="btn btn-default mybtn" target="right" style="background-color: snow"><span class="glyphicon glyphicon-picture"></span>&nbsp;显示轮播图</a>
                <a href="${pageContext.request.contextPath}/loginOut.action" class="btn btn-default mybtn" style="background-color: snow"><span class="glyphicon glyphicon-log-out"></span>&nbsp;退出登录</a>
                </div>
                </div>
        </div>
    </div>
    <%--xia--%>
    <div class="row" style="padding: 0; margin-top: 5px;" >
        <div class="col-lg-2">
            <%--左--%>
            <div class="panel-group" id="accordion" role="tablist"
                 aria-multiselectable="true" >

                <c:forEach items="${listLimit}" var="parentlimit" varStatus="index" >
                <c:if test="${parentlimit.parentid == 0}">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne" style="background-color: black">

                        <h4 class="panel-title" >
                            <a role="button"  data-toggle="collapse"
                               href="#${index.index}" aria-expanded="true" data-parent="#accordion"
                               aria-controls="collapseOne" style="color: white">${parentlimit.limitname}
                                <span class="glyphicon glyphicon-circle-arrow-down"> </span></a>
                        </h4>

                    </div>
                    <c:if test="${index.index == 0}">
                    <div id="${index.index}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        </c:if>
                        <c:if test="${index.index != 0}">
                        <div id="${index.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                            </c:if>
                            <div class="panel-body">
                                <c:forEach items="${listLimit}" var="childlimit">
                                    <c:if test="${childlimit.parentid == parentlimit.limitid}">
                                        <a class="btn btn-block btn-primary"
                                           href="${childlimit.limiturl}" target="right">${childlimit.limitname}</a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="col-lg-10" style="padding: 0">
                <div>
                    <iframe src="right.jsp" width="1124px" height="560px" name="right"></iframe>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
