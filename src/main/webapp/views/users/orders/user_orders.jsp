<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách đơn hàng của bạn</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #000; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h2>Danh sách đơn hàng của bạn</h2>

<c:choose>
    <c:when test="${not empty orders}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Địa chỉ giao hàng</th>
                    <th>Tổng tiền</th>
                    <th>Thanh toán</th>
                    <th>Trạng thái</th>
                    <th>Chi tiết</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="o" items="${orders}">
                    <tr>
                        <td>${o.id}</td>
                        <td>${o.shipping_address}</td>
                        <td>${o.total_amount}</td>
                        <td>${o.payment_method}</td>
                        <td>${o.status}</td>
                        <td>
                            <a href="UserOrderController?action=orderDetail&id=${o.id}">Xem chi tiết</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Bạn chưa có đơn hàng nào.</p>
    </c:otherwise>
</c:choose>

<a href="home">Quay lại trang chủ</a>

</body>
</html>
