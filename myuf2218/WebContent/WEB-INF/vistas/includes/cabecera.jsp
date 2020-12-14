<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<base href="${pageContext.request.contextPath}/" />

<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
<title>Perretes</title>
</head>
<body>
	<nav id="menu-principal"
		class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
		<a class="navbar-brand" href="#">Perretes</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<c:if test="${usuario.admin}">
					<li class="nav-item"><a class="nav-link" href="perros">Listado
							de perros</a></li>
					<li class="nav-item"><a class="nav-link" href="admin/guardar">Añadir
							perro</a></li>
				</c:if>
			</ul>
			<c:if test="${usuario != null}">
			<ul class="navbar-nav">
				
						<li class="nav-item navbar-text">${usuario.email}</li>
						<li class="nav-item"><a class="nav-link" href="logout">Desconectar</a></li>
				
					
			</ul>
			</c:if>
		</div>
	</nav>

	<%-- <nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Inicio</a></li>
			<li class="breadcrumb-item"><a href="#">Segundo nivel</a></li>
			<li class="breadcrumb-item active" aria-current="page">Bootstrap</li>
		</ol>
	</nav>
	
	------------------------
	
	
	<c:if test="${alertamensaje != null}">
		<div class="alert alert-${alertatipo} alert-dismissible fade show"
			role="alert">
			${alertamensaje}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<%
			session.removeAttribute("alertamensaje");
		session.removeAttribute("alertatipo");
		%>
	</c:if>
	
	--%>

	<%-- ${pageContext.request.contextPath} --%>

	<main class="container">