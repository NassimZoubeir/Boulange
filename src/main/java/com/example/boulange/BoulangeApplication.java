package com.example.boulange;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import com.example.boulange.entity.Ordinateur;
import com.example.boulange.entity.Utilisateur;
import com.example.boulange.outil.Outil;
import com.example.boulange.repository.OrdinateurRepository;
import com.example.boulange.repository.UtilisateurRepository;

@SpringBootApplication
public class BoulangeApplication {
	private  static  OrdinateurRepository  ordinateurRepository  =  null;
	private static UtilisateurRepository utilisateurRepository = null;
	public static void main(String[] args) {
		ApplicationContext  ctx  = SpringApplication.run(BoulangeApplication.class, args);
		ordinateurRepository  =  ctx.getBean(OrdinateurRepository.class);
		utilisateurRepository = ctx.getBean(UtilisateurRepository.class);
		initialiser();
	}
	public  static  void  initialiser()  {
		Ordinateur ordinateur1 = new Ordinateur("Ordinateur Apple MACBOOK CTO Pro 13 New M1 16 256 iGris sideral",
				1549.99,
				"Puce Apple M1",
				13.3, 
				16, 
				"01.png",
				"https://www.boulanger.com/ref/1156449");
		
		Ordinateur  ordinateur2  =  new  Ordinateur("PACKARD BELL PB314-35-P53X",
				 449,
				 "Intel Pentium Silver N6000",
				 14,
				 8,
				 "02.png",
				 "https://www.boulanger.com/ref/1164052");
		
		Ordinateur  ordinateur3  =  new  Ordinateur("Matebook 14s 2021 I7 16Go 512 Touch",
				 1049,
				 "Intel Core i7 11370H",
				 14.2,
				 16,
				 "03.png",
				 "https://www.boulanger.com/ref/1171445");
		
		Ordinateur  ordinateur4  =  new  Ordinateur("ACER Aspire A317-52-54QM Noir",
				 699,
				 "Intel Core i7 11370H",
				 17.3,
				 8,
				 "04.png",
				 "https://www.boulanger.com/ref/1148125");
		
		Ordinateur  ordinateur5  =  new  Ordinateur("ACER Aspire Vero AV15-51-78H5",
				 899,
				 "Intel Core i7 1195G7",
				 15.6,
				 16,
				 "05.png",
				 "https://www.boulanger.com/ref/1170405");
		
		Ordinateur  ordinateur6  =  new  Ordinateur("PC Hybride HP ENVY x360 13-ay0034nf",
				 799,
				 "AMD Ryzen 5 4500U",
				 13.3 ,
				 8,
				 "06.png",
				 "https://www.boulanger.com/ref/1163913");
		
		Ordinateur  ordinateur7  =  new  Ordinateur("PC Hybride LENOVO Flex 5 14ITL05-257",
				 936.17,
				 "Intel Core i5 1135G7",
				 14,
				 16,
				 "07.png",
				 "https://www.boulanger.com/ref/1164775");
		
		Ordinateur  ordinateur8  =  new  Ordinateur("HP Envy 17-ch0039nf",
				 1499.39,
				 "Intel Core i7 1165G7",
				 17.3,
				 16,
				 "08.png",
				 "https://www.boulanger.com/ref/1161111");
		
		Ordinateur  ordinateur9  =  new  Ordinateur("HP 17-cp0054",
				 449,
				 "AMD Athlon Silver 3050U",
				 17,
				 8,
				 "09.png",
				 "https://www.boulanger.com/ref/1169654");
		
		Ordinateur  ordinateur10  =  new  Ordinateur("ACER Aspire A517-52G-757L",
				 1099,
				 "Intel Core i7 1165G7",
				 17.3,
				 16,
				 "10.png",
				 "https://www.boulanger.com/ref/1164115");
		
		Ordinateur  ordinateur11  =  new  Ordinateur("PC Gamer MSI GS66 Stealth 11UG-289FR",
				 3199,
				 "Intel Core i7 11800H- 32 Go",
				 15.6,
				 32,
				 "11.png",
				 "https://www.boulanger.com/ref/1164182");
		
		Ordinateur  ordinateur12  =  new  Ordinateur("ACER Swift SF514-55T-73TS Vert",
				 1199,
				 "Intel Core i7 11800H- 32 Go",
				 14,
				 16,
				 "12.png",
				 "https://www.boulanger.com/ref/1164097");
		
		ordinateurRepository.save(ordinateur1);
		ordinateurRepository.save(ordinateur2);
		ordinateurRepository.save(ordinateur3);
		ordinateurRepository.save(ordinateur4);
		ordinateurRepository.save(ordinateur5);
		ordinateurRepository.save(ordinateur6);
		ordinateurRepository.save(ordinateur7);
		ordinateurRepository.save(ordinateur8);
		ordinateurRepository.save(ordinateur9);
		ordinateurRepository.save(ordinateur10);
		ordinateurRepository.save(ordinateur11);
		ordinateurRepository.save(ordinateur12);
		String hashPassword;
		Utilisateur utilisateur = null;
		try {
			hashPassword = Outil.hashMdpSha256("nass");
			utilisateur = new Utilisateur("nass", hashPassword, "nass@gmail.com", "abonne");
			utilisateurRepository.save(utilisateur);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Impossible de créer l'utilisateur nass");
		}
		 try {
			 hashPassword = Outil.hashMdpSha256("admin");
			 utilisateur = new Utilisateur("admin", hashPassword, "arnacoeur@gmail.com", "administrateur");
			 utilisateurRepository.save(utilisateur);
		} catch (NoSuchAlgorithmException e) {
			 System.out.println("Impossible de créer l'utilisateur admin");
		}

	}	

}
