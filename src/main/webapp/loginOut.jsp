<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2019/8/16
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        session.removeAttribute("employee");
        out.write("<script>location.href='login.jsp';</script>");
    %>
</body>
</html>
