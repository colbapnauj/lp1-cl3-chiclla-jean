package interfaces;

import java.util.ArrayList;

import entidades.Producto;

public interface ProductoInterface {

	public ArrayList<Producto> listaProductos();
	
	public boolean actualizarProducto(Producto producto);
	
	public Producto obtenerProducto(int idProducto);
}
