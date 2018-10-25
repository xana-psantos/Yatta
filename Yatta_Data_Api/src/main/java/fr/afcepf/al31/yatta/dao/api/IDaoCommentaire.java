package fr.afcepf.al31.yatta.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Commentaire;

public interface IDaoCommentaire extends JpaRepository<Commentaire, Integer> {

}
