package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.Dbconnection;
import models.OrderItem;

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
}
