package fr.formation.blog.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.blog.persistance.ArticleDao;

@Service
public class ArticleService {

	private static final ArticleService INSTANCE = new ArticleService();

	public static ArticleService getInstance() {
		return ArticleService.INSTANCE;
	}

	@Autowired
	private ArticleDao dao;

	public List<Article> getAll() {
		return this.dao.readAll();
	}
	
	public Article getOne(Integer id) {
		return this.dao.read(id);
	}

	public boolean addArticle(String title, String content) {
		Article newArticle = this.dao.create(new Article(title, content));
		return newArticle.getId() != null;
	}

	public boolean updateArticle(Integer id, String title, String content) {
		boolean result = false;
		// Si l'identifiant existe et correspond bien à un article existant.
		if (id != null && this.read(id) != null) {
			this.dao.update(new Article(id, title, content));
			result = true;
		}
		return result;
	}

	private Article read(Integer id) {
		return this.dao.read(id);
	}

	public void deleteArticle(Integer id) {
		this.dao.delete(id);
	}
}
