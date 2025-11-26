<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác nhận thanh toán</title>
</head>
<body>

<h1 align="center">Xác nhận thanh toán</h1>

<!-- ======================= DANH SÁCH SẢN PHẨM ======================= -->

<h3>Danh sách sản phẩm</h3>

<table border="1" width="100%" cellpadding="8" cellspacing="0">
    <tr>
        <th>Sản phẩm</th>
        <th>Số lượng</th>
        <th>Giá</th>
        <th>Tổng</th>
    </tr>

    <c:forEach var="item" items="${cartList}">
        <tr>
            <td>${item.productName}</td>
            <td>${item.quantity}</td>
            <td>${item.productPrice} VNĐ</td>
            <td>${item.productPrice * item.quantity} VNĐ</td>
        </tr>
    </c:forEach>
</table>

<br>

<table border="1" width="100%" cellpadding="8" cellspacing="0">
    <tr>
        <td><b>Tổng sản phẩm:</b></td>
        <td>
            <c:set var="totalItems" value="0"/>
            <c:forEach var="item" items="${cartList}">
                <c:set var="totalItems" value="${totalItems + item.quantity}"/>
            </c:forEach>
            ${totalItems}
        </td>
    </tr>

    <tr>
        <th>Tổng cộng:</th>
        <th>${totalAmount} VNĐ</th>
    </tr>
</table>

<br><br>

<!-- ======================= FORM GIAO HÀNG ======================= -->

<h3>Thông tin giao hàng</h3>

<form method="post" action="checkout">

    <table border="1" width="100%" cellpadding="8" cellspacing="0">
        <tr>
            <td>Địa chỉ giao hàng *</td>
            <td>
                <textarea name="shippingAddress" rows="4" required></textarea>
            </td>
        </tr>

        <tr>
            <td>Phương thức thanh toán *</td>
            <td>
                <select name="paymentMethod" required>
                    <option value="COD">Thanh toán khi nhận hàng</option>
                    <option value="BANKING">Chuyển khoản ngân hàng</option>
                </select>
            </td>
        </tr>
    </table>

    <input type="hidden" name="userId" value="${userId}">
    <input type="hidden" name="totalAmount" value="${totalAmount}">

    <br>

    <button type="submit">Hoàn tất thanh toán</button>
    <button type="button" onclick="window.location.href='cart'">Quay lại giỏ hàng</button>

</form>

<script>
    document.querySelector('form').addEventListener('submit', function(e) {
        if (!confirm('Bạn có chắc chắn muốn hoàn tất thanh toán không?')) {
            e.preventDefault();
        }
    });
</script>

</body>
</html>
