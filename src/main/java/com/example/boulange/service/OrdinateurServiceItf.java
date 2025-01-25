package com.example.boulange.service;

import java.util.List;

import com.example.boulange.entity.Ordinateur;


public interface OrdinateurServiceItf {
	List<Ordinateur> getAllOrdinateur();
	void creerOrdinateur(Ordinateur ordinateur);
	Ordinateur getOrdinateurById(Long id);
	List<Ordinateur> getOrdinateurAcheterListParOrdinateurIdList(List<Long> ordinateurAcheterListId);
	void incrementernombreOrdinateur(Long id);
	void decrementernombreOrdinateur(Long id);
}
