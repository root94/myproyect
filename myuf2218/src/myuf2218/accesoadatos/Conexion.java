package myuf2218.accesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String URL = "jdbc:mysql://localhost:3306/perros_bdd?characterEncoding=UTF-8";
	private static final String USER = "debian-sys-maint";
	private static final String PASS = "o8lAkaNtX91xMUcV";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido cargar el driver de MySQL", e);
		}
	}
	
	public static Connection getConexion() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema con la conexión", e);
		}
		return con;
	
	}

}
