<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="entidades.Producto" %>

<%
	Producto producto = (Producto) request.getAttribute("producto");
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ab_s - <%=(producto != null) ? producto.getNombre() : "Detalle de Producto" %> </title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
	
	<%@ include file="NavBar.jsp" %>

	<div class="container">
		<h1>Producto</h1>
		
		<div class="mb-3">
			<span>${mensajeDeCarga}</span>
		</div>
		
		<form method="post" action="productos">
			<div>
				<input type="text" name="type" value="actualizar" hidden/>
			</div>
			<div>
				<input type="text" name="idProducto"
					value="<%=(producto != null) ? producto.getIdProducto() : "" %>" hidden/>
			</div>
			<div class="mb-3">
				<label class="form-label">Nombre de Producto</label>
				<input type="text" name="txtNombre" class="form-control"
					value="<%=(producto!=null)? producto.getNombre(): "" %>" />
			</div>
			<div class="mb-3">
				<label class="form-label">Descripción de Producto</label>
				<textarea class="form-control"
					name="txtDescripcion"><%=(producto!=null)? producto.getDescripcion() : "" %></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">Precio</label>
				<input type="number" name="txtPrecio" class="form-control" 
					value="<%=(producto != null) ? producto.getPrecio() : "" %>" />
			</div>
			<div class="mb-3">
				<label class="form-label">Categoría</label>
				<input type="text" name="txtCategoria" class="form-control"
					value="<%=(producto != null) ? producto.getCategoria() : "" %>" />
			</div>
			
			<button type="submit" class="btn btn-primary">Actualizar</button>
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>