package fr.formation.blog.persistance;

import java.util.List;

/**
 * Définition d'une interface comportant les 4 méthodes élémentaires de
 * manipulation de données (C.R.U.D) + readAll.
 *
 * @param <T> classe qui représente le type de données manipulé par le DAO.
 */
public interface Dao<T> {

	/**
	 * Crée une nouvelle entité en base de données.
	 * 
	 * @param entity l'entité à créer.
	 * @return T l'entité avec son identifiant rempli.
	 */
	public T create(T entity);
	
	/**
	 * Lit les informations d'une entité en base de données.
	 * 
	 * @param id l'identifiant unique de l'entité.
	 * @return T l'entité construite à partir des informations récupérées.
	 */
	public T read(Integer id);
	
	/**
	 * Récupère toutes les entités (lignes) d'une table.
	 * 
	 * @return List<T> la liste de toutes les entités lues.
	 */
	public List<T> readAll();
	
	/**
	 * Met à jour l'entité donnée dans la base de données.
	 *
	 * @param entity l'entité à mettre à jour avec ses nouvelles informations.
	 * @return T l'entité mise à jour.
	 */
	public T update(T entity);
	
	/**
	 * Supprime définitivement une entité de la base de données.
	 * 
	 * @param id l'identifiant unique de l'entité.
	 * @return boolean vrai si la suppression est un succès, sinon faux.
	 */
	public boolean delete(Integer id);
}
