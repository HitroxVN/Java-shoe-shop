package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.Dbconnection;
import models.Carts;

public class CartDao {

    // Lấy tất cả sản phẩm trong giỏ hàng kèm thông tin sản phẩm
    public List<Carts> getCartByUserId(int userId) {
        List<Carts> cartList = new ArrayList<>();
        String sql = 
            "SELECT c.id, c.user_id, c.product_id, c.quantity, "
             +  "    p.name AS productName, p.price AS productPrice "
           + " FROM carts c "
          + " JOIN products p ON c.product_id = p.id "
          + " WHERE c.user_id = ? " ;

        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Carts cart = new Carts();
                cart.setId(rs.getInt("id"));
                cart.setProductId(userId);
                cart.setQuantity(rs.getInt("quantity"));
                cart.setProductName(rs.getString("productName"));
                cart.setProductPrice(rs.getDouble("productPrice"));

                cartList.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    }

    // Thêm sản phẩm vào giỏ hàng
    public boolean addToCart(Carts cart) {
        String sql = "INSERT INTO carts(user_id, product_id, quantity) VALUES(?,?,?)ON DUPLICATE KEY UPDATE quantity = quantity + ? ";

        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cart.getId());
            ps.setInt(2, cart.getProductId());
            ps.setInt(3, cart.getQuantity());
            ps.setInt(4, cart.getQuantity());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật số lượng
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

    // Xóa sản phẩm khỏi giỏ
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
