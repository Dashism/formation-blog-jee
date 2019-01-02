package fr.formation.blog.presentation;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.blog.metier.Article;
import fr.formation.blog.metier.ArticleService;

@Controller
@RequestMapping("/")
public class ViewController {
	
	private static final Logger LOGGER = Logger.getLogger(ViewController.class);

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
	public ModelAndView delete(@RequestParam Integer id) {
		LOGGER.debug("Action suppression d'un article !");
		this.service.deleteArticle(id);
		return this.index();
	}
	
	@RequestMapping("form")
	public ModelAndView showCreateForm() {
		LOGGER.debug("Action afficher formulaire de création d'article !");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form");
		return mav;
	}
	
	@RequestMapping(path="form", method=RequestMethod.POST)
	public ModelAndView validateForm(Article article) {
		this.service.addArticle(article.getTitle(), article.getContent());
		return this.index();
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/index.html");
	}
}
