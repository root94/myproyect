package myuf2218.accesoadatos;

import java.util.TreeMap;

import myuf2218.modelos.Usuario;

public class UsuariosDao implements Dao<Usuario>{
private TreeMap<Long, Usuario> usuarios = new TreeMap<>();
	
	private UsuariosDao() {
		usuarios.put(1L, new Usuario(1L, "ruth@ruth.com", "ruth", "ruth",true));
		usuarios.put(2L, new Usuario(2L, "pepe@pepe.com","pepe" ,"ruth",false));
	}
	
	private static UsuariosDao dao = new UsuariosDao();
	
	public static UsuariosDao getDao() {
		return dao;
	}

	@Override
	public Iterable<Usuario> obtenerTodos() {
		return usuarios.values();
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		return usuarios.get(id);
	}

	@Override
	public void insertar(Usuario objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(Usuario objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		
	}

}
