package fr.formation.blog.presentation.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.formation.blog.metier.Article;
import fr.formation.blog.metier.ArticleService;

@Path("/json")
public class JsonWebService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Article create(Article article) {
		ArticleService.getInstance().addArticle(article.getTitle(), article.getContent());
		// FIXME: Remplir l'attribut id avant de renvoyer.
		return article;
	}
}
