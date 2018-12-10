package fr.formation.blog.persistance;

public class SqlQueries {

	public static final String READ_ALL_ARTICLE = "SELECT * FROM Article;";

	public static final String UPDATE_ARTICLE = "UPDATE Article SET title='%s', content='%s' WHERE id=%s;";

	public static final String DELETE_ARTICLE = "DELETE FROM Article WHERE id=%s;";
}
