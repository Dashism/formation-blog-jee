package fr.formation.blog.persistance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Représentation d'une connexion à MySQL avec l'API JPA.
 */
public class MySqlConnection {

	/**
	 * Déclaration d'un singleton (instance unique) privé.
	 */
	private static final MySqlConnection INSTANCE = new MySqlConnection();

	/**
	 * @return MysqlConnection l'instance unique (singleton) de connexion BDD.
	 */
	public static MySqlConnection getInstance() {
		return MySqlConnection.INSTANCE;
	}

	/**
	 * Il est nécessaire de mémoriser quelque part l'instance unique de
	 * EntityManagerFactory.
	 */
	private EntityManagerFactory entityManagerFactory;

	/**
	 * Constructeur par défaut. Récupère l'instance d'EntityManagerFactory
	 * associée à notre unité de persistence 'blog' définie dans
	 * persistence.xml.
	 */
	public MySqlConnection() {
		this.entityManagerFactory = Persistence
				.createEntityManagerFactory("blog");
	}

	/**
	 * @return EntityManager une instance capable d'effectuer les opérations
	 *         CRUD sur la base de données.
	 */
	public EntityManager getEntityManager() {
		return this.entityManagerFactory.createEntityManager();
	}

}
