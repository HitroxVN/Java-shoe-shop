<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Danh sách đơn hàng</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>User ID</th>
        <th>Địa chỉ giao hàng</th>
        <th>Tổng tiền</th>
        <th>Thanh toán</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>

    <c:forEach var="o" items="${orders}">
        <tr>
            <td>${o.id}</td>
            <td>${o.user_id}</td>
            <td>${o.shipping_address}</td>
            <td>${o.total_amount}</td>
            <td>${o.payment_method}</td>
            <td>${o.status}</td>

            <td>
                <c:if test="${o.status == 'pending'}">
                    <a href="Ordercontrollers?action=confirm&id=${o.id}">Xác nhận</a>
                </c:if>

                <c:if test="${o.status != 'pending'}">
                    Đã xử lý
                </c:if>
                <a href="OrderItemController?id=${o.id}">Xem chi tiết đơn hàng</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>