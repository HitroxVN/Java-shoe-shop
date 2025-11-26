package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.Dbconnection;
import models.Order_items;

public class OrderItemDao {

    // Thêm sản phẩm vào order
    public boolean addOrderItem(Order_items orderItem) {
        String sql = "INSERT INTO order_items(order_id, product_id, quantity, price, created_at, updated_at) VALUES(?,?,?,?,NOW(),NOW())";
        
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderItem.getOrder_id());
            ps.setInt(2, orderItem.getProduct_id());
            ps.setInt(3, orderItem.getQuantity());
            ps.setDouble(4, orderItem.getPrice());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách sản phẩm của một order
    public List<Order_items> getOrderItemsByOrderId(int orderId) {
        List<Order_items> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order_items item = new Order_items();
                item.setId(rs.getInt("id"));
                item.setOrder_id(rs.getInt("order_id"));
                item.setProduct_id(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setCreated_at(rs.getString("created_at"));
                item.setUpdated_at(rs.getString("updated_at"));
                orderItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }
}
