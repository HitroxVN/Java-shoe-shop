<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ Hàng Của Bạn</title>
</head>
<body>
    <h1>Giỏ Hàng Của Tôi</h1>
    
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>

    <c:choose>
        <c:when test="${empty cartItems}">
            <p>Giỏ hàng của bạn đang trống.</p>
        </c:when>
        <c:otherwise>
            <form action="checkout" method="POST">
                <table>
                    <thead>
                        <tr>
                            <th>Chọn</th>
                            <th>Sản phẩm</th>
                            <th>Phân loại</th>
                            <th>Đơn giá</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="totalCartPrice" value="${0}"/>
                        <c:forEach var="item" items="${cartItems}">
                            <tr>
                                <td>
                                    <input type="checkbox" name="selectedItems" value="${item.id}" checked>
                                </td>
                                <td>
                                    <img src="${item.imagePath}" width="50" height="50" alt="${item.productName}">
                                    ${item.productName}
                                </td>
                                <td>${item.variantName}</td>
                                <td><fmt:formatNumber value="${item.price}" pattern="#,##0"/> VNĐ</td>
                                <td>
                                    <input type="number" name="quantity_${item.id}" value="${item.quantity}" min="1">
                                </td>
                                <td>
                                    <c:set var="itemTotalPrice" value="${item.totalPrice}"/>
                                    <c:set var="totalCartPrice" value="${totalCartPrice + itemTotalPrice}"/>
                                    <fmt:formatNumber value="${itemTotalPrice}" pattern="#,##0"/> VNĐ
                                </td>
                                <td>
                                    <a href="delete-cart-item?itemId=${item.id}">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <h3>Tổng cộng: <fmt:formatNumber value="${totalCartPrice}" pattern="#,##0"/> VNĐ</h3>

                <button type="submit" name="action" value="update">Cập Nhật Số Lượng</button>
                <button type="submit" name="action" value="checkout">Tiến Hành Thanh Toán</button>
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>