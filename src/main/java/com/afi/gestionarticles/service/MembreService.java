package com.afi.gestionarticles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.afi.gestionarticles.entity.Article;
import com.afi.gestionarticles.entity.Membre;
import com.afi.gestionarticles.repository.ArticleRepository;
import com.afi.gestionarticles.repository.MembreRepository;

@Service
@Component
@ComponentScan("com.afi.gestionarticles.repository")
public class MembreService implements MembreInterface {

	@Autowired
	ArticleRepository ar;
	MembreRepository mr;

	@Override
	public void ajouterArticle(Article art) {
		// TODO Auto-generated method stub
		if(art.getId() == 0) {
			ar.save(art);
		} else {
			Article updatedArt = art;
			updatedArt.setLibelle(art.getLibelle());
			updatedArt.setCategorie(art.getCategorie());
			updatedArt.setDescription(art.getDescription());
			
			ar.save(updatedArt);
		}
	}

	@Override
	public void modifierArticle(Article art) {
		// TODO Auto-generated method stub
		ar.save(art);
	}

	@Override
	public void supprimerArticle(int id) {
		// TODO Auto-generated method stub
		ar.deleteById(id);
	}

	@Override
	public Article rechercherArticleParId(int id) {
		// TODO Auto-generated method stub
		Article art = null;
		
		Optional<Article> opt = ar.findById(id);
		
		if(opt.isPresent()) {
			art = opt.get();
		} else {
			throw new RuntimeException("Article not found");
		}
		
		return art;
	}

	@Override
	public Iterable<Article> rechercherArticleParLibelle(String libelle) {
		// TODO Auto-generated method stub
		
		return ar.findByLibelle(libelle);
	}

	@Override
	public List<Article> listerArticles() {
		// TODO Auto-generated method stub
		return ar.findAll();
	}

	@Override
	public Membre testLogin(String login, String password) {
		// TODO Auto-generated method stub
		return mr.findByLoginAndPassword(login, password);
	}
	
}
