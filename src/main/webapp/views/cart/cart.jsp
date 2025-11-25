<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Giỏ hàng</h2>

<table border="1">
    <tr>
        <th>Sản phẩm</th>
        <th>Số lượng</th>
        <th>Giá</th>
        <th>Thao tác</th>
    </tr>

    <c:forEach var="item" items="${cartItems}">
        <tr>
            <td>${item.product.name}</td>

            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="cartId" value="${item.cart.id}"/>
                    <input type="number" name="quantity" min="1" value="${item.cart.quantity}"/>
                    <input type="submit" value="Cập nhật"/>
                </form>
            </td>

            <td>${item.product.price}</td>

            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="remove"/>
                    <input type="hidden" name="cartId" value="${item.cart.id}"/>
                    <input type="submit" value="Xóa"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
