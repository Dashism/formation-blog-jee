package fr.formation.blog.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
// Déclaration des attributs faisant partie de la session utilisateur.
@SessionAttributes({ "author" })
// Déclaration d'une portée pour ce controller permettant d'être unique par session utilisateur.
@Scope("session")
@Transactional(readOnly=true)
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
	 * Valeur initiale de l'attribut en session (non obligatoire).
	 * 
	 * @return String une valeur par défaut arbitraire.
	 */
	@ModelAttribute("author")
	public String defaultAuthor() {
		return "DEFAULT_AUTHOR";
	}

	/**
	 * Répond sur "http://localhost:8080/blog/" et
	 * "http://localhost:8080/blog/index.html".
	 * 
	 * @return ModelAndView la vue index.
	 */
	@RequestMapping({ "", "index" })
	public ModelAndView index(HttpServletRequest request,
			@RequestParam(required = false) String message) {
		LOGGER.debug("Page d'accueil index !");
		LOGGER.debug("Message récupéré après redirection ? '" + message + "'");
		ModelAndView mav = new ModelAndView();
		// Il suffit d'ajouter la clé "author" au model pour que la valeur soit
		// conservée en session (grâce à l'annotation sur la classe).
		mav.addObject("author", request.getUserPrincipal().getName());
		// 1. Configurer la vue.
		mav.setViewName("index");
		// 2. Ajouter les données nécessaires à la vue.
		mav.addObject("articles", this.service.readAll());
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
		this.service.delete(id);
		// On change pour un type de retour String permettant de renvoyer
		// uniquement le nom de vue de redirection.
		return BlogConstants.REDIRECT_TO_INDEX;
	}

	/**
	 * Répond sur http://localhost:8080/blog/form.html. Prépare le formulaire
	 * Spring de la JSP avec une nouvelle instance d'Article vide, prête à
	 * recevoir les informations qui seront saisies par l'utilisateur.
	 * 
	 * @return ModelAndView la page contenant le formulaire de création
	 *         d'article.
	 */
	@RequestMapping("form")
	public ModelAndView showCreateForm() {
		LOGGER.debug("Action afficher formulaire de création d'article !");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form");
		// Préremplir le model avec un article existant pour le form:form de
		// Spring.
		mav.addObject("article", new Article());
		// On ajoute isEdit à faux pour l'affichage du titre de création dans la
		// JSP.
		mav.addObject("isEdit", false);
		return mav;
	}

	/**
	 * Répond sur http://localhost:8080/blog/form-edit.html?id=xx. Prépare le
	 * formulaire Spring de la JSP avec l'article existant chargé depuis la BDD.
	 * 
	 * @return ModelAndView la page contenant le formulaire de création
	 *         d'article.
	 */
	@RequestMapping("form-edit")
	public ModelAndView showEditForm(Integer id) {
		LOGGER.debug(
				"Action afficher formulaire de modification d'un article !");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form");
		// Préremplir le model avec l'article existant chargé depuis la BDD.
		Article article = this.service.read(id);
		Hibernate.initialize(article);
		mav.addObject("article", article);
		// On ajoute isEdit à vrai pour l'affichage du titre de modification (+
		// le champ caché pour l'id) dans la JSP.
		mav.addObject("isEdit", true);
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
	public String validateForm(Article article, RedirectAttributes attributes) {
		String message = null;
		// Si l'identifiant est null alors on peut effectuer la création, et si
		// la création
		// renvoie vrai alors on met le message de succès, sinon on passe au
		// else if suivant.
		if (article.getId() == null && this.service.create(article) != null) {
			message = "Article bien ajouté !";
		} else if (article.getId() != null
				&& this.service.update(article) != null) {
			message = "Article bien modifié !";
		} else {
			message = "Erreur : article non ajouté ou non modifié...";
		}
		// Utilisation des attributs flash de redirection (pas visible dans
		// l'URL, contrairement aux attributs de redirection normaux).
		// Le message sera reçu par le nouveau paramètre "message" de la méthode
		// index (ciblée par la redirection).
		attributes.addFlashAttribute("message", message);
		// On change pour un type de retour String permettant de renvoyer
		// uniquement le nom de vue de redirection.
		return BlogConstants.REDIRECT_TO_INDEX;
	}

	/**
	 * Déconnexion avec invalidation de la session et des attributs de session
	 * Spring.
	 * 
	 * @param session la session HTTP (que Tomcat avec un JSESSIONID) associée à
	 *                l'utilisateur ayant effectué la requête.
	 * @return String la chaine de redirection vers index.
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session, SessionStatus status) {
		session.invalidate();
		// Ajout pour les session attributes comme "author" permettant de
		// nettoyer les informations associées à la session.
		status.setComplete();
		// On change pour un type de retour String permettant de renvoyer
		// uniquement le nom de vue de redirection.
		return BlogConstants.REDIRECT_TO_INDEX;
	}
}
