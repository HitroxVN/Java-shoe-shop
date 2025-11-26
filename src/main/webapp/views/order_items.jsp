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
            <th>User ID</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.id}</td>
                <td>${item.orderId}</td>
                <td>${item.productId}</td>
                <td>${item.quantity}</td>
                <td>${item.price}</td>
                <td>${item.userId}</td>
                <td>
                    <c:if test="${canEdit}">
                        <a href="editOrderItem?id=${item.id}">Edit</a>
                        <a href="deleteOrderItem?id=${item.id}"
                           onclick="return confirm('Bạn có chắc muốn xóa item này?');">Delete</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
