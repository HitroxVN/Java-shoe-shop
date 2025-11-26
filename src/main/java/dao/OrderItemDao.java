package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.Dbconnection;
import models.OrderItem;
import models.Order_items;

public class OrderItemDAO {

    public List<OrderItem> getItemsByOrderId(int orderId) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem(
                    rs.getInt("id"),
                    rs.getInt("order_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("created_at"),
                    rs.getString("updated_at")
                );
                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
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
}
