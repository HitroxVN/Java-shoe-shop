<%@ page import="dao.CheckoutDAO, models.CartItem, java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    int userId = 1; // tạm thời
    CheckoutDAO cartDAO = new CheckoutDAO();
    ArrayList<CartItem> cart = cartDAO.getCartByUser(userId);

    double total = 0;
%>

<h2>Thanh toán hóa đơn</h2>

<table border="1" cellpadding="10">
    <tr>
        <th>Sản phẩm</th>
        <th>Số lượng</th>
        <th>Giá</th>
        <th>Tổng</th>
    </tr>

    <% for (CartItem item : cart) { %>
        <tr>
            <td><%= item.getProductName() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getPrice() %> VNĐ</td>
            <td><%= item.getTotal() %> VNĐ</td>
        </tr>
        <% total += item.getTotal(); %>
    <% } %>
</table>

<h3>Tổng thanh toán: <%= total %> VNĐ</h3>

<form action="CheckoutServlet" method="post">
    <input type="hidden" name="user_id" value="<%= userId %>">

    Địa chỉ giao hàng: <br>
    <input type="text" name="address" required style="width:300px;"><br><br>

    Phương thức thanh toán: <br>
    <select name="payment">
        <option value="COD" selected>Thanh toán khi nhận hàng</option>
    </select><br><br>

    <button type="submit">Đặt hàng</button>
</form>
