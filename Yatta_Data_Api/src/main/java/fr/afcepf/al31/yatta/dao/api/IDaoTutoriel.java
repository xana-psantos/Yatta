package fr.afcepf.al31.yatta.dao.api;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Tutoriel;

public interface IDaoTutoriel extends JpaRepository<Tutoriel, Integer> {
   
    List<Tutoriel> findTop4ByOrderByNbreVueDesc();
  
    List<Tutoriel> findAllByOrderByNbreVueDesc();
    
    List<Tutoriel> findAllByEspaceTutorielAndDateRetraitIsNull(EspaceTutoriel espaceTutoriel);
    
    List<Tutoriel> findAllByEspaceTutorielAndDateRetraitIsNullOrderByDateAjoutDesc(EspaceTutoriel espaceTutoriel);
    
    List<Tutoriel> rechercheByCategorie(Collection<Integer> listeCategories);
    
}
