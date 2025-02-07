package com.example.boulange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boulange.entity.Marque;

public interface MarqueRepository extends JpaRepository<Marque, Long> {

}
