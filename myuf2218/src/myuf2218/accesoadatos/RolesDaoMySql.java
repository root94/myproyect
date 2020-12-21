package myuf2218.accesoadatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myuf2218.modelos.Rol;
import myuf2218.modelos.Usuario;

public class RolesDaoMySql implements DaoUno<Rol>{
	private RolesDaoMySql() {

	}

	private static final RolesDaoMySql INSTANCIA = new RolesDaoMySql();

	public static RolesDaoMySql getDao() {
		return INSTANCIA;
	}
	
	private static final String SQL_SELECT_ID = "SELECT * FROM roles r WHERE r.id = ?";

	@Override
	public Rol obtenerPorId(Long id) {
		try (Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);
			

			try (ResultSet rs = ps.executeQuery()) {

				Rol rol = null;
				if (rs.next()) {
					rol = new Rol(rs.getLong("r.id"), rs.getString("r.nombre"));
					
				}
				return rol;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener el rol cuyo id es " + id, e);
		}
	}



}
