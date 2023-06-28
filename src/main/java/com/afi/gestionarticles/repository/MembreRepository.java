package com.afi.gestionarticles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.afi.gestionarticles.entity.Membre;

@Repository
@Component
public interface MembreRepository extends JpaRepository<Membre, Integer> {

	// recherche d'un membre par nom
	public Iterable<Membre> findByNom(String nom);
	
	public Membre findByLoginAndPassword(String login, String password);
}
