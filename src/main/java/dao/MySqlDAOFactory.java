package dao;

import interfaces.ProductoInterface;
import modelos.ProductoModel;

public class MySqlDAOFactory extends DAOFactory {
	
	@Override
	public ProductoInterface getProducto() {
		return new ProductoModel();
	}
}
