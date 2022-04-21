package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
	public static Connection con = null;
	private static ConnectSQL instance = new ConnectSQL();
	public static ConnectSQL getInstance() {
		return instance;
	}
	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName= QLSinhVien";
		String user = "sa";
		String password = "sapassword";
		con = DriverManager.getConnection(url, user, password);
		
	}
	public void disconnect() {
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	public static Connection getConnection() {
		return con;
	}
}
