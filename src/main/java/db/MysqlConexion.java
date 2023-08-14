package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConexion {
	
	public static Connection getConexion() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			String url = "jdbc:mysql://localhost:3306/tienda_virtual_cl3_lp1_jchiclla?useSSL=false&useTimezone=true&serverTimezone=UTC";
			String usr = "root";
			String psw = "";
			con = DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException e) {
			System.out.println("Error >> driver no instalado " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error >> de conexión con la DB " + e.getMessage());
		} catch (Exception e) {
			System.out.print("Error >> general: " + e.getLocalizedMessage());
		}
		
		return con;
	}
	
	public static void closeConexion(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Problemas al cerrar la conexion" + e.getMessage());
		}
	}
}
