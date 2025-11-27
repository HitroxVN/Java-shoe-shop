<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Order Items</title>
</head>
<body>

<h2>Order Items</h2>

<table border="1" cellpadding="5" cellspacing="0">
    <thead>
        <tr>
            <th>ID</th>
            <th>Order ID</th>
            <th>Product ID</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Ngày tạo đơn</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.id}</td>
                <td>${item.order_id}</td>
                <td>${item.product_id}</td>
                <td>${item.quantity}</td>
                <td>${item.price}</td>
                <td>${item.created_at}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
