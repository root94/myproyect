<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>
${alertamensaje}
<table class="table table-striped table-bordered table-hover table-sm">
	<thead class="thead-dark">
		<tr>
			<th>Id</th>
			<th>Raza</th>
			<th>Nombre</th>
			<c:choose>

				<c:when test="${usuario.admin}">
           			 <th>Fecha de adopción</th>
        		 </c:when>


				<c:otherwise>
            
         	</c:otherwise>
			</c:choose>
		<!--  	<c:if test="usuario.admin">
				<th>Fecha de adopción</th>

			</c:if>-->
			<th>Opciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${perros}" var="perro">
			<c:if test="${usuario.admin || !usuario.admin && !perro.adoptado}">
				<tr>
					<th>${perro.id}</th>
					<td>${perro.raza.nombre}</td>
					<td>${perro.nombre}</td>
					<c:choose>

				<c:when test="${usuario.admin}">
           			 <td><c:if test="${perro.adoptado}">				
								${perro.fechaAdopcion}
						</c:if> <c:if test="${!perro.adoptado}">				
								- NO ADOPTADO
						</c:if></td>
        		 </c:when>


				<c:otherwise>
            	
         	</c:otherwise>
			</c:choose>
					
					
					<!--<c:if test="usuario.admin">
						<td><c:if test="${perro.adoptado}">				
								${perro.fechaAdopcion}
						</c:if> <c:if test="${!perro.adoptado}">				
								- NO ADOPTADO
						</c:if></td>
					</c:if>-->
					<td><c:if test="${usuario.admin }">

							<a class="btn btn-primary" href="admin/guardar?id=${perro.id}">Editar</a>


							<a class="btn btn-danger"
								onclick="return confirm('¿Estás seguro?')"
								href="admin/borrar?id=${perro.id}">Borrar</a>
						</c:if> <c:if test="${!usuario.admin }">

							<a class="btn btn-danger"
								onclick="return confirm('¿Estás seguro?')"
								href="adoptar?id=${perro.id}">Adoptar</a>
						</c:if></td>
				</tr>
			</c:if>
		</c:forEach>
	</tbody>
</table>


<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>