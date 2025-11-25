package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.CategoryStatistic;
import models.CustomerStatistic;
import models.ProductStatistic;
import models.Products;
import util.JDBConnection;

public class StatisticDAO {
	
	
	// lấy ra tổng doanh thu
	public double doanhThu() {
		double doanhThu = 0;
		
		try {
			Connection connection = JDBConnection.getConnection();
			
			String sql = "SELECT SUM(total_amount) FROM orders WHERE status = 'delivered'";
			PreparedStatement st = connection.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				doanhThu = rs.getDouble(1);
			}
			
			JDBConnection.closeConnection(connection, st, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doanhThu;
	}
	
	
	// lấy ra top 3 sp bán chạy nhất
	public List<ProductStatistic> sanPhanBanChay(){
		List<ProductStatistic> list = new ArrayList();
		
		try {
			Connection connection = JDBConnection.getConnection();
			
			String sql = "SELECT p.id, p.name, SUM(oi.quantity) AS sold "
		               + "FROM order_items oi "
		               + "JOIN orders o ON oi.order_id = o.id " // Thêm JOIN orders để lọc
		               + "JOIN products p ON oi.product_id = p.id " 
		               + "WHERE o.status = 'delivered' "         // Lọc theo trạng thái đã giao
		               + "GROUP BY p.id, p.name "
		               + "ORDER BY sold DESC "
		               + "LIMIT 3";
			
			PreparedStatement st = connection.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			 while (rs.next()) {
	                ProductStatistic productStatistic = new  ProductStatistic(
	                        rs.getInt("id"),
	                        rs.getString("name"),
	                        rs.getInt("sold")
	                );
	                list.add(productStatistic);
	            }

			JDBConnection.closeConnection(connection, st, rs);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	// top 3 khách hàng vip
	public List<CustomerStatistic> khachHangVip() {
	    List<CustomerStatistic> list = new ArrayList<>();

	    try {
	        Connection connection = JDBConnection.getConnection();

	        String sql = "SELECT u.id, u.full_name, "
	                   + "COUNT(o.id) AS totalOrders, "
	                   + "SUM(o.total_amount) AS totalSpent "
	                   + "FROM orders o "
	                   + "JOIN users u ON o.user_id = u.id "
	                   + "WHERE o.status = 'delivered' "
	                   + "GROUP BY u.id, u.full_name "
	                   + "ORDER BY totalSpent DESC "
	                   + "LIMIT 3";

	        PreparedStatement st = connection.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            CustomerStatistic customer = new CustomerStatistic(
	                    rs.getInt("id"),
	                    rs.getString("full_name"),
	                    rs.getInt("totalOrders"),
	                    rs.getDouble("totalSpent")
	            );
	            list.add(customer);
	        }

	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	// thể loai
	// Lấy top 3 thương hiệu bán chạy nhất
	public List<CategoryStatistic> topThuongHieuBanChay() {
	    List<CategoryStatistic> list = new ArrayList<>();
	    try {
	        Connection connection = JDBConnection.getConnection();
	        String sql = "SELECT c.id, c.name, SUM(oi.quantity) AS totalSold " +
	                     "FROM order_items oi " +
	                     "JOIN orders o ON oi.order_id = o.id " +
	                     "JOIN products p ON oi.product_id = p.id " +
	                     "JOIN categories c ON p.category_id = c.id " +
	                     "WHERE o.status = 'delivered' " +
	                     "GROUP BY c.id, c.name " +
	                     "ORDER BY totalSold DESC " +
	                     "LIMIT 3";

	        PreparedStatement st = connection.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            CategoryStatistic cs = new CategoryStatistic(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getInt("totalSold")
	            );
	            list.add(cs);
	        }

	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	// tổng số thể loại
	public int soThuongHieu() {
		int count = 0;
		
		try {
			Connection c = JDBConnection.getConnection();
			
			String sql = "SELECT COUNT(*) FROM categories";
			PreparedStatement st = c.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			JDBConnection.closeConnection(c, st, rs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return count;
	}
	
	
	// Lấy các thương hiệu có tổng số lượng sản phẩm trong kho < 100
	public List<CategoryStatistic> thuongHieuTonThap() {
	    List<CategoryStatistic> list = new ArrayList<>();
	    try {
	        Connection connection = JDBConnection.getConnection();
	        String sql = "SELECT c.id, c.name, SUM(p.stock) AS totalStock " +
	                     "FROM products p " +
	                     "JOIN categories c ON p.category_id = c.id " +
	                     "GROUP BY c.id, c.name " +
	                     "HAVING totalStock < 100 " +
	                     "ORDER BY totalStock ASC";

	        PreparedStatement st = connection.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            CategoryStatistic cs = new CategoryStatistic(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getInt("totalStock")
	            );
	            list.add(cs);
	        }

	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	
	
	// tổng số đơn hàng
	// hàm lấy ra cố lượng đơn hàng
	public int tongSoDonHang() {
		int totalOrders = 0;
	    try {
	        Connection connection = JDBConnection.getConnection();
	        String sql = "SELECT COUNT(*) FROM orders";
	        PreparedStatement st = connection.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            totalOrders = rs.getInt(1);
	        }
	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return totalOrders;
	}

	// đếm số lượng đơn hàng theo trạng thái
	public int countOrderByStatus(String status) {
	    int count = 0;

	    try {
	        Connection connection = JDBConnection.getConnection();

	        String sql = "SELECT COUNT(*) FROM orders WHERE status = ?";
	        PreparedStatement st = connection.prepareStatement(sql);
	        st.setString(1, status);

	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            count = rs.getInt(1);
	        }

	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	// phần thống kê sản phẩm
	// tổng số sản phẩm
	public int tongSanPham() {
	    int total = 0;
	    try {
	        Connection connection = JDBConnection.getConnection();
	        String sql = "SELECT COUNT(*) FROM products";
	        PreparedStatement st = connection.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        if (rs.next()) total = rs.getInt(1);

	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	}
	
	// tổng số lượng sản phẩm trong kho
		public int tongSanPhamTrongKho() {
		    int total = 0;
		    try {
		        Connection connection = JDBConnection.getConnection();
		        String sql = "SELECT SUM(stock) FROM products";
		        PreparedStatement st = connection.prepareStatement(sql);
		        ResultSet rs = st.executeQuery();

		        if (rs.next()) total = rs.getInt(1);

		        JDBConnection.closeConnection(connection, st, rs);

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return total;
		}

	
	// tổng số lượng sản phẩm đã bán
	public int tongSanPhamDaBan() {
	    int total = 0;
	    try {
	        Connection connection = JDBConnection.getConnection();
	        String sql = "SELECT SUM(quantity) FROM order_items";
	        PreparedStatement st = connection.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        if (rs.next()) total = rs.getInt(1);

	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	}
	// lấy ra tất cả các sản phảm dưới 30 sp
	public List<Products> sanPhamTonThap() {
	    List<Products> list = new ArrayList<>();
	    try {
	        Connection connection = JDBConnection.getConnection();
	        String sql = "SELECT * FROM products WHERE stock < 30 ORDER BY stock ASC";
	        PreparedStatement st = connection.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            Products product = new Products(
	                rs.getInt("id"),
	                rs.getInt("category_id"),
	                rs.getString("name"),
	                rs.getString("description"),
	                rs.getDouble("price"),
	                rs.getString("image"),
	                rs.getString("size"),
	                rs.getInt("stock")
	            );
	            list.add(product);
	        }

	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	
	
	// phàn thống kê khách hàng
	// tổng số tài khoản
	public int tongTaiKhoan() {
	    int total = 0;
	    try {
	        Connection connection = JDBConnection.getConnection();
	        String sql = "SELECT COUNT(*) FROM users";
	        PreparedStatement st = connection.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            total = rs.getInt(1);
	        }

	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	}
	// tổng số tài khoản theo quyền
	public int tongTaiKhoanTheoRole(String role) {
	    int total = 0;
	    try {
	        Connection connection = JDBConnection.getConnection();
	        String sql = "SELECT COUNT(*) FROM users WHERE role = ?";
	        PreparedStatement st = connection.prepareStatement(sql);
	        st.setString(1, role);
	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            total = rs.getInt(1);
	        }

	        JDBConnection.closeConnection(connection, st, rs);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	}


	
	
	public static void main(String[] args) {
	    StatisticDAO dao = new StatisticDAO();

	    // 1. Tổng doanh thu
	    System.out.println("Tổng doanh thu: " + dao.doanhThu());

	    // 2. Top 3 sản phẩm bán chạy nhất
	    System.out.println("\nTop 3 sản phẩm bán chạy nhất:");
	    List<ProductStatistic> productStatistics = dao.sanPhanBanChay();
	    for (ProductStatistic ps : productStatistics) {
	        System.out.println(ps.toString());
	    }

	    // 3. Top 3 khách hàng VIP
	    System.out.println("\nTop 3 khách hàng VIP:");
	    List<CustomerStatistic> customerStatistics = dao.khachHangVip();
	    for (CustomerStatistic cs : customerStatistics) {
	        System.out.println(cs.toString());
	    }

	    // 4. Tổng số đơn hàng
	    System.out.println("\nTổng số đơn hàng: " + dao.tongSoDonHang());

	    // 5. Tổng số đơn hàng theo trạng thái
	    String[] statuses = {"pending", "confirmed", "shipped", "delivered", "cancelled"};
	    System.out.println("\nSố lượng đơn hàng theo trạng thái:");
	    for (String status : statuses) {
	        System.out.println(status + ": " + dao.countOrderByStatus(status));
	    }

	    // 6. Thống kê sản phẩm
	    System.out.println("\nTổng số sản phẩm: " + dao.tongSanPham());
	    System.out.println("Tổng số sản phẩm trong kho: " + dao.tongSanPhamTrongKho());
	    System.out.println("Tổng số sản phẩm đã bán: " + dao.tongSanPhamDaBan());

	    // 7. Sản phẩm tồn kho dưới 30
	    System.out.println("\nSản phẩm tồn kho dưới 30:");
	    List<Products> lowStockProducts = dao.sanPhamTonThap();
	    for (Products p : lowStockProducts) {
	        System.out.println(p.toString());
	    }

	    // 8. Thống kê khách hàng
	    System.out.println("\nTổng số tài khoản: " + dao.tongTaiKhoan());
	    String[] roles = {"customer", "admin", "staff"};
	    System.out.println("Tổng số tài khoản theo role:");
	    for (String role : roles) {
	        System.out.println(role + ": " + dao.tongTaiKhoanTheoRole(role));
	    }
	    
	    // 9 top 3 thương hiệu bạn chạy nhất
	    System.out.println("\nTop 3 thương hiệu bán chạy nhất:");
	    List<CategoryStatistic> topCategories = dao.topThuongHieuBanChay();
	    for (CategoryStatistic cs : topCategories) {
	        System.out.println(cs.toString());
	    }
	    
	    // 10 số lượng sp của thương hiệu nào it
	    System.out.println("\nCác thương hiệu có tổng sản phẩm tồn kho < 100:");
	    List<CategoryStatistic> lowStockCategories = dao.thuongHieuTonThap();
	    for (CategoryStatistic cs : lowStockCategories) {
	        System.out.println(cs.toString());
	    }
	    // 11 số lượng thể loại
	    System.out.println(dao.soThuongHieu());

	}
}
