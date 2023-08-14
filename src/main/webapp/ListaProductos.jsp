<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidades.Producto" %>
<%@ page import="java.util.List" %>

<%
	List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaProductos");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ab_s Productos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>

	<%@ include file="NavBar.jsp" %>

	<div class="container">
		<table class="table mt-3">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Nombre</th>
					<th scope="col">Descripción</th>
					<th scope="col">Precio</th>
					<th scope="col">Categoría</th>
					<th scope="col">Stock (unid)</th>
					<th scope="col">Acciones</th>
				<tr>
			</thead>
			<tbody>
	
	<%
		if (listaProductos != null) {
			for (Producto producto : listaProductos) {	
	%>
			
			
				<tr>
					<th scope="row"><%=producto.getIdProducto() %>
					<td><%=producto.getNombre() %></td>
					<td><%=producto.getDescripcion() %></td>
					<td><%=producto.getPrecio() %></td>
					<td><%=producto.getCategoria() %></td>
					<td><%=producto.getStock() %></td>
					<td><a href="productos?detalle=<%=producto.getIdProducto() %>">Editar</a>
				</tr>
	<%
			}
		}
	%>
			</tbody>
		</table>
	
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>