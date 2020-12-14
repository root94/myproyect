package myuf2218.accesoadatos;

import java.time.LocalDate;
import java.util.TreeMap;

import myuf2218.modelos.Perro;
import myuf2218.modelos.Raza;

public class PerrosDao implements Dao<Perro>{
	
	private TreeMap<Long, Perro> perros = new TreeMap<>();
	
	private PerrosDao() {
		perros.put(1L, new Perro(1L, RazasDao.getDao().obtenerPorId(1L),"Bruno",  LocalDate.of(2012, 6, 5),true));
		perros.put(2L, new Perro(2L, RazasDao.getDao().obtenerPorId(2L), "Boss", LocalDate.of(2019, 1, 2),false));
	}
	
	private static PerrosDao dao = new PerrosDao();
	
	public static PerrosDao getDao() {
		return dao;
	}

	@Override
	public Iterable<Perro> obtenerTodos() {
		return perros.values();
	}

	@Override
	public Perro obtenerPorId(Long id) {
		return perros.get(id);
	}

	@Override
	public void insertar(Perro perro) {
		Long id = perros.lastKey() + 1;
		perro.setId(id);
		perros.put(perro.getId(), perro);
		
	}

	@Override
	public void modificar(Perro perro) {
		if (!perros.containsKey(perro.getId())) {
			throw new AccesoDatosException("No existe ese id");
		}
		perros.put(perro.getId(), perro);
		
	}

	@Override
	public void borrar(Long id) {
		if (!perros.containsKey(id)) {
			throw new AccesoDatosException("No existe ese id");
		}
		perros.remove(id);
	}

}
