package fr.formation.blog.presentation;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Un exemple de service Web ne reposant sur aucun standard particulier à part
 * l'API Servlet.
 * 
 * @author Adminl
 *
 */
public class DataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		Writer writer = resp.getWriter();
		writer.append("{ \"version\": \"1.0.0-alpha\" }");
		writer.flush();
	}
}
