package com.example.boulange.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boulange.entity.Marque;
import com.example.boulange.entity.Ordinateur;
import com.example.boulange.repository.MarqueRepository;
import com.example.boulange.repository.OrdinateurRepository;
import com.example.boulange.repository.UtilisateurRepository;


@Controller
public class AdminController {
	
	 @Autowired
	 private UtilisateurRepository utilisateurRepository;
	 
	 @Autowired
	 private OrdinateurRepository ordinateurRepository;
	 
	 @Autowired
	 private MarqueRepository marqueRepository;

	    @GetMapping("/admin")
	    public String adminTableau(Model model) {
	        model.addAttribute("utilisateurs", utilisateurRepository.findAll());
	        model.addAttribute("ordinateurs", ordinateurRepository.findAll());
	        model.addAttribute("marques", marqueRepository.findAll());
	        return "admin";
	    }

	    @PostMapping("/admin/supprimerUtilisateur/{id}")
	    public String supprimerUtilisateur(@PathVariable Long id) {
	        utilisateurRepository.deleteById(id);
	        return "redirect:/admin";
	    }
	    
	    @PostMapping("/admin/supprimerOrdinateur/{id}")
	    public String supprimerOrdinateur(@PathVariable Long id) {
	        ordinateurRepository.deleteById(id);
	        return "redirect:/admin";
	    }
	  
	    @PostMapping("/admin/mettreAJourStock/{id}")
	    public String mettreAJourStock(@PathVariable Long id, @RequestParam int nombreOrdinateur) {
	        Ordinateur ordinateur = ordinateurRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Ordinateur invalide"));
	        ordinateur.setNombreOrdinateur(ordinateur.getNombreOrdinateur() + nombreOrdinateur);
	        ordinateurRepository.save(ordinateur);
	        return "redirect:/admin";
	    }

	    @PostMapping("/admin/creerMarque")
	    public String creerMarque(@RequestParam String nom) {
	        Marque marque = new Marque(nom);
	        marqueRepository.save(marque);
	        return "redirect:/admin";
	    }
	    
	    @PostMapping("/admin/supprimerMarque/{id}")
	    public String supprimerMarque(@PathVariable Long id) {
	        marqueRepository.deleteById(id);
	        return "redirect:/admin";
	    }
}
