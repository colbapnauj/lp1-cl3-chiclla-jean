package dao;

import interfaces.ProductoInterface;

public abstract class DAOFactory {
	
	public static final int MYSQL = 1;
	
	public abstract ProductoInterface getProducto();
	
	public static DAOFactory getDaoFactory(int tipo) {
		switch (tipo) {
		case MYSQL:
			return new MySqlDAOFactory();
		default:
			return null;		
		}
	}
}
