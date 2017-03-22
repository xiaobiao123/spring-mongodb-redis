package thread.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
		@Override
		protected Connection initialValue() {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
				System.out.println(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
	};

	public static Connection getConnection() {
		return connectionHolder.get();
	}

	public static void setConnection(Connection conn) {
		connectionHolder.set(conn);
	}
	
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection connection=ConnectionManager.getConnection();

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Connection connection2=ConnectionManager.getConnection();

		try {
			connection2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}