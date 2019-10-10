<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/9/4
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
.carousel{}
.carousel img{width:800px;height:500px !important;}
</style>
<head>
    <title>Title</title>
</head>

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css " rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<body style="padding:20px 10% 0px 10%;background:url('img/xingxing.gif')">
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
        <li data-target="#carousel-example-generic" data-slide-to="5"></li>
        <li data-target="#carousel-example-generic" data-slide-to="6"></li>
        <li data-target="#carousel-example-generic" data-slide-to="7"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox" style="margin-left: 5%">
        <div class="item active">
            <img src="img/lala.jpg">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="img/h_bg.jpg">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="img/title.jpg">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="img/1000.jpg">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="img/time4.png">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="img/time3.png">
            <div class="carousel-caption">
            </div>
        </div>
         <div class="item">
            <img src="img/timg.jpg">
            <div class="carousel-caption">
            </div>
        </div>
         <div class="item">
            <img src="img/timg2.jpg">
            <div class="carousel-caption">
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
</body>
</html>
