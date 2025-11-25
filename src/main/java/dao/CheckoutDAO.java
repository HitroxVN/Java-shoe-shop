package dao;

import java.sql.*;
import java.util.ArrayList;
import models.CartItem;
import db.Dbconnection;

public class CheckoutDAO {

    public ArrayList<CartItem> getCartByUser(int userId) {
        ArrayList<CartItem> list = new ArrayList<>();

        String sql = "SELECT c.id, c.product_id, p.name, p.price, c.quantity, p.image "
                   + "FROM carts c JOIN products p ON c.product_id = p.id "
                   + "WHERE c.user_id=?";

        try {
            Connection conn = Dbconnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();

                item.setId(rs.getInt("id"));
                item.setProductId(rs.getInt("product_id"));
                item.setProductName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                item.setImage(rs.getString("image"));

                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
