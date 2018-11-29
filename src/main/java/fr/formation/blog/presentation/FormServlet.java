package fr.formation.blog.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.formation.blog.metier.ArticleService;

public class FormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(FormServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOGGER.debug("Dans FormServlet -> doGet !");
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/form.jsp")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOGGER.debug("Dans FormServlet -> doPost !");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		LOGGER.info(String.format(
				"Récupération des informations du <form> :\n\t- Titre : %s\n\t- Contenu : %s",
				title, content));
		ArticleService service = ArticleService.getInstance();
		service.addArticle(title, content);
		resp.sendRedirect(
				this.getServletContext().getContextPath() + "/index.html");
	}
}
