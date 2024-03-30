package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String banco = "jdbc:postgresql://localhost:5433/posjava";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
				System.out.println("Conectado ao banco!");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SingleConnection() {
		conectar();
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	
	

}
