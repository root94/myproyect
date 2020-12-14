<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="row">
	<h1 class="col-12">Edición de perrete</h1>

	<div class="col-12">
		<form action="admin/guardar" method="post">
			<input type="hidden" name="id" value="${perro.id}" />

			<%-- <div class="form-group row">
				<label for="id" class="col-sm-2 col-form-label">Id</label>
				<div class="col-sm-10">
					<input type="number" class="form-control ${pelicula.errorId != null ? 'is-invalid' : '' }" id="id" name="id"
						value="${pelicula.id}" readonly>
					<div class="invalid-feedback">${pelicula.errorId}</div>
				</div>
			</div> --%>
			<div class="form-group row">
				<label for="nombre" class="col-sm-2 col-form-label">Raza</label>
				<div class="col-sm-10">

					<select class="form-control" aria-label="Default select example" name="raza">
						<c:forEach items="${razas }" var="raza">
							<option value="${raza.id }" ${raza.id == perro.raza.id? 'selected' : '' }>${raza.nombre }</option>

						</c:forEach>

					</select> 
					<div class="invalid-feedback">${perro.errorRaza}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
				<div class="col-sm-10">
					<input type="text"
						class="form-control ${perro.errorNombre != null ? 'is-invalid' : '' }"
						id="nombre" name="nombre" placeholder="nombre"
						value="${perro.nombre}">
					<div class="invalid-feedback">${perro.errorNombre}</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="fecha-adopcion" class="col-sm-2 col-form-label">Fecha
					de Adopción</label>
				<div class="col-sm-10">
					<input type="date"
						class="form-control ${perro.errorFechaAdopcion != null ? 'is-invalid' : '' }"
						id="fecha-adopcion" name="fecha-adopcion"
						value="${perro.fechaAdopcion}">
					<div class="invalid-feedback">${perro.errorFechaAdopcion}</div>
				</div>
			</div>

			<div class="form-group row">
				<div class="col-sm-10">
					<button type="submit" class="btn btn-primary">Aceptar</button>
					<a class="btn btn-danger" href="perros">Cancelar</a>
				</div>
			</div>
		</form>

	</div>


</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>