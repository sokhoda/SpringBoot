<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page errorPage="../generalErrorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <style>
        <%@include file='../proj.css' %>
    </style>
    <%--
      <script src="js/FillClick.js"></script>
      --%>
</head>

<body>


<input class="hidden" id="command" name="command" value="">


<div class="container-fluid bg-grey">
    <h2>
        Dashboard
    </h2>
    <h3>
        <p>${title}: ${message}<br/></p>
        <p>Logged in as: <span style="color:blue">${userName}</span>; </p>
        Roles: <span style="color:brown"> ${userRoles}</span>;
    </h3>
    <h2>

        <a href="mail/send">-1. Send Mail</a>
        <br>
        <br>
        <a href="order/create">0. Create Order</a>
        <br>
        <br>
        <a href="pizza/list">1. Browse Pizza list</a>
        <br>
        <br>
        <a href="pizza/pizzalist/upload">2. Import Pizza list</a>
        <br>
        <br>
        <br>
        <a href="customer/list">3. Browse Customer list</a>
        <br>
        <br>
        <a href="customer/customerlist/upload">4. Import Customer list</a>
    </h2>

    <c:url var="logoutUrl" value="/app/logout"/>
    <form action="${logoutUrl}" method="post">
        <br>
        <button type="submit" class="btn btn-warning btn-xs">Log out</button>
        <sec:csrfInput/>
    </form>

    <footer style="margin-top:10px; text-align: center">&copy;Alex, Kyiv, 2017</footer>

</div>
</body>

</html>