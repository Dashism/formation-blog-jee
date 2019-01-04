package fr.formation.blog.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.formation.blog.persistance.ArticleDao;

@Service
public class ArticleService extends RestService<Article> {

	@Autowired
	private ArticleDao dao;

	@Override
	protected JpaRepository<Article, Integer> getDao() {
		return this.dao;
	}

}
