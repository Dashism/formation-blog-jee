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
	
	public void deleteArticle(Integer id) {
		this.dao.delete(id);
	}
}
