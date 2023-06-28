package com.afi.gestionarticles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.afi.gestionarticles.entity.Administrateur;
import com.afi.gestionarticles.entity.Membre;
import com.afi.gestionarticles.repository.AdministrateurRepository;
import com.afi.gestionarticles.repository.MembreRepository;

@Component
@Service
@ComponentScan("com.afi.gestionarticles.repository")
public class AdministrateurService implements AdministrateurInterface {
	
	@Autowired
	MembreRepository mr;
	AdministrateurRepository ar;

	@Override
	public void ajouterMembre(Membre mbre) {
		// TODO Auto-generated method stub
		if(mbre.getId() == 0) {
			mr.save(mbre);
		} else {
			Membre updateMbre = mr.findById(mbre.getId()).get();
			updateMbre.setPrenom(mbre.getPrenom());
			updateMbre.setNom(mbre.getNom());
			updateMbre.setLogin(mbre.getLogin());
			updateMbre.setPassword(mbre.getPassword());
			
			mr.save(updateMbre);
		}
	}

	@Override
	public void supprimerMembre(int id) {
		// TODO Auto-generated method stub
		mr.deleteById(id);
	}

	@Override
	public void modifierMembre(Membre mbre) {
		// TODO Auto-generated method stub
		mr.save(mbre);
	}

	@Override
	public Membre rechercherMembreParId(int id) {
		// TODO Auto-generated method stub
		Membre mbre = null;
		
		Optional<Membre> op = mr.findById(id);
		
		if(op.isPresent()) {
			mbre = op.get();
		} else {
			throw new RuntimeException("User not found");
		}
		
		return mbre;
	}

	@Override
	public Iterable<Membre> rechercherMembreParNom(String nom) {
		// TODO Auto-generated method stub
		return mr.findByNom(nom);
	}

	@Override
	public List<Membre> listerMembres() {
		// TODO Auto-generated method stub
		return mr.findAll();
	}

	@Override
	public Administrateur testLogin(String login, String password) {
		// TODO Auto-generated method stub
		Administrateur admin = ar.findByLoginAndPassword(login, password);
		
		return admin;
	}

}
