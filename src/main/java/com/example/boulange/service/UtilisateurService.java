package com.example.boulange.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boulange.entity.Ordinateur;
import com.example.boulange.entity.Utilisateur;
import com.example.boulange.repository.OrdinateurRepository;
import com.example.boulange.repository.UtilisateurRepository;


@Service
public  class UtilisateurService implements UtilisateurServiceItf {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private  OrdinateurRepository  ordinateurRepository;

	@Autowired
	private  OrdinateurServiceItf  ordinateurService;

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);	
	}
	@Override
	public Utilisateur lireUtilisateurParLogin(String login) {
		return utilisateurRepository.findByLogin(login);
	}
	@Override
	public Utilisateur lireUtilisateurParId(Long id) {
		Utilisateur utilisateur = utilisateurRepository.findById(id).get();
		return utilisateur;
	}
	@Override
	public void acheterListOrdinateurUtilisateur(List<Long> ordinateurIdList, Long idUtilisateur) {
	    Utilisateur utilisateur = lireUtilisateurParId(idUtilisateur);
	    List<Ordinateur> ordinateurList = ordinateurService.getOrdinateurAcheterListParOrdinateurIdList(ordinateurIdList);

	    for (Ordinateur ordinateur : ordinateurList) {
	        ordinateurRepository.save(ordinateur); // Sauvegarder les modifications
	    }

	    utilisateur.getAcheterOrdinateurList().addAll(ordinateurList);
	    System.out.println("majOrdinateurAcheterListUtilisateur utilisateur=" + utilisateur);
	    utilisateurRepository.save(utilisateur);
	}

	@Override
	public List<Ordinateur> getAchatOrdinateurList(Long idUtilisateur) {
		Utilisateur  utilisateur  =  lireUtilisateurParId(idUtilisateur);
		return  utilisateur.getAcheterOrdinateurList();
	}	
	
}
