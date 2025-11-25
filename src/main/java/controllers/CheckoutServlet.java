package controllers;

import dao.CheckoutDAO;
import db.Dbconnection;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CartItem;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        int userId = Integer.parseInt(req.getParameter("user_id"));
        String address = req.getParameter("address");
        String payment = "COD";

        // Đã đổi CartDAO thành CheckoutDAO
        CheckoutDAO cartDAO = new CheckoutDAO();
        ArrayList<CartItem> cart = cartDAO.getCartByUser(userId);

        double total = cart.stream().mapToDouble(CartItem::getTotal).sum();

        try {
            Connection conn = Dbconnection.getConnection();
            conn.setAutoCommit(false);

            String sqlOrder = "INSERT INTO orders (user_id, shipping_address, total_amount, payment_method) VALUES (?,?,?,?)";
            PreparedStatement psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setInt(1, userId);
            psOrder.setString(2, address);
            psOrder.setDouble(3, total);
            psOrder.setString(4, payment);
            psOrder.executeUpdate();

            ResultSet rs = psOrder.getGeneratedKeys();
            rs.next();
            int orderId = rs.getInt(1);

            String sqlItem = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?,?,?,?)";
            PreparedStatement psItem = conn.prepareStatement(sqlItem);

            for (CartItem item : cart) {
                psItem.setInt(1, orderId);
                psItem.setInt(2, item.getProductId());
                psItem.setInt(3, item.getQuantity());
                psItem.setDouble(4, item.getPrice());
                psItem.executeUpdate();
            }

            conn.commit();

            PreparedStatement clear = conn.prepareStatement("DELETE FROM carts WHERE user_id=?");
            clear.setInt(1, userId);
            clear.executeUpdate();

            psOrder.close();
            psItem.close();
            clear.close();
            conn.close();

            resp.sendRedirect("success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Lỗi khi thanh toán!");
        }
    }
}
