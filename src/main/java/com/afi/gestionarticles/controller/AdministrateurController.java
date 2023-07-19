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
import org.springframework.web.bind.annotation.RequestAttribute;

import com.afi.gestionarticles.entity.Administrateur;
import com.afi.gestionarticles.entity.Membre;
import com.afi.gestionarticles.service.AdministrateurService;

@Component
@Controller
public class AdministrateurController {

	@Autowired
	AdministrateurService as;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("member", new Membre());
		return "home";
	}
	
	@GetMapping("/admin/home")
	public String adminHome(Model model) {
		model.addAttribute("membersList", as.listerMembres());
		model.addAttribute("member", new Membre());
		
		return "admin/home";
	}
	
	@GetMapping("/admin/addmember")
	public String addMemberForm(Model model) {
		model.addAttribute("member", new Membre());
		
		return "admin/addMember";
	}
	
	@PostMapping("/admin/addmember/save")
	public String addMember(Model model, @ModelAttribute("member") Membre membre, BindingResult result) {
		System.out.println("Id= "+membre.getId());
		as.ajouterMembre(membre);
		System.out.println("Id= "+membre.getId());
		return "redirect:/admin/home?Success";
	}
	
	@GetMapping("/admin/editmember/{id}")
	public String editMemberForm(Model model, @PathVariable("id") int id) {
		Membre membre = as.rechercherMembreParId(id);
		model.addAttribute("member", membre);

		return "admin/editMember";
	}
	
	@GetMapping("/admin/deletemember/{id}")
	public String deleteMember(Model model, @PathVariable("id") int id) {
		as.supprimerMembre(id);
		
		return "redirect:/admin/home";
	}
	

	@GetMapping("/admin/searchbyid/{id}")
	public String searchById(@PathVariable("id") int id, Model model) {
		Membre membre = as.rechercherMembreParId(id);
		model.addAttribute("member", membre);
		
		return "admin/resultatRecherche";
	}
	
	@GetMapping("/admin/searchbynom/nom")
	public String searchByNom(Model model, @ModelAttribute("member") Membre member, BindingResult result) {
		Iterable<Membre> res = as.rechercherMembreParNom(member.getNom());
		model.addAttribute("results", res);
		
		return "admin/resultatsRecherche";
	}
	
	@GetMapping("/admin/login")
	public String loginForm(Model model) {
		model.addAttribute("administrateur", new Administrateur());
		
		return "admin/login";
	}
	
	@GetMapping("/admin/testLogin")
	public String testLogin(Model model, @ModelAttribute("administrateur") Administrateur admin, BindingResult result) {
		Administrateur administrateur = null;
		administrateur = as.testLogin(admin.getLogin(), admin.getPassword());
		
		if(administrateur == null) {
			// TODO: mettre un flash
			return "redirect:/admin/login";
		} else {
			// TODO start session 
			return "redirect:/admin/home";
		}
	}
	
}
