package com.example.boulange.service;

import java.util.List;

import com.example.boulange.entity.Achat;
import com.example.boulange.entity.Ordinateur;
import com.example.boulange.entity.Utilisateur;

public interface UtilisateurServiceItf {
	void creerUtilisateur(Utilisateur utilisateur);
	Utilisateur lireUtilisateurParLogin(String login);
	Utilisateur lireUtilisateurParId(Long id);
	void acheterListOrdinateurUtilisateur(List<Long>  livreIdList,  Long  idUtilisateur);
	List<Achat> getAchatOrdinateurList(Long idUtilisateur);
	Achat getAchatById(Long id);
	void majAchat(Achat achat);
}
