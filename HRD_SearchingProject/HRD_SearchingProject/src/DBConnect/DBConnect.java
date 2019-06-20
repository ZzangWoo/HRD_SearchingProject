package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnect {

	public static Connection getConnection() {
		
		Connection conn = null;
		DataSource ds;
		
		try {
			Context context = new InitialContext();
			Context envContext = (Context)context.lookup("java:/comp/env");
			 ds = (DataSource)envContext.lookup("jdbc/team1");
			conn = ds.getConnection();
			
			System.out.println("드라이버커넥션완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return conn;
		
	}
	
}