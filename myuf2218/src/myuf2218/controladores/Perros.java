package myuf2218.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myuf2218.accesoadatos.PerrosDao;
import myuf2218.accesoadatos.PerrosDaoMySql;


@WebServlet("/perros")
public class Perros extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("perros", PerrosDaoMySql.getDao().obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/listadoperros.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
