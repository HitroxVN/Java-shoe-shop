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

	private static final String JDBC_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
	}

	// dong ket noi
	public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	test main connect
	public static void main(String[] args) {
		try (Connection conn = Dbconnection.getConnection()) {
			if (conn != null) {
				System.out.println("Ket noi thanh cong");
			} else {
				System.out.println("Ket noi that bai");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}