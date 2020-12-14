package myuf2218.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myuf2218.accesoadatos.AccesoDatosException;
import myuf2218.accesoadatos.PerrosDaoMySql;
import myuf2218.accesoadatos.RazasDao;
import myuf2218.modelos.Perro;
import myuf2218.modelos.Raza;

/**
 * Servlet implementation class Guardar
 */
@WebServlet("/admin/guardar")
public class Guardar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("razas", RazasDao.getDao().obtenerTodos());
		if (request.getParameter("id") != null) {
			String id = request.getParameter("id");
			Perro perro = PerrosDaoMySql.getDao().obtenerPorId(Long.parseLong(id));
			request.setAttribute("perro", perro);
		}

		request.getRequestDispatcher("/WEB-INF/vistas/admin/perro.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		Raza raza = RazasDao.getDao().obtenerPorId(Long.parseLong(request.getParameter("raza")));
		String fechaAdopcion = request.getParameter("fecha-adopcion");

		// CAMBIAR LO DE LA RAZA

		Perro perro = new Perro(id, raza, nombre, fechaAdopcion, "false");

		if (!perro.isCorrecto()) {
			request.setAttribute("razas", RazasDao.getDao().obtenerTodos());
			request.setAttribute("perro", perro);
			request.getRequestDispatcher("/WEB-INF/vistas/admin/perro.jsp").forward(request, response);
			return;
		}

		String alertaMensaje, alertaTipo, op = null;

		try {
			if (perro.getId() == null) {
				op = "inserción";
				PerrosDaoMySql.getDao().insertar(perro);
			} else {
				op = "modificación";
				PerrosDaoMySql.getDao().modificar(perro);
			}
			alertaMensaje = "La " + op + " se ha hecho correctamente";
			alertaTipo = "success";
		} catch (AccesoDatosException e) {
			alertaMensaje = "Ha habido un error en la " + op + ": " + e.getMessage();
			alertaTipo = "danger";
		}

		HttpSession session = request.getSession();

		session.setAttribute("alertamensaje", alertaMensaje);
		session.setAttribute("alertatipo", alertaTipo);

		// 5. Redireccionar a la siguiente pantalla
		response.sendRedirect(request.getContextPath() + "/perros");

	}

}
