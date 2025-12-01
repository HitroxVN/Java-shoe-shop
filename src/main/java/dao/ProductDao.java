package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Dbconnection;
import models.Products;

public class ProductDao extends DAO {
	
	// Lấy tất cả sản phẩm
	public List<Products> getAll() {
		List<Products> list = new ArrayList<>();
		String sql = "SELECT p.*, c.name as category_name FROM products p "
				+ "LEFT JOIN categories c ON p.category_id = c.id ORDER BY p.id DESC";

		try (Connection conn = Dbconnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				list.add(mapProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Lấy sản phẩm theo ID
	public Products getProductById(int id) {
		String sql = "SELECT p.*, c.name as category_name FROM products p "
				+ "LEFT JOIN categories c ON p.category_id = c.id WHERE p.id = ?";

		try (Connection conn = Dbconnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return mapProduct(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Thêm sản phẩm
	public boolean addProduct(Products product) {
		String sql = "INSERT INTO products (category_id, name, description, price, image, size, stock) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = Dbconnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, product.getCategory_id());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getDescription());
			pstmt.setDouble(4, product.getPrice());
			pstmt.setString(5, product.getImage());
			pstmt.setString(6, product.getSize());
			pstmt.setInt(7, product.getStock());

			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Kiểm tra tồn tại tên sản phẩm (toàn cục)
	public boolean existsByName(String name) {
		String sql = "SELECT COUNT(*) FROM products WHERE name = ?";
		try (Connection conn = Dbconnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Kiểm tra tồn tại tên sản phẩm, loại bỏ chính product có id (dùng khi update)
	public boolean existsByNameExcludingId(String name, int id) {
		String sql = "SELECT COUNT(*) FROM products WHERE name = ? AND id <> ?";
		try (Connection conn = Dbconnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, name);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Cập nhật sản phẩm
	public boolean updateProduct(Products product) {
		String sql = "UPDATE products SET category_id=?, name=?, description=?, price=?, "
				+ "image=?, size=?, stock=? WHERE id=?";

		try (Connection conn = Dbconnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, product.getCategory_id());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getDescription());
			pstmt.setDouble(4, product.getPrice());
			pstmt.setString(5, product.getImage());
			pstmt.setString(6, product.getSize());
			pstmt.setInt(7, product.getStock());
			pstmt.setInt(8, product.getId());

			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Xóa sản phẩm
	public boolean deleteProduct(int id) {
		String sql = "DELETE FROM products WHERE id = ?";

		try (Connection conn = Dbconnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Tìm kiếm sản phẩm
	public List<Products> search(String keyword, Integer categoryId) {
		List<Products> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT p.*, c.name as category_name FROM products p "
				+ "LEFT JOIN categories c ON p.category_id = c.id WHERE 1=1");

		if (keyword != null && !keyword.trim().isEmpty()) {
			sql.append(" AND p.name LIKE ?");
		}
		if (categoryId != null && categoryId > 0) {
			sql.append(" AND p.category_id = ?");
		}
		sql.append(" ORDER BY p.id DESC");

		try (Connection conn = Dbconnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

			int index = 1;
			if (keyword != null && !keyword.trim().isEmpty()) {
				pstmt.setString(index++, "%" + keyword + "%");
			}
			if (categoryId != null && categoryId > 0) {
				pstmt.setInt(index++, categoryId);
			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(mapProduct(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
