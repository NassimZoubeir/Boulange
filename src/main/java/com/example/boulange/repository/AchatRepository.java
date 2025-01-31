package com.example.boulange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boulange.entity.Achat;

public interface AchatRepository extends JpaRepository<Achat, Long> {}
