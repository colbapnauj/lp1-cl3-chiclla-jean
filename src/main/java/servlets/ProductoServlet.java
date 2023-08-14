package servlets;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import dao.DAOFactory;
import interfaces.ProductoInterface;
import entidades.Producto;

@WebServlet("/productos")
@MultipartConfig
public class ProductoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private static final boolean debugMode = false;
	
	public ProductoServlet() {
        super();
    }
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String detalle = req.getParameter("detalle");
		
		if (detalle != null) {
			detalleProducto(req, resp);
			return;
		}
		
		String type = req.getParameter("type");
		
		if (type == null) {
			configuracionInicial(req, resp);
			return;
		}
		
		switch (type) {
		case "obtener": 
			obtenerProducto(req, resp);
			break;
		case "actualizar":
			actualizarProducto(req, resp);
			break;
		default:
			configuracionInicial(req, resp);
		}
	}
	
	protected void configuracionInicial(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface dao = daoFactory.getProducto();
		
		ArrayList<Producto> listaProductos = dao.listaProductos();
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
						
			Gson gson = new Gson();
			String json= gson.toJson(listaProductos);
			PrintWriter out = resp.getWriter();
			out.write(json);
			
			return;
		}
		
		req.setAttribute("listaProductos", listaProductos);
		req.getRequestDispatcher("ListaProductos.jsp").forward(req, resp);
	}
	
	
	protected void actualizarProducto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idProductoStr = req.getParameter("idProducto");
		String nombre = req.getParameter("txtNombre");
		String descripcion = req.getParameter("txtDescripcion");
		String precio = req.getParameter("txtPrecio");
		String categoria = req.getParameter("txtCategoria");
		
		Producto producto = new Producto();
		producto.setIdProducto(Integer.parseInt(idProductoStr));
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(Double.parseDouble(precio));
		producto.setCategoria(categoria);
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface dao = daoFactory.getProducto();

		dao.actualizarProducto(producto);
		
		req.setAttribute("mensaje", "Producto actualizado");
		
		// cambiar de ruta
		String projectPath = getServletContext().getContextPath();
		resp.sendRedirect(projectPath  + "/productos");
	
	}
	
	protected void obtenerProducto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idStr = req.getParameter("idProducto");
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface dao = daoFactory.getProducto();
		Producto producto = dao.obtenerProducto(Integer.parseInt(idStr));
		
		if (producto != null) {
			req.setAttribute("producto", producto);
		}
		
		configuracionInicial(req, resp);
	}
	
	protected void detalleProducto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idStr = req.getParameter("detalle");
		
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface dao = daoFactory.getProducto();
		Producto producto = dao.obtenerProducto(Integer.parseInt(idStr));
		
		if (debugMode) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
						
			Gson gson = new Gson();
			String json= gson.toJson(producto);
			PrintWriter out = resp.getWriter();
			out.write(json);
			
			return;
		}
		
		if (producto != null) {
			req.setAttribute("mensajeDeCarga", "Producto con id: " + producto.getIdProducto());
			req.setAttribute("producto", producto);
			req.getRequestDispatcher("DetalleProducto.jsp").forward(req, resp);
		}
	}
}
