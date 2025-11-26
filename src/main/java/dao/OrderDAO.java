package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Orders;
import db.Dbconnection;

public class OrderDAO {

    // Lấy danh sách tất cả Orders
    public List<Orders> getAllOrders() {
        List<Orders> list = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY created_at DESC";

        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Orders order = new Orders(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("shipping_address"),
                        rs.getDouble("total_amount"),
                        rs.getString("payment_method"),
                        rs.getString("status"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



    // Lấy 1 đơn hàng theo ID
    public Orders getOrderById(int id) {
        Orders order = null;
        String sql = "SELECT * FROM orders WHERE id = ? LIMIT 1";

        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Orders(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("shipping_address"),
                        rs.getDouble("total_amount"),
                        rs.getString("payment_method"),
                        rs.getString("status"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }



    // Thêm đơn hàng mới
    public boolean insertOrder(Orders o) {
        String sql = "INSERT INTO orders (user_id, shipping_address, total_amount, payment_method, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, o.getUser_id());
            ps.setString(2, o.getShipping_address());
            ps.setDouble(3, o.getTotal_amount());
            ps.setString(4, o.getPayment_method());
            ps.setString(5, o.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }



    // Cập nhật trạng thái đơn hàng
    public boolean updateStatus(int orderId, String newStatus) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Orders> getOrdersByUser(int userId) {
        List<Orders> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orders order = new Orders(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("shipping_address"),
                    rs.getDouble("total_amount"),
                    rs.getString("payment_method"),
                    rs.getString("status"),
                    rs.getString("created_at"),
                    rs.getString("updated_at")
                );
                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}