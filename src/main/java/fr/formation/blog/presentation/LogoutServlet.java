package fr.formation.blog.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Déconnexion provoquée par l'invalidation de la session utilisateur
		// (JSESSIONID).
		req.getSession().invalidate();
		// Redirection vers une page protégée du site pour redéclencher la
		// sécurité et la redirection vers la page de login.
		resp.sendRedirect(
				this.getServletContext().getContextPath() + "/index.html");
	}
}
