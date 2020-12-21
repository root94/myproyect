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
import myuf2218.modelos.Rol;
import myuf2218.modelos.Usuario;

public class UsuariosDaoSql implements Dao<Usuario> {
	private UsuariosDaoSql() {

	}

	private static final UsuariosDaoSql INSTANCIA = new UsuariosDaoSql();

	public static UsuariosDaoSql getDao() {
		return INSTANCIA;
	}

	private static final String SQL_SELECT = "SELECT * FROM usuarios u JOIN roles r ON u.rol = r.id";
	private static final String SQL_SELECT_ID = "SELECT * FROM usuarios u JOIN roles r ON u.rol = r.id where u.id = ?";


	@Override
	public Iterable obtenerTodos() {
		try (Connection con = Conexion.getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT);) {

			ArrayList<Usuario> usuarios = new ArrayList<>();
			Usuario  usuario;
			Rol rol;

			while (rs.next()) {
				rol = new Rol(rs.getLong("r.id"), rs.getString("r.nombre"));
				usuario = new Usuario(rs.getLong("u.id"), rs.getString("u.nombre"), rs.getString("u.email"),rs.getString("u.password"),rol);
				
				usuarios.add(usuario);
			}

			return usuarios;

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener todos los registros de usuarios", e);
		}
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		try (Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);
			

			try (ResultSet rs = ps.executeQuery()) {

				Usuario usuario = null;
				if (rs.next()) {
					Rol rol = RolesDaoMySql.getDao().obtenerPorId(rs.getLong("u.rol"));
					usuario = new Usuario(rs.getLong("u.id"), rs.getString("u.nombre"), rs.getString("u.email"),rs.getString("u.password"),rol);
					
				}
				return usuario;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener el perro cuyo id es " + id, e);
		}
	}

	@Override
	public void insertar(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificar(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub

	}

}
