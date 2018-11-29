package fr.formation.blog.persistance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.formation.blog.metier.Article;

public class ArticleDao implements Dao<Article> {

	private final MySqlConnection mysqlConn;
	private final EntityManager em;

	public ArticleDao() {
		this.mysqlConn = MySqlConnection.getInstance();
		this.em = this.mysqlConn.getEntityManager();
	}

	@Override
	public Article create(Article entity) {
		this.em.getTransaction().begin();
		this.em.persist(entity);
		this.em.getTransaction().commit();
		return entity;
	}

	@Override
	public Article read(Integer id) {
		Article result = null;
		result = this.em.find(Article.class, id);
		return result;
	}

	@Override
	public List<Article> readAll() {
		List<Article> results = new ArrayList<>();
		TypedQuery<Article> query = this.em
				.createQuery(JpqlQueries.READL_ALL_ARTICLE, Article.class);
		results.addAll(query.getResultList());
		return results;
	}

	@Override
	public Article update(Article entity) {
		this.em.getTransaction().begin();
		entity = this.em.merge(entity);
		this.em.getTransaction().commit();
		return entity;
	}

	@Override
	public boolean delete(Integer id) {
		boolean result = false;
		this.em.getTransaction().begin();
		Article article = this.read(id);
		this.em.remove(article);
		this.em.getTransaction().commit();
		return result;
	}

}
