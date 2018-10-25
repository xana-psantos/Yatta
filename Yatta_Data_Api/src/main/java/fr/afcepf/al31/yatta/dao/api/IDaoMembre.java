package fr.afcepf.al31.yatta.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Membre;

public interface IDaoMembre extends JpaRepository<Membre, Integer>{
    List<Membre> findTop4ByOrderByDateInscription();
    
    List<Membre> findAllByOrderByDateInscription();

}
