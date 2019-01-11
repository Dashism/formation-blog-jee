package fr.formation.blog.presentation.rest;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.blog.metier.Article;
import fr.formation.blog.metier.ArticleService;

@RestController
@RequestMapping("/article")
@Transactional(readOnly=true)
@CrossOrigin(origins = { 
		// Pour déploiement avec 'ng serve'.
		"http://localhost:4200",
		// Pour déploiement avec 'ng build --base-href="/blog-angular/"'.
		"http://localhost:8080" })
public class ArticleWebService {
	
	@Autowired
	private ArticleService service;

	@GetMapping
	public List<Article> list() {
		return this.service.readAll();
	}
	
	@PostMapping
	public Article create(@RequestBody Article article) {
		return this.service.create(article);
	}
	
	@GetMapping("/{id}")
	public Article read(@PathVariable Integer id) {
		Article article = this.service.read(id);
		Hibernate.initialize(article);
		return article;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		this.service.delete(id);
	}
	
	@PutMapping("/{id}")
	public Article update(@PathVariable Integer id,
			@RequestBody Article article) {
		return this.service.update(article);
	}
	
}
