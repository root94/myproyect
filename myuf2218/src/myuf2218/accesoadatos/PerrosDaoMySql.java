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

public class PerrosDaoMySql implements Dao<Perro> {
	private PerrosDaoMySql() {
	}

	private static final PerrosDaoMySql INSTANCIA = new PerrosDaoMySql();

	public static PerrosDaoMySql getDao() {
		return INSTANCIA;
	}
	private static final String SQL_SELECT = "SELECT * FROM perros p JOIN razas r ON p.raza = r.id";
	private static final String SQL_SELECT_ID = "SELECT * FROM perros p JOIN razas r ON p.raza = r.id WHERE p.id = ?";
	private static final String SQL_INSERT = "INSERT INTO perros (raza, nombre, adoptado) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE perros SET raza = ?, nombre = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM perros WHERE id = ?";
	

	@Override
	public Iterable<Perro> obtenerTodos() {

		try (Connection con = Conexion.getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT);) {

			ArrayList<Perro> perros = new ArrayList<>();
			Perro perro;
			Raza raza;

			while (rs.next()) {
				raza = new Raza(rs.getLong("r.id"), rs.getString("r.nombre"), rs.getString("r.descripcion"));
				perro = new Perro();
				//perro = new Perro(rs.getLong("p.id"), raza, rs.getString("p.nombre"),
				//		rs.getDate("p.fecha-adopcion").toLocalDate(), rs.getBoolean("p.adoptado"));
				perro.setId(rs.getLong("p.id"));
				perro.setNombre(rs.getString("p.nombre"));
				if (rs.getDate("p.fecha-adopcion") != null) {
					perro.setFechaAdopcion(rs.getDate("p.fecha-adopcion").toLocalDate());
				}
				//perro.setFechaAdopcion(rs.getDate("p.fecha-adopcion").toLocalDate());
				perro.setAdoptado(rs.getBoolean("p.adoptado"));
				perro.setRaza(raza);
				perros.add(perro);
			}

			return perros;

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener todos los registros de perros", e);
		}

	}

	@Override
	public Perro obtenerPorId(Long id) {
		try (Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);
			

			try (ResultSet rs = ps.executeQuery()) {

				Perro perro = null;
				Raza raza = null;
				if (rs.next()) {
					raza = new Raza(rs.getLong("r.id"), rs.getString("r.nombre"), rs.getString("r.descripcion"));
					perro = new Perro();
					perro.setId(rs.getLong("p.id"));
					perro.setNombre(rs.getString("p.nombre"));
					if (rs.getDate("p.fecha-adopcion") != null) {
						perro.setFechaAdopcion(rs.getDate("p.fecha-adopcion").toLocalDate());
					}
					perro.setAdoptado(rs.getBoolean("p.adoptado"));
					perro.setRaza(raza);
				}

				return perro;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener el perro cuyo id es " + id, e);
		}
	}

	@Override
	public void insertar(Perro perro) {
		try (Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {
			ps.setLong(1, perro.getRaza().getId());
			ps.setString(2, perro.getNombre());
			ps.setBoolean(3, false);
			

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados == 0) {
				throw new AccesoDatosException("No se ha conseguido insertar el registro");
			} else if (numeroRegistrosInsertados > 1) {
				throw new AccesoDatosException("SE HA INSERTADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al insertar el perro", e);
		}

	}

	@Override
	public void modificar(Perro perro) {
		try (Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {
			// UPDATE perros SET raza = ?, nombre = ? WHERE id = ?

			ps.setLong(1, perro.getRaza().getId());
			ps.setString(2, perro.getNombre());
			ps.setLong(3, perro.getId());

			int numeroRegistrosModificados = ps.executeUpdate();

			if (numeroRegistrosModificados == 0) {
				throw new AccesoDatosException("No se ha encontrado el registro a modificar");
			} else if (numeroRegistrosModificados > 1) {
				throw new AccesoDatosException("SE HA MODIFICADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al modificar el perro", e);
		}

	}

	@Override
	public void borrar(Long id) {
		try (Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {

			ps.setLong(1, id);

			int numeroRegistrosBorrados = ps.executeUpdate();

			if (numeroRegistrosBorrados == 0) {
				throw new AccesoDatosException("Se ha intentado borrar un id que no existe");
			} else if (numeroRegistrosBorrados > 1) {
				throw new AccesoDatosException("SE HA BORRADO MÁS DE UN REGISTRO");
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un problema al obtener el perro cuyo id es " + id, e);
		}

	}

}
