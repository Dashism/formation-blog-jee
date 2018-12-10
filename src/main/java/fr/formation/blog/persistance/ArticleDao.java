package fr.formation.blog.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.formation.blog.metier.Article;

public class ArticleDao implements Dao<Article> {

	private final MySqlConnection mysqlConn;

	public ArticleDao() {
		this.mysqlConn = MySqlConnection.getInstance();
	}

	@Override
	public Article create(Article entity) {
		try {
			Statement st = this.mysqlConn.getConn().createStatement();
			String query = String.format(SqlQueries.UPDATE_ARTICLE,
					entity.getTitle(), entity.getContent());
			System.out.println("Requête créer un article : " + query);
			st.execute(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			Integer articleId = rs.getInt("GENERATED_KEY");
			entity.setId(articleId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Article read(Integer id) {
		Article result = null;
		try {
			Statement st = this.mysqlConn.getConn().createStatement();
			ResultSet rs = st.executeQuery(SqlQueries.READ_ALL_ARTICLE);
			rs.next();
			String title = rs.getString("title");
			String content = rs.getString("content");
			result = new Article(id, title, content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Article> readAll() {
		List<Article> results = new ArrayList<>();
		try {
			Statement st = this.mysqlConn.getConn().createStatement();
			ResultSet rs = st.executeQuery(SqlQueries.READ_ALL_ARTICLE);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				results.add(new Article(id, title, content));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public Article update(Article entity) {
		Article updated = null;
		try {
			Statement st = this.mysqlConn.getConn().createStatement();
			int rows = st.executeUpdate(String.format(SqlQueries.UPDATE_ARTICLE,
					entity.getTitle(), entity.getContent(), entity.getId()));
			if (rows > 0) {
				updated = entity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public boolean delete(Integer id) {
		boolean result = false;
		try {
			Statement st = this.mysqlConn.getConn().createStatement();
			int rows = st.executeUpdate(
					String.format(SqlQueries.DELETE_ARTICLE, id));
			if (rows > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
