package com.example.boulange.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boulange.repository.UtilisateurRepository;


@Controller
public class AdminController {
	
	 @Autowired
	    private UtilisateurRepository utilisateurRepository;

	    @GetMapping("/admin")
	    public String adminTableau(Model model) {
	        model.addAttribute("utilisateurs", utilisateurRepository.findAll());
	        return "admin";
	    }

	    @PostMapping("/admin/supprimerUtilisateur/{id}")
	    public String supprimerUtilisateur(@PathVariable Long id) {
	        utilisateurRepository.deleteById(id);
	        return "redirect:/admin";
	    }
}
