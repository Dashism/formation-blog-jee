package fr.formation.blog.presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.formation.blog.metier.Article;
import fr.formation.blog.metier.ArticleService;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(IndexServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOGGER.debug("IndexServlet -> doGet !");
		// Récupération du singleton d'ArticleService.
		ArticleService service = ArticleService.getInstance();
		// Ajout d'un attribut de requête comportant la liste des articles.
		// La clé "articles" utilisée permettra l'utilisation de l'expression
		// ${articles} dans la index.jsp.
		List<Article> articles = service.getAll();
		LOGGER.info("Chargement des articles depuis la BDD : " + articles.size());
		req.setAttribute("articles", articles);
		// Continuer (forward) les traitements avec la JSP donnée.
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/index.jsp")
				.forward(req, resp);
	}
}
