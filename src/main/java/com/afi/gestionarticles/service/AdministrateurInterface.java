package com.afi.gestionarticles.service;

import java.util.List;

import com.afi.gestionarticles.entity.Administrateur;
import com.afi.gestionarticles.entity.Membre;

public interface AdministrateurInterface {

	public void ajouterMembre(Membre mbre);
	public void supprimerMembre(int id);
	public void modifierMembre(Membre mbre);
	public Membre rechercherMembreParId(int id);
	public Iterable<Membre> rechercherMembreParNom(String nom);
	public List<Membre> listerMembres();
	public Administrateur testLogin(String login, String password);
}
