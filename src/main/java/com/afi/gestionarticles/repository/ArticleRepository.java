package com.afi.gestionarticles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.afi.gestionarticles.entity.Article;

@Component
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	// recherche par libelle 
	public Iterable<Article> findByLibelle(String libelle);
	
}
