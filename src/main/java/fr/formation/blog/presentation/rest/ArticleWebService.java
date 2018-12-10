package fr.formation.blog.presentation.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.formation.blog.metier.Article;
import fr.formation.blog.metier.ArticleService;

@Path("/article")
public class ArticleWebService {

	@GET
	public Response list() {
		return Response.status(Status.OK.getStatusCode())
				.entity(ArticleService.getInstance().getAll().toString())
				.build();
	}

	@GET
	@Path("/{id}")
	public Response read(@PathParam("id") Integer id) {
		// Construire l'article à chercher dans la liste.
		Article searchedArticle = new Article();
		// Remplir son identifiant avec celui demandé dans l'URL.
		searchedArticle.setId(id);
		// Je récupère l'indice ou se trouve l'article cherché dans la liste.
		int index = ArticleService.getInstance().getAll()
				.indexOf(searchedArticle);
		if (index >= 0) {
			// Je retrouve l'article demandé dans la liste grâce à l'indice.
			Article article = ArticleService.getInstance().getAll().get(index);

			// Je construis la réponse HTTP avec javax.ws.rs.Response.
			return Response.ok(article.toString()).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	public Response create(@FormParam("title") String title,
			@FormParam("content") String content) {
		Boolean createOk = ArticleService.getInstance().addArticle(title,
				content);
		return Response
				.status(createOk ? Status.OK : Status.INTERNAL_SERVER_ERROR)
				.entity(createOk.toString()).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer id) {
		ArticleService.getInstance().deleteArticle(id);
		return Response.noContent().build();
	}
}
