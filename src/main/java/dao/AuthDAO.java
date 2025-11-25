/**
 * Author: Hoàng Văn Trường
 * Created on: Nov 22, 2025
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.Dbconnection;
import models.Users;

public class AuthDAO extends DAO {
	
	// đăng nhập
	public Users login(String email, String password) {
		String query = "select * from users where email = ?";
		try (Connection conn = Dbconnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, email);
			
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					Users u = mapUser(rs);
					
					// check pass hash
					if(sha256(password).equals(u.getPassword())) {
						return u; // ok
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null; // false
	}
	
	// đăng ký
	public boolean register(String email, String password) {
		String query = "INSERT INTO `users` (`email`, `password`) VALUES (?, ?)";
		
		try (Connection conn = Dbconnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setString(1, email);
			String hashPassword = sha256(password);
			ps.setString(2, hashPassword);
			
			return ps.executeUpdate() > 0; // true
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	// check email trong db
	public boolean checkEmail(String email) {
        String query = "SELECT email FROM users WHERE email = ? LIMIT 1";
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // nếu có dòng thì trả true
            }

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

}
