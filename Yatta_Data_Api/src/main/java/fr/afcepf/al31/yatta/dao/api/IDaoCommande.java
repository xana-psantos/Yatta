package fr.afcepf.al31.yatta.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Membre;

public interface IDaoCommande extends JpaRepository<Commande, Integer> {
    List<Commande> findParAcheteur(Integer id);
    List<Commande> getCommandeRecus(Membre membre);
    List<Commande> getVentesByBoutique(Integer idBoutique);
    List<Commande> getCommandePanier(Integer idAcheteur);
    Commande afficherCommandeById(Integer id);
}

