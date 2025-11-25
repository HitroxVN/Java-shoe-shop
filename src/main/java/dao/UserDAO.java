/**
 * Author: Hoàng Văn Trường
 * Created on: Nov 21, 2025
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Dbconnection;
import models.Users;

public class UserDAO extends DAO {
	
	// lấy tất cả users
		public List<Users> getAll(){
			List<Users> list = new ArrayList<>();
			
			String query = "select * from users";
			try (Connection conn = Dbconnection.getConnection();
		             PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery()) {
				
				while(rs.next()) {
					list.add(mapUser(rs));
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
		}
		
		// lấy tất cả users theo role
		public List<Users> getByRole(String role){
			List<Users> list = new ArrayList<>();
			
			String query = "select * from users where role = ?";
			try (Connection conn = Dbconnection.getConnection();
		             PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, role);
				
				try (ResultSet rs = ps.executeQuery()){
					while(rs.next()) {
						list.add(mapUser(rs));
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
		}
	
	// lấy u theo id
	public Users getById(int id) {
		String query = "select * from users where id = ?";
		try (Connection conn = Dbconnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return mapUser(rs);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	// tạo user mới
	public boolean create(Users user) {
		String query = "INSERT INTO users(full_name, email, password, phone, address, role) VALUES(?,?,?,?,?,?)";
		
		try (Connection conn = Dbconnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setString(1, user.getFull_name());
	        ps.setString(2, user.getEmail());
	        String hashPassword = sha256(user.getPassword());
	        ps.setString(3, hashPassword);
	        ps.setString(4, user.getPhone());
	        ps.setString(5, user.getAddress());
	        ps.setString(6, user.getRole());
			
			return ps.executeUpdate() > 0; // return true if row > 0
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	// cập nhập  user 
		public boolean update(Users user) {
			String query = "UPDATE users SET full_name = ?, email = ?, password = ?, phone = ?, address = ?, role = ? WHERE id = ?";
			
			try (Connection conn = Dbconnection.getConnection();
					PreparedStatement ps = conn.prepareStatement(query)) {
				
				ps.setString(1, user.getFull_name());
		        ps.setString(2, user.getEmail());
		        ps.setString(3, user.getPassword());
		        ps.setString(4, user.getPhone());
		        ps.setString(5, user.getAddress());
		        ps.setString(6, user.getRole());
		        ps.setInt(7, user.getId());
				
				return ps.executeUpdate() > 0; // return true if row > 0
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		
		// xoá user
		public boolean delete(int id) {
		    String sql = "DELETE FROM users WHERE id = ?";

		    try (Connection conn = Dbconnection.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setInt(1, id);

		        return ps.executeUpdate() > 0;

		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
//		tìm kiếm theo tên, email, sđt
		public List<Users> search(String keyword){
			List<Users> list = new ArrayList<>();
			String query = "SELECT * FROM users WHERE full_name LIKE ? OR email LIKE ? OR phone LIKE ?";
			
			try (Connection conn = Dbconnection.getConnection();
			         PreparedStatement ps = conn.prepareStatement(query)) {
				
				String searchKey = "%" + keyword + "%";
				ps.setString(1, searchKey);
				ps.setString(2, searchKey);
				ps.setString(3, searchKey);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					list.add(mapUser(rs));
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return list;
		}

	
	// main test 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDAO dao = new UserDAO();
//		List<User> list = dao.getByRole("staff");
//		for (User user : list) {
//			System.out.println(user);
//		}
		
//		User user = dao.getById(3);
//		System.out.println(user);
	}

}
