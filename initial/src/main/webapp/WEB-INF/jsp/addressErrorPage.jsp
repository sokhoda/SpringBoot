<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>ADDRESS ERROR</title>
</head>
<body>

    <h1 class="text-danger">
       ERROR PAGE: FOLLOWING ADDRESS IS NOT VALID
    </h1>
    ${url}
<br/>
    	${ex}

</body>
</html>
