<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>ERROR</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<div style="margin-left: 20px">
    <h1 class="text-danger">
       ERROR
    </h1>
    <h4>
    	<%=   exception.toString()    %>
    </h4>
    </div>
</body>
</html>
