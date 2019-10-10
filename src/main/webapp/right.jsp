<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/8/15
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<style type="text/css">
   body{
       background-image:url("img/skeyedu.png");
       background-size: cover;
   }
    #myDIV {
        width: 400px;
        height: 100px;
        -webkit-animation: mymove 5s infinite;
        animation: mymove 5s infinite;
    }
   @-webkit-keyframes mymove {
       80% {font-size: 30px;}
   }
   @keyframes mymove {
       80% {font-size: 30px;}
   }

</style>
<body>
<div id="myDIV">
<span style="color: white">欢迎${roleName}<shiro:principal property="ename"/>,使用该系统</span>
</div>
</body>
</html>

