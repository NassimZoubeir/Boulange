package com.example.boulange.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.boulange.entity.Ordinateur;
import com.example.boulange.service.OrdinateurServiceItf;

import jakarta.servlet.ServletContext;





@Controller
public class OrdinateurController {
	@Autowired
	private  OrdinateurServiceItf  ordinateurService;
	
	@RequestMapping("/accueil")
	public  String  accueil()  {
		System.out.println("==== /accueils ====");
		return  "accueil";
	}
	@RequestMapping("/afficher-ordinateurs")
    public String administrer(Model model) {
		System.out.println("==== /afficher-ordinateurs ====");
		List<Ordinateur> ordinateurList = ordinateurService.getAllOrdinateur();
    	System.out.println("OrdinateurList: " + ordinateurList);
    	model.addAttribute("ordinateurList", ordinateurList);
        return "catalogue";
    }
	 @RequestMapping("/creer-ordinateur")
	 public String creerOrdinateur(Model model) {
	 model.addAttribute("titre", "Créer ordinateur");
	 return "creer-ordinateur";
	 }
	 @Autowired
	    private ServletContext context;

	    @Value("${dir.images}")
	    private String imageDir;
	    
	    @RequestMapping("/creer-ordinateur-validation")
	    public String creerValidationOrdinateur(
	            String denomination,
	            double prix,
	            double ecran,
	            String processeur,
	            int vive,
	            String lien,
	            MultipartFile image) {

	    	String imageName = null;

	    	if (image != null && !image.isEmpty()) {
	    	    try {
	    	        
	    	        imageName = image.getOriginalFilename();
	    	        Path pathFile = Paths.get(imageDir, imageName);

	    	        System.out.println("Chemin de l'image : " + pathFile.toString()); // Log pour vérifier le chemin final

	    	        
	    	        File directory = new File(imageDir);
	    	        if (!directory.exists()) {
	    	            directory.mkdirs();
	    	        }

	    	        
	    	        image.transferTo(pathFile.toFile());
	    	    } catch (IllegalStateException | IOException e) {
	    	        e.printStackTrace();
	    	        System.err.println("Erreur lors de l'enregistrement de l'image : " + e.getMessage());
	    	        return "error"; 
	    	    }
	    	}

	    	
	    	Ordinateur ordinateur = new Ordinateur(denomination, prix, processeur, ecran, vive, imageName, lien);
	    	ordinateurService.creerOrdinateur(ordinateur);

	    	return "redirect:/afficher-ordinateurs";
	    }
}