package fr.afcepf.al31.yatta.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.LigneDeCommande;

public interface IDaoLigneCommande extends JpaRepository<LigneDeCommande, Integer>{
    List<LigneDeCommande> findByCommande(Commande commande);
}
