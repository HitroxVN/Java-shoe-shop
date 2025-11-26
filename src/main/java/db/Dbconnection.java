package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbconnection {

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String USER = "root";
    private static final String DATABASE = "java_shop";
    private static final String PASSWORD = "123456";

    // Thêm useSSL=false và serverTimezone để tránh SSLException và lỗi timezone
    private static final String JDBC_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
                                           + "?useSSL=false&serverTimezone=Asia/Bangkok";

    // Lấy kết nối
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy driver MySQL", e);
        }
    }

    // Đóng kết nối
    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test kết nối
    public static void main(String[] args) {
        try (Connection conn = Dbconnection.getConnection()) {
            if (conn != null) {
                System.out.println("Kết nối thành công!");
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
