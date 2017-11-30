<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>ERROR</title>
</head>
<body>

    <h1 class="text-danger">
       ERROR PAGE
    </h1>
    ${url}
<br/>
    	${ex}

</body>
</html>
