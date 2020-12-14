package myuf2218.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myuf2218.accesoadatos.AccesoDatosException;
import myuf2218.accesoadatos.PerrosDao;
import myuf2218.accesoadatos.PerrosDaoMySql;

/**
 * Servlet implementation class Borrar
 */
@WebServlet("/admin/borrar")
public class Borrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		Long id = Long.parseLong(sId);

		String alertaMensaje, alertaTipo;

		try {
			PerrosDaoMySql.getDao().borrar(id);
			alertaMensaje = "Registro " + id + " borrado correctamente";
			alertaTipo = "success";

		} catch (AccesoDatosException e) {
			alertaMensaje = "El registro a borrar no existe";
			alertaTipo = "warning";
		}

		HttpSession session = request.getSession();

		session.setAttribute("alertatipo", alertaTipo);
		session.setAttribute("alertamensaje", alertaMensaje);

		response.sendRedirect(request.getContextPath() + "/perros");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
