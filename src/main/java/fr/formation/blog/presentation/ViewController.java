package fr.formation.blog.presentation;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.blog.BlogConstants;
import fr.formation.blog.metier.Article;
import fr.formation.blog.metier.ArticleService;

/**
 * Controleur des vues responsable de distribuer les bons objets vues/model pour
 * aller vers une page JSP avec les informations dans le model.
 * 
 * L'annotation @Controller défini la classe en tant que bean Spring singleton.
 * L'annotation @RequestMapping défini la classe comme capable de répondre sur
 * les requêtes HTTP commençant par "/".
 */
@Controller
@RequestMapping("/")
public class ViewController {

	/**
	 * Déclaration du Logger pour cette classe.
	 */
	private static final Logger LOGGER = Logger.getLogger(ViewController.class);

	/**
	 * Injection d'une dépendance au service des articles.
	 */
	@Autowired
	private ArticleService service;

	/**
	 * Répond sur "http://localhost:8080/blog/" et
	 * "http://localhost:8080/blog/index.html".
	 * 
	 * @return ModelAndView la vue index.
	 */
	@RequestMapping({ "", "index" })
	public ModelAndView index() {
		LOGGER.debug("Page d'accueil index !");
		ModelAndView mav = new ModelAndView();
		// 1. Configurer la vue.
		mav.setViewName("index");
		// 2. Ajouter les données nécessaires à la vue.
		mav.addObject("articles", this.service.getAll());
		return mav;
	}

	/**
	 * Répond sur "http://localhost:8080/blog/delete.html?id=".
	 * 
	 * @param id l'identifiant de l'article à supprimer.
	 * @return ModelAndView la vue index.
	 */
	@RequestMapping("delete")
	public String delete(@RequestParam Integer id) {
		LOGGER.debug("Action suppression d'un article !");
		this.service.deleteArticle(id);
		// On change pour un type de retour String permettant de renvoyer
		// uniquement le nom de vue de redirection.
		return BlogConstants.REDIRECT_TO_INDEX;
	}

	/**
	 * Répond sur http://localhost:8080/blog/form.html.
	 * 
	 * @return ModelAndView la page contenant le formulaire de création
	 *         d'article.
	 */
	@RequestMapping("form")
	public ModelAndView showCreateForm() {
		LOGGER.debug("Action afficher formulaire de création d'article !");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form");
		return mav;
	}

	/**
	 * Répond sur le "submit" du formulaire sur
	 * http://localhost:8080/blog/form.html.
	 * 
	 * @param article un objet Article avec titre et contenu envoyés dans le
	 *                corps de la requête.
	 * @return String la chaine de redirection vers index.
	 */
	@RequestMapping(path = "form", method = RequestMethod.POST)
	public String validateForm(Article article) {
		this.service.addArticle(article.getTitle(), article.getContent());
		// On change pour un type de retour String permettant de renvoyer
		// uniquement le nom de vue de redirection.
		return BlogConstants.REDIRECT_TO_INDEX;
	}

	/**
	 * 
	 * @param session la session HTTP (que Tomcat avec un JSESSIONID) associée à
	 *                l'utilisateur ayant effectué la requête.
	 * @return String la chaine de redirection vers index.
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		// On change pour un type de retour String permettant de renvoyer
		// uniquement le nom de vue de redirection.
		return BlogConstants.REDIRECT_TO_INDEX;
	}
}
