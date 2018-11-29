package fr.formation.blog.metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Définition d'un POJO (Plain Old Java Object), une classe qui respecte les
 * contraintes suivantes :
 * <ul>
 * <li>Des attributs (fields) uniquement <b>privés</b></li>
 * <li>Un constructeur par défaut (sans arguments)</li>
 * <li>Des accesseurs pour chaque attribut</li>
 * <li style="color:red;">Surtout pas de méthodes de traitement !</li>
 * </ul>
 * 
 * Représentation d'une entité de la base de données liée à la table 'article'.
 */
@Entity
@Table(name = "article")
public class Article {

	/**
	 * Définition d'un attribut Java correspondant à la clé primaire (PK).
	 */
	@Id
	// On précise que cet attribut est une valeur générée par la base de données
	// (Auto-Increment).
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Déclaration du lien avec la colonne 'id' de la table 'article'.
	@Column(name = "id")
	private Integer id;

	/**
	 * Déclaration du lien avec la colonne 'title' de la table 'article'.
	 */
	@Column(name = "title")
	private String title;

	/**
	 * Déclaration du lien avec la colonne 'content' de la table 'article'.
	 */
	@Column
	private String content;

	public Article() {
	}

	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Article(Integer id, String title, String content) {
		this(title, content);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
