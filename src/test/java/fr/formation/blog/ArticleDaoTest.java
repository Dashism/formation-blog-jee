package fr.formation.blog;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.sql.SQLException;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.formation.blog.metier.Article;
import fr.formation.blog.persistance.ArticleDao;
import fr.formation.blog.persistance.MySqlConnection;

/**
 * Vérification des méthodes CRUD de {@link ArticleDao}.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void test1ReadAll() {
		List<Article> articles = ArticleDaoTest.DAO.readAll();
		Assert.assertThat(
				"La méthode readAll du DAO ne lit pas les bonnes informations.",
				articles, allOf(hasSize(2), IsCollectionContaining
						.hasItem(new Article(5, "", ""))));
	}

	@Test
	public void test2Create() {
		Article a = DAO.create(new Article("TEST JUNIT", "TO DELETE !!"));
		Assert.assertNotNull("Le DAO a renvoyé null, l'article n'est pas créé.",
				a);
		Assert.assertNotNull("Le DAO a renvoyé un article sans identifiant !",
				a.getId());
		// Suppression de l'article ajouté par le test unitaire pour ne pas
		// déranger les résultats des autres tests.
		DAO.delete(a.getId());
	}

	@Test
	public void test3Update() {
		// toCreate -> #1
		Article toCreate = new Article("Article...", "Contenu à mettre à jour");
		ArticleDaoTest.DAO.create(toCreate);
		// toUpdate -> #2
		Article toUpdate = new Article(toCreate.getId(), "Titre à jour",
				"Contenu terminé.");
		ArticleDaoTest.DAO.update(toUpdate);

		Assert.assertNotEquals("Le titre n'a pas été mis à jour",
				toCreate.getTitle(), toUpdate.getTitle());
		
		ArticleDaoTest.DAO.delete(toCreate.getId());
	}

}
