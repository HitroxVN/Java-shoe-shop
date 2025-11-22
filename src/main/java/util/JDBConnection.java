package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBConnection {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3307/java_shop";
    private static final String USER = "root";    
    private static final String PASS = "";  
    
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }
	
	// dong ket noi
	public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
