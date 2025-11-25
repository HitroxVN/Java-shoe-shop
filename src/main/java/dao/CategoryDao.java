package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Dbconnection;
import models.Categories;

public class CategoryDao {
	// Lấy tất cả loại sản phẩm
	public List<Categories> getAll() {
		List<Categories> categories = new ArrayList<>();
		String sql = "SELECT * FROM categories ORDER BY id ASC";

		try (Connection conn = Dbconnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Categories category = new Categories();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	// Lấy loại sản phẩm theo ID
	public Categories getCategoryById(int id) {
		Categories category = null;
		String sql = "SELECT * FROM categories WHERE id = ?";

		try (Connection conn = Dbconnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				category = new Categories();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	// Thêm loại sản phẩm mới
	public boolean addCategory(Categories category) {
		String sql = "INSERT INTO categories (name) VALUES (?)";

		try (Connection conn = Dbconnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, category.getName());
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Cập nhật loại sản phẩm
	public boolean updateCategory(Categories category) {
		String sql = "UPDATE categories SET name=? WHERE id=?";

		try (Connection conn = Dbconnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, category.getName());
			pstmt.setInt(2, category.getId());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Xóa loại sản phẩm
	public boolean deleteCategory(int id) {
		String sql = "DELETE FROM categories WHERE id = ?";

		try (Connection conn = Dbconnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Kiểm tra xem loại sản phẩm có sản phẩm nào không
	public boolean hasProducts(int categoryId) {
		String sql = "SELECT COUNT(*) as count FROM products WHERE category_id = ?";

		try (Connection conn = Dbconnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, categoryId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("count") > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}