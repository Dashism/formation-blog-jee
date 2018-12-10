package fr.formation.blog.metier;

import java.util.List;

import fr.formation.blog.persistance.ArticleDao;

public class ArticleService {

	private static final ArticleService INSTANCE = new ArticleService();
	
	public static ArticleService getInstance() {
		return ArticleService.INSTANCE;
	}
	
	private final ArticleDao dao;
	
	public ArticleService() {
		this.dao = new ArticleDao();
	}
	
	public List<Article> getAll() {
		return this.dao.readAll();
	}
	
	public boolean addArticle(String title, String content) {
		Article newArticle = this.dao.create(new Article(title, content));
		return newArticle.getId() != null;
	}
	
	public boolean updateArticle(Integer id, String title, String content) {
		boolean result = false;
		// Si l'identifiant correspond bien à un article existant.
		// TODO : appeler dao.read(id).
		if (id != null) {
			this.dao.update(new Article(id, title, content));
			result = true;
		}
		return result;
	}
	
	public void deleteArticle(Integer id) {
		this.dao.delete(id);
	}
}
