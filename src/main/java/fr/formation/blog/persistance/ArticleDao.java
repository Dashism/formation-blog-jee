package fr.formation.blog.persistance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.formation.blog.metier.Article;

/**
 * Implémentation d'un DAO pour l'entité Article. On utilise l'instance de
 * EntityManager pour effectuer des actions CRUD dans le contexte de persistence
 * JPA. Ce contexte de persistence est synchronisé en continue avec les
 * informations dans la base de données.
 */
public class ArticleDao implements Dao<Article> {

	private final MySqlConnection mysqlConn;
	private final EntityManager em;

	public ArticleDao() {
		this.mysqlConn = MySqlConnection.getInstance();
		this.em = this.mysqlConn.getEntityManager();
	}

	/**
	 * {@inheritDoc} Utilise EntityManager.persist pour ajouter l'entité dans le
	 * contexte de persistence JPA.
	 */
	@Override
	public Article create(Article entity) {
		this.em.getTransaction().begin();
		this.em.persist(entity);
		this.em.getTransaction().commit();
		return entity;
	}

	/**
	 * {@inheritDoc} Utilise EntityManager.read pour lire l'entité en BDD et la
	 * placer dans le contexte de persistence JPA.
	 */
	@Override
	public Article read(Integer id) {
		Article result = null;
		result = this.em.find(Article.class, id);
		return result;
	}

	/**
	 * {@inheritDoc} Utilise EntityManager pour éxécuter une requête JPQL avec
	 * getResultList() pour récupérer plusieurs résultats. Place aussi toutes
	 * les entités lues dans le contexte de persistence JPA.
	 */
	@Override
	public List<Article> readAll() {
		List<Article> results = new ArrayList<>();
		TypedQuery<Article> query = this.em
				.createQuery(JpqlQueries.READL_ALL_ARTICLE, Article.class);
		results.addAll(query.getResultList());
		return results;
	}

	/**
	 * {@inheritDoc} Utilise EntityManager.merge pour mettre à jour l'entité
	 * dans le contexte de persistence JPA.
	 */
	@Override
	public Article update(Article entity) {
		this.em.getTransaction().begin();
		entity = this.em.merge(entity);
		this.em.getTransaction().commit();
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
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
