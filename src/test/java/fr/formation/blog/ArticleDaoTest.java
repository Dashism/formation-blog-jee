package fr.formation.blog;

import java.sql.SQLException;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.formation.blog.metier.Article;
import fr.formation.blog.persistance.ArticleDao;
import fr.formation.blog.persistance.MySqlConnection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.*;

/**
 * Vérification des méthodes CRUD de {@link ArticleDao}.
 */
public class ArticleDaoTest {

	/**
	 * Instance unique (singleton) du DAO pour partager avec les différentes
	 * méthodes de tests.
	 */
	private static ArticleDao DAO;

	/**
	 * Initialisation unique pour l'éxécution de toutes les méthodes de test de
	 * cette classe.
	 */
	@BeforeClass
	public static void init() {
		ArticleDaoTest.DAO = new ArticleDao();
	}

	/**
	 * Libération des ressources nécessaires au tests de cette classe.
	 * 
	 * @throws SQLException si la fermeture de la connexion JDBC n'a pas pu être
	 *                      effectuée.
	 */
	@AfterClass
	public static void dispose() throws SQLException {
		MySqlConnection.getInstance().getConn().close();
	}

	@Test
	public void testReadAll() {
		List<Article> articles = ArticleDaoTest.DAO.readAll();
		Assert.assertThat(
				"La méthode readAll du DAO ne lit pas les bonnes informations.",
				articles, allOf(hasSize(2), IsCollectionContaining
						.hasItem(new Article(5, "", ""))));
	}
}
