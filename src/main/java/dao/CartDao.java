package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.Dbconnection;
import models.Carts;

public class CartDao {

    // Lấy tất cả sản phẩm trong giỏ hàng của user
    public List<Carts> getCartByUserId(int userId) {
        List<Carts> cartList = new ArrayList<>();
        String sql = "SELECT * FROM carts WHERE user_id = ?";
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cartList.add(new Carts(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }

    // Thêm sản phẩm vào giỏ hàng
    public boolean addToCart(Carts cart) {
        String sql = "INSERT INTO carts(user_id, product_id, quantity) VALUES(?,?,?) ON DUPLICATE KEY UPDATE quantity = quantity + ?";
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cart.getUser_id());
            ps.setInt(2, cart.getProduct_id());
            ps.setInt(3, cart.getQuantity());
            ps.setInt(4, cart.getQuantity());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật số lượng sản phẩm trong giỏ
    public boolean updateQuantity(int cartId, int quantity) {
        String sql = "UPDATE carts SET quantity = ? WHERE id = ?";
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, cartId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public boolean removeFromCart(int cartId) {
        String sql = "DELETE FROM carts WHERE id = ?";
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
