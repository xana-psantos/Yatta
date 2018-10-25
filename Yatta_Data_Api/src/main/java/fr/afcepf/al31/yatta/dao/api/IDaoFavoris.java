package fr.afcepf.al31.yatta.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Favoris;
import fr.afcepf.al31.yatta.entities.Membre;

public interface IDaoFavoris extends JpaRepository<Favoris, Integer> {
    List<Favoris> findByMembreSuiveur(Membre membre);
    List<Favoris> findByMembreSuivi(Membre membre);
}
