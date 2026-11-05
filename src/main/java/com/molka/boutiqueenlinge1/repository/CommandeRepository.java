
package com.molka.boutiqueenlinge1.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.molka.boutiqueenlinge1.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

   
    List<Commande> findByStatutIgnoreCase(String statut);
}
