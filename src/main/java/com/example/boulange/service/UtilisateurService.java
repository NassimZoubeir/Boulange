package com.example.boulange.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.boulange.entity.Achat;
import com.example.boulange.entity.Ordinateur;
import com.example.boulange.entity.Utilisateur;
import com.example.boulange.entity.VerificationToken;
import com.example.boulange.repository.AchatRepository;
import com.example.boulange.repository.OrdinateurRepository;
import com.example.boulange.repository.UtilisateurRepository;
import com.example.boulange.repository.VerificationTokenRepository;


@Service
public  class UtilisateurService implements UtilisateurServiceItf {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailServiceImpl emailService;
	
	@Autowired
	private  OrdinateurRepository  ordinateurRepository;

	@Autowired
	private  OrdinateurServiceItf  ordinateurService;
	
	@Autowired
	private AchatRepository achatRepository;

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);	
		
		 // Génération du jeton
	    String token = UUID.randomUUID().toString();
	    VerificationToken verificationToken = new VerificationToken(token, utilisateur);
	    verificationTokenRepository.save(verificationToken); // Utiliser l'instance injectée

	    // Envoi de l'email
	    String verificationUrl = "http://localhost:8080/verifier-email?token=" + token;
	    String subject = "Confirmez votre email";
	    String body = "Bonjour " + utilisateur.getLogin() + ",\n\nCliquez sur ce lien pour vérifier votre email :\n" + verificationUrl;

	    // Utiliser le service d'email correctement injecté
	    emailService.sendSimpleMessage(utilisateur.getEmail(), subject, body);
	}
	@Override
	public boolean verifierEmail(String token) {
	    var optionalToken = verificationTokenRepository.findByToken(token); // Utiliser l'instance injectée

	    if (optionalToken.isPresent() && !optionalToken.get().isExpired()) {
	        Utilisateur utilisateur = optionalToken.get().getUtilisateur();
	        utilisateur.setVerified(true);
	        utilisateurRepository.save(utilisateur);
	        return true;
	    }
	    return false;
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
	public List<Achat> getAchatOrdinateurList(Long idUtilisateur) {
		Utilisateur utilisateur = lireUtilisateurParId(idUtilisateur);
		System.out.println("UtilisateurService - getAchatOrdinateurList utilisateur:" + utilisateur);
		return utilisateur.getAcheterOrdinateurList();
	}
	@Override
	public Achat getAchatById(Long id) {
		return achatRepository.findById(id).get();
	}
	@Override
	public void acheterListOrdinateurUtilisateur(List<Long> ordinateurIdList, Long idUtilisateur) {
		Utilisateur utilisateur = lireUtilisateurParId(idUtilisateur);
		List<Ordinateur> ordinateurList = ordinateurService.getOrdinateurAcheterListParOrdinateurIdList(ordinateurIdList);
		System.out.println("UtilisateurService - acheterListOrdinateurUtilisateur ordinateurList:\n" + ordinateurList);
		System.out.println("majOrdinateurAcheterListUtilisateur utilisateur=" + utilisateur);
		Achat achat = null;
		for(int i=0; i < ordinateurList.size(); i++) {
			achat = new Achat(ordinateurList.get(i), new Date());
			achatRepository.save(achat);
			utilisateur.acheterOrdinateur(achat);
		}
		System.out.println("majOrdinateurAcheterListUtilisateur utilisateur=" + utilisateur);
		utilisateurRepository.save(utilisateur);	
	}
	    @Override
	    public void majAchat(Achat achat) {
	        achatRepository.save(achat);
	    }
	
}
