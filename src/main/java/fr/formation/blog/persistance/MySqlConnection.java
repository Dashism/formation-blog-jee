package fr.formation.blog.persistance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MySqlConnection {

	private static final MySqlConnection INSTANCE = new MySqlConnection();
	
	public static MySqlConnection getInstance() {
		return MySqlConnection.INSTANCE;
	}
	
	private EntityManagerFactory entityManagerFactory;
	
	public MySqlConnection() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("blog");
	}

	public EntityManager getEntityManager() {
		return this.entityManagerFactory.createEntityManager();
	}
	
}
