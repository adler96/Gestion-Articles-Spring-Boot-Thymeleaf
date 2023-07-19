package com.afi.gestionarticles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.afi.gestionarticles.entity.Article;
import com.afi.gestionarticles.entity.Membre;
import com.afi.gestionarticles.service.MembreService;

@Component
@Controller
public class MembreController {

	@Autowired
	MembreService ms;
	
	@GetMapping("/member/home")
	public String memberHome(Model model) {
		model.addAttribute("allArticles", ms.listerArticles());
		model.addAttribute("article", new Article());
		
		return "member/home";
	}
	
	@GetMapping("/member/addarticle")
	public String addArticleForm(Model model) {
		model.addAttribute("article", new Article());
		
		return "member/addArticle";
	}
	
	@PostMapping("/member/addarticle/save")
	public String saveArticle(@ModelAttribute("article") Article art, Model model, BindingResult result) {
		ms.ajouterArticle(art);
		
		return "redirect:/member/home";
	}
	
	@GetMapping("/member/editarticle/{id}")
	public String editArticleForm(@PathVariable("id") int id, Model model) {
		Article art = ms.rechercherArticleParId(id);
		
		model.addAttribute("article", art);
		
		return "member/editarticle";
	}
	
	@GetMapping("/member/deletearticle/{id}")
	public String deleteArticle(@PathVariable("id") int id) {
		ms.supprimerArticle(id);
		
		return "redirect:/member/home";
	}
	
	@GetMapping("/member/searchbyid/{id}")
	public String searchById(@PathVariable("id") int id, Model model) {
		Article art = ms.rechercherArticleParId(id);
		model.addAttribute("article", art);
		
		return "member/resultatRecherche";
	}
	
	@GetMapping("/member/searchbylibelle/lib")
	public String searchByName(@ModelAttribute("article") Article article, BindingResult result, Model model) {
		Iterable<Article> res = ms.rechercherArticleParLibelle(article.getLibelle());
		model.addAttribute("results", res);
		
		return "member/resultatsRecherche";
	}
	
	@GetMapping("/member/login")
	public String loginMember(Model model) {
		model.addAttribute("member", new Membre());
		
		return "member/login";
	}
	
	@GetMapping("/member/testLogin") 
	public String testLogin(@ModelAttribute("member") Membre membre) {
		Membre mbre = null;
		mbre = ms.testLogin(membre.getLogin(), membre.getPassword());
		
		if(mbre == null) {
			// TODO: mettre un flash
			return "redirect:/member/login";
		} else {
			// TODO: sauvegarder session
			return "redirect:/member/home";
		}
	}
}
