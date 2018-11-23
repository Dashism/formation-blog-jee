package fr.formation.blog.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.blog.metier.ArticleService;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Récupération du singleton d'ArticleService.
		ArticleService service = ArticleService.getInstance();
		// Ajout d'un attribut de requête comportant la liste des articles.
		// La clé "articles" utilisée permettra l'utilisation de l'expression
		// ${articles} dans la index.jsp.
		req.setAttribute("articles", service.getAll());
		// Continuer (forward) les traitements avec la JSP donnée.
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/index.jsp")
				.forward(req, resp);
	}
}
