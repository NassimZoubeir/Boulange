package com.example.boulange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boulange.entity.Ordinateur;
import com.example.boulange.repository.OrdinateurRepository;

@Service
public class OrdinateurService implements OrdinateurServiceItf {
	@Autowired
	private OrdinateurRepository ordinateurRepository;

	@Override
	public List<Ordinateur> getAllOrdinateur() {
		return ordinateurRepository.findAll();
	}
	@Override
	 public void creerOrdinateur(Ordinateur ordinateur) {
		ordinateurRepository.save(ordinateur);
	 }
}
