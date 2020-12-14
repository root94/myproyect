package myuf2218.accesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import myuf2218.modelos.Perro;
import myuf2218.modelos.Raza;

public class RazasDao implements Dao<Raza>{
	private RazasDao() {
	}

	private static final RazasDao INSTANCIA = new RazasDao();

	public static RazasDao getDao() {
		return INSTANCIA;
	}

	private static final String URL = "jdbc:mysql://localhost:3306/perros_bdd?characterEncoding=UTF-8";
	private static final String USER = "debian-sys-maint";
	private static final String PASS = "o8lAkaNtX91xMUcV";

	private static final String SQL_SELECT = "SELECT * FROM razas r";
	private static final String SQL_SELECT_ID = "SELECT * FROM razas WHERE id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha podido cargar el driver de MySQL", e);
		}
	}

	@Override
	public Iterable<Raza> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT);) {

			ArrayList<Raza> razas = new ArrayList<>();
			
			Raza raza;

			while (rs.next()) {
				raza = new Raza(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
				
				razas.add(raza);
			}

			return razas;
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener todos los registros de razas", e);
		}

	}

	@Override
	public Raza obtenerPorId(Long id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);
			
			try (ResultSet rs = ps.executeQuery()) {

				Raza raza = null;

				if (rs.next()) {
					raza = new Raza(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
					
				}

				return raza;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener la raza cuyo id es " + id, e);
		}
	}

	@Override
	public void insertar(Raza objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(Raza objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		
	}

}
