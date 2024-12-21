package com.example.boulange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boulange.entity.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	Utilisateur findByLogin(String login);
}