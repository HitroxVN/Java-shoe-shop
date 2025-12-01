/**
 * Author: Hoàng Văn Trường
 * Created on: Nov 22, 2025
 */
package dao;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Products;
import models.Users;

public class DAO {
	
	// mapping bảng user
	protected Users mapUser(ResultSet rs) throws SQLException {
		return new Users(
				rs.getInt("id"),
				rs.getString("full_name"),
				rs.getString("email"),
				rs.getString("password"),
				rs.getString("phone"),
				rs.getString("address"),
				rs.getString("role")
				);
	}
	
	protected Products mapProduct(ResultSet rs) throws SQLException {
		Products p = new Products();
		p.setId(rs.getInt("id"));
		p.setCategory_id(rs.getInt("category_id"));
		p.setName(rs.getString("name"));
		p.setDescription(rs.getString("description"));
		p.setPrice(rs.getDouble("price"));
		p.setImage(rs.getString("image"));
		p.setSize(rs.getString("size"));
		p.setStock(rs.getInt("stock"));
		try {
			p.setCategoryName(rs.getString("category_name"));
		} catch (SQLException e) {
			// Không có category_name
		}
		return p;
	}
	
	// hash password SHA-256
	protected String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(input.getBytes("UTF-8"));
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b)); 
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
