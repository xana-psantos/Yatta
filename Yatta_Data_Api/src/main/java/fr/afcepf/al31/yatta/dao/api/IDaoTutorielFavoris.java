package fr.afcepf.al31.yatta.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.TutorielFavoris;

public interface IDaoTutorielFavoris extends JpaRepository<TutorielFavoris, Integer> {
    List<TutorielFavoris> findByMembre(Membre membre);
}
