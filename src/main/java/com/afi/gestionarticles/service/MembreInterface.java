package com.afi.gestionarticles.service;

import java.util.List;

import com.afi.gestionarticles.entity.Article;
import com.afi.gestionarticles.entity.Membre;

public interface MembreInterface {

	public void ajouterArticle(Article art);
	public void modifierArticle(Article art);
	public void supprimerArticle(int id);
	public Article rechercherArticleParId(int id);
	public Iterable<Article> rechercherArticleParLibelle(String libelle);
	public List<Article> listerArticles();
	public Membre testLogin(String login, String password);
	
}
