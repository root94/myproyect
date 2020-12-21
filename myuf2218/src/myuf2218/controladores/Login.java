package myuf2218.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myuf2218.accesoadatos.UsuariosDao;
import myuf2218.accesoadatos.UsuariosDaoSql;
import myuf2218.modelos.Usuario;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Iterable<Usuario> usuarios = UsuariosDaoSql.getDao().obtenerTodos();
		Usuario u = null;
		
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
				u = usuario;		
			} 	
		}
		if (u == null) {
			request.setAttribute("alertamensaje", "El usuario o contraseña son incorrectos");
			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);

		} else {
			request.getSession().setAttribute("usuario", u);
			response.sendRedirect(request.getContextPath() + "/perros");
			
		}
		
		
	}

}
