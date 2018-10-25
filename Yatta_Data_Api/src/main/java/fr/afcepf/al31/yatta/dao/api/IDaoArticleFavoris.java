package fr.afcepf.al31.yatta.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.ArticleFavoris;
import fr.afcepf.al31.yatta.entities.Membre;

public interface IDaoArticleFavoris extends JpaRepository<ArticleFavoris, Integer> {
    List<ArticleFavoris> findByMembre(Membre membre);
}
