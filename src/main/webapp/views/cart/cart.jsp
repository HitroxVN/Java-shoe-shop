<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Giỏ hàng</h2>

<table border="1" cellspacing="0" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Số lượng</th>
        <th>Thành tiền</th>
        <th>Thao tác</th>
    </tr>

    <c:forEach var="item" items="${cartList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.productName}</td>
            <td>${item.productPrice}</td>

            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="cartId" value="${item.id}">
                    <input type="number" min="1" name="quantity" value="${item.quantity}">
                    <input type="submit" value="Cập nhật">
                </form>
            </td>

            <td>${item.productPrice * item.quantity}</td>

            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="cartId" value="${item.id}">
                    <input type="submit" value="Xóa">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
