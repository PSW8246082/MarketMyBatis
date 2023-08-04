package common;

import java.sql.*;

public class JDBCTemplate {

	//싱글톤 적용
	private static JDBCTemplate instance;
	private static Connection conn;
	
	
	
	public JDBCTemplate() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	//연결
	public Connection createConnection()
	{
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "MEMBERWEB";
		String password = "MEMBERWEB";
		
		try {
			if(conn == null || conn.isClosed()) {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);  //오토커밋 해제
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//연결 해제
	public void close(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	//커밋하기
	public void comit(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed()) {
					conn.commit();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//롤백하기
	public void rollback(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
