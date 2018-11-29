package fr.formation.blog.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.blog.metier.ArticleService;

public class DeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String strId = req.getParameter("id");
		Integer id = Integer.parseInt(strId);
		ArticleService.getInstance().deleteArticle(id);
		resp.sendRedirect(this.getServletContext().getContextPath() + "/index.html");
	}
}
