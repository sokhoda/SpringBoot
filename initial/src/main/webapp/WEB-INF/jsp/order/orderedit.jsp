<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page errorPage="../generalErrorPage.jsp" %>
<%--<script src="../../JS/angular.js"></script>--%>
<%--<script src="../../JS/orderController.js"></script>--%>
<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style>
        <%@include file='../../proj.css' %>
    </style>
</head>

<body>
<form action="../order/addnew" method="post" id="myForm">

    <input class="hidden" id="orderedPizzaIds" name="orderedPizzaIds">

    <div class="container-fluid bg-grey">
        <h2>
            Order
        </h2>
        <h3>
            <a href="/PizzaService/app/dashboard">&LongLeftArrow; back to Dashboard</a>
        </h3>
        <table class="table table-striped table-hover table-condensed " id="documenttab" style="width:95%;">
            <thead>
            <tr>
                <th><h3>Pizza Name</h3></th>
                <th><h3>Type</h3></th>
                <th><h3>Price</h3></th>
                <th><h3>Quantity</h3></th>
            </tr>
            </thead
            <c:forEach varStatus="loopCounter" items="${pizzas}" var="pizza">
                <tr>
                    <td align="left">${pizza.name}</td>
                    <td align="left">${pizza.type}</td>
                    <td align="left">${pizza.price}</td>
                    <td>
                        <input style="text-align: right; max-width: 4em"
                               class="form-control input-sm"
                               id="quantity${pizza.pizzaId}"
                               value="0">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%--<sec:authorize access="hasRole('ADMIN')">--%>
        <div class="text-center">
                <div class="btn-group">
                    <button type="button" id="crtupd" class="btn1  btn-default btn-lg btn-success"
                            onclick="generateOrderedPizzaIdString()">
                        Complete Order
                    </button>
                </div>
        </div>
        <%--</sec:authorize>--%>

        <footer style="margin-top:10px; text-align: center">&copy;Alex, Kyiv, 2017</footer>
    </div>
    <sec:csrfInput/>
</form>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <button type="submit" class="btn btn-warning btn-xs">Log out</button>
    <sec:csrfInput/>
</form>

</body>

<script type="text/javascript">

    function generateOrderedPizzaIdString() {
        var ids =  JSON.parse('${pizzaIds}');
        var arr = [];
        for(var i = 0; i < ids.length; i++) {
            var currCount = $('#quantity' + ids[i]).val();
            arr.push(ids[i] + ',' + currCount);
        }
        $('#orderedPizzaIds').val(arr.join(';'));
        $('#myForm').submit();
    }
</script>
</html>