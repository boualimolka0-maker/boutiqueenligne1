package com.molka.boutiqueenlinge1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.molka.boutiqueenlinge1.model.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

}