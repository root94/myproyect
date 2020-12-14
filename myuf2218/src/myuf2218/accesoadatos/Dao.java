package myuf2218.accesoadatos;

public interface Dao<T> {
	Iterable<T> obtenerTodos();
	T obtenerPorId(Long id);
	void insertar(T objeto);
	void modificar(T objeto);
	void borrar(Long id);
}
