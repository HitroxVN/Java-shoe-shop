package dao;

import java.sql.*;
import db.Dbconnection;
import models.Orders;

public class OrderDao {

    // Tạo đơn hàng mới
    public int createOrder(Orders order) {
        String sql = "INSERT INTO orders(user_id, shipping_address, total_amount, payment_method, status, created_at, updated_at) VALUES(?,?,?,?,?,NOW(),NOW())";
        
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUser_id());
            ps.setString(2, order.getShipping_address());
            ps.setDouble(3, order.getTotal_amount());
            ps.setString(4, order.getPayment_method());
            ps.setString(5, order.getStatus());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Lấy thông tin đơn hàng theo ID
    public Orders getOrderById(int orderId) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt("id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setShipping_address(rs.getString("shipping_address"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setStatus(rs.getString("status"));
                order.setCreated_at(rs.getString("created_at"));
                order.setUpdated_at(rs.getString("updated_at"));
                return order;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cập nhật trạng thái đơn hàng
    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ?, updated_at = NOW() WHERE id = ?";
        
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
