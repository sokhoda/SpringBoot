<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- Created by IntelliJ IDEA.
 User: s_okhoda
 Date: 20.01.2016
 Time: 17:52
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<script src="../../../js/taxi.js" type="text/javascript"></script>--%>
<%--<script src="../../../js/angular.js"></script>--%>
<%--<script src="../../../js/registerAjax.js"></script>--%>

<html>
<head>
    <title>Registration Form</title>
    <style>
        <%--
            <%@include file='../css/addNotebook.css' %>
            --%>
    </style>
</head>

<body>
<form>
    <%=
    pageContext.getRequest().getRemoteHost();
    %>
    <%
        pageContext.setAttribute("fgAttr", 1, pageContext.REQUEST_SCOPE);
        RequestDispatcher requestDispatcher =
                pageContext.getServletContext().getRequestDispatcher("pizzalist.jsp");
        requestDispatcher.forward(request, response);
    %>
    <p>
        Hello <b>${pageContext.request.remoteUser}</b>
    </p>
</form>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <button type="submit" class="btn btn-warning btn-xs">Log out</button>
    <sec:csrfInput/>
</form>
</body>
</html>
