package myuf2218.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Perro {
	
	private Long id;
	private String  nombre, errorId, errorRaza, errorNombre, errorFechaAdopcion, errorAdoptado;
	private LocalDate fechaAdopcion;
	private boolean adoptado, correcto = true;
	private Raza raza;
	
	public Perro(Long id, Raza raza, String nombre, LocalDate fechaAdopcion, boolean adoptado) {
	
		setId(id);
		setRaza(raza);
		setNombre(nombre);
		setFechaAdopcion(fechaAdopcion);
		setAdoptado(adoptado);
		
	}
	
	public Perro(String id, Raza raza, String nombre, String fechaAdopcion, String adoptado) {
		setId(id);
		setRaza(raza);
		setNombre(nombre);
		setFechaAdopcion(fechaAdopcion);
		setAdoptado(adoptado);
	}

	public Perro() {
		// TODO Auto-generated constructor stub
	}

	private void setAdoptado(String adoptado) {
		boolean perroIsAdoptado;
		try {
			perroIsAdoptado = Boolean.parseBoolean(adoptado);
			setAdoptado(perroIsAdoptado);
		} catch (Exception e) {
			setErrorAdoptado("Solo se admiten valores true o false");
		}
		
	}

	private void setFechaAdopcion(String fechaAdopcion) {
		LocalDate perroFechaAdopcion;
		try {
			perroFechaAdopcion = LocalDate.parse(fechaAdopcion, DateTimeFormatter.ofPattern("yyy-MM-dd"));
			setFechaAdopcion(perroFechaAdopcion);
		} catch (Exception e) {
			setErrorFechaAdopcion("La fecha debe tener un formato 1234-12-12");
		}
		
	}

	private void setId(String id) {
		Long perroId;
		
		try {
			perroId = id.length() == 0 ? null : Long.parseLong(id);
			setId(perroId);
		} catch (Exception e) {
			setErrorId("El id debe ser numérico");
		}
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		
		this.raza = raza;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre == null || nombre.trim().length() < 2) {
			setErrorNombre("No se admiten nombres de menos de 2 caracteres");
		}
		this.nombre = nombre;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		correcto = false;
		this.errorId = errorId;
	}

	public String getErrorRaza() {
		return errorRaza;
	}

	public void setErrorRaza(String errorRaza) {
		correcto = false;
		this.errorRaza = errorRaza;
	}

	public String getErrorNombre() {
		return errorNombre;
	}

	public void setErrorNombre(String errorNombre) {
		correcto = false;
		this.errorNombre = errorNombre;
	}

	public String getErrorFechaAdopcion() {
		return errorFechaAdopcion;
	}

	public void setErrorFechaAdopcion(String errorFechaAdopcion) {
		correcto = false;
		this.errorFechaAdopcion = errorFechaAdopcion;
	}

	public String getErrorAdoptado() {
		return errorAdoptado;
	}

	public void setErrorAdoptado(String errorAdoptado) {
		correcto = false;
		this.errorAdoptado = errorAdoptado;
	}

	public LocalDate getFechaAdopcion() {
		return fechaAdopcion;
	}

	public void setFechaAdopcion(LocalDate fechaAdopcion) {
		this.fechaAdopcion = fechaAdopcion;
	}

	public boolean isAdoptado() {
		return adoptado;
	}

	public void setAdoptado(boolean adoptado) {
		this.adoptado = adoptado;
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	@Override
	public String toString() {
		return "Perro [id=" + id + ", raza=" + raza + ", nombre=" + nombre + ", errorId=" + errorId + ", errorRaza="
				+ errorRaza + ", errorNombre=" + errorNombre + ", errorFechaAdopcion=" + errorFechaAdopcion
				+ ", errorAdoptado=" + errorAdoptado + ", fechaAdopcion=" + fechaAdopcion + ", adoptado=" + adoptado
				+ ", correcto=" + correcto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (adoptado ? 1231 : 1237);
		result = prime * result + (correcto ? 1231 : 1237);
		result = prime * result + ((errorAdoptado == null) ? 0 : errorAdoptado.hashCode());
		result = prime * result + ((errorFechaAdopcion == null) ? 0 : errorFechaAdopcion.hashCode());
		result = prime * result + ((errorId == null) ? 0 : errorId.hashCode());
		result = prime * result + ((errorNombre == null) ? 0 : errorNombre.hashCode());
		result = prime * result + ((errorRaza == null) ? 0 : errorRaza.hashCode());
		result = prime * result + ((fechaAdopcion == null) ? 0 : fechaAdopcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((raza == null) ? 0 : raza.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perro other = (Perro) obj;
		if (adoptado != other.adoptado)
			return false;
		if (correcto != other.correcto)
			return false;
		if (errorAdoptado == null) {
			if (other.errorAdoptado != null)
				return false;
		} else if (!errorAdoptado.equals(other.errorAdoptado))
			return false;
		if (errorFechaAdopcion == null) {
			if (other.errorFechaAdopcion != null)
				return false;
		} else if (!errorFechaAdopcion.equals(other.errorFechaAdopcion))
			return false;
		if (errorId == null) {
			if (other.errorId != null)
				return false;
		} else if (!errorId.equals(other.errorId))
			return false;
		if (errorNombre == null) {
			if (other.errorNombre != null)
				return false;
		} else if (!errorNombre.equals(other.errorNombre))
			return false;
		if (errorRaza == null) {
			if (other.errorRaza != null)
				return false;
		} else if (!errorRaza.equals(other.errorRaza))
			return false;
		if (fechaAdopcion == null) {
			if (other.fechaAdopcion != null)
				return false;
		} else if (!fechaAdopcion.equals(other.fechaAdopcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (raza == null) {
			if (other.raza != null)
				return false;
		} else if (!raza.equals(other.raza))
			return false;
		return true;
	}
	
	
	
	
	

}
