package fr.afcepf.al31.yatta.dao.api;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;

public interface IDaoEspaceTutoriel extends JpaRepository<EspaceTutoriel, Integer>{

        List<EspaceTutoriel> findByMembre(Membre membre);
        
        EspaceTutoriel findById(int idEspaceTuto);
        
        List<EspaceTutoriel> getEspaceTutorielByCategorie(Collection<Integer> listeCategories);
        
        
}
