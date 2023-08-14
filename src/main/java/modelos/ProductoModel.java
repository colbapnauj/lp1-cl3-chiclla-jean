package modelos;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.MysqlConexion;
import entidades.Producto;
import interfaces.ProductoInterface;


public class ProductoModel implements ProductoInterface {

	@Override
	public ArrayList<Producto> listaProductos() {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
				
		try {
			
			cn = MysqlConexion.getConexion();
			String consulta = "-- Consulta de Inventario\r\n"
					+ "SELECT P.producto_id, P.nombre, P.descripcion, P.precio, P.categoria, SUM(I.cantidad) AS stock\r\n"
					+ "FROM Productos P\r\n"
					+ "JOIN Inventario I ON P.producto_id = I.producto_id\r\n"
					+ "GROUP BY P.producto_id, P.nombre, P.precio;";
			ps = cn.prepareStatement(consulta);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int productoId = rs.getInt("producto_id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				String categoria = rs.getString("categoria");
				int stock = rs.getInt("stock");
				
				Producto producto = new Producto(productoId, nombre, descripcion, precio, categoria, stock);
				listaProductos.add(producto);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaProductos;
	}

	@Override
	public boolean actualizarProducto(Producto producto) {
		Connection cn = null;
		PreparedStatement ps = null;
		int value = 0;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String consulta = "UPDATE Productos\r\n"
					+ "SET nombre = ?, descripcion = ?, precio = ?, categoria = ?\r\n"
					+ "WHERE producto_id = ?;";
			ps = cn.prepareStatement(consulta);
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getDescripcion());
			ps.setDouble(3, producto.getPrecio());
			ps.setString(4, producto.getCategoria());
			ps.setInt(5, producto.getIdProducto());
			
			value = ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (cn != null) cn.close();
			} catch (Exception e ) {
				e.printStackTrace();
				
			}
		}
		return value != 0;
	}
	
	@Override
	public Producto obtenerProducto(int idProducto) {
		Producto producto = null;
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = MysqlConexion.getConexion();
			String consulta = "SELECT * FROM Productos WHERE producto_id = ?;";
			ps = cn.prepareStatement(consulta);
			ps.setInt(1, idProducto);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String descripcion= rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				String categoria = rs.getString("categoria");
				
				producto = new Producto();
				producto.setIdProducto(idProducto);
				producto.setNombre(nombre);
				producto.setDescripcion(descripcion);
				producto.setPrecio(precio);
				producto.setCategoria(categoria);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return producto;
	}
}
