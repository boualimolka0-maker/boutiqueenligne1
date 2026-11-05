package com.molka.boutiqueenlinge1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.molka.boutiqueenlinge1.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}