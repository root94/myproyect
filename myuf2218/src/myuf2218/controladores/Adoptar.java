package myuf2218.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myuf2218.accesoadatos.PerrosDao;
import myuf2218.accesoadatos.PerrosDaoMySql;
import myuf2218.modelos.Perro;

/**
 * Servlet implementation class Adoptar
 */
@WebServlet("/adoptar")
public class Adoptar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Perro perro = PerrosDaoMySql.getDao().obtenerPorId(id);
		perro.setAdoptado(true);
		PerrosDao.getDao().modificar(perro);
		response.sendRedirect(request.getContextPath() + "/perros");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
