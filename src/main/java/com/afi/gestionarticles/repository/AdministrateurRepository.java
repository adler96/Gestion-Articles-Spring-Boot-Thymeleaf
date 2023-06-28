package com.afi.gestionarticles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.afi.gestionarticles.entity.Administrateur;

@Repository
@Component
public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {
	public Administrateur findByLoginAndPassword(String login, String password);
}
