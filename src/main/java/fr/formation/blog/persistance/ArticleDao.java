package fr.formation.blog.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.blog.metier.Article;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {

}
