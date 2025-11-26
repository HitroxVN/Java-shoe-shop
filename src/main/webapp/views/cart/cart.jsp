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

<br/>
<%
    double total = 0;
    java.util.List<models.Carts> cartList = (java.util.List<models.Carts>) request.getAttribute("cartList");
    if (cartList != null) {
        for (models.Carts cart : cartList) {
            total += cart.getProductPrice() * cart.getQuantity();
        }
    }
%>

<h3>Tổng tiền: <%= total %> VNĐ</h3>

<form action="checkout" method="get">
    <input type="hidden" name="userId" value="1" />
    <input type="submit" value="Thanh toán" style="padding:10px 16px;background:#4CAF50;color:#fff;border:none;border-radius:4px;cursor:pointer;" />
</form>
