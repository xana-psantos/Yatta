package fr.afcepf.al31.yatta.business.api.utilitaire;

import java.util.Collection;
import java.util.List;

import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Tutoriel;
import fr.afcepf.al31.yatta.entities.TutorielFavoris;

public interface IGestionUtilTuto {

    List<Commentaire> getAllCommentaireTuto(Tutoriel tutoriel);
    
    List<EspaceTutoriel> getEspaceTutorielByName(String name);
    
    List<Tutoriel> getAllTutorielByEspaceTutorielAndDateRetraitIsNull(EspaceTutoriel espace);
    
    List<Tutoriel> getAllTutoriel();
    
    List<Tutoriel> getAllTutorielOrderByNbreVue();
    
    List<Tutoriel> getTop4TutorielOrderByNbreVue();

    Tutoriel getTutorielById(Integer id);

    List<Tutoriel> getTutorielByName(String name);

    Double noteMoyenneTutoriel(Tutoriel tutoriel);
    
    List<TutorielFavoris> getTutorielFavoris(Membre membre);
    
    List<Tutoriel> getAllTutorielByEspaceTutorielAndDateRetraitIsNullOrderByDateAjoutDesc(EspaceTutoriel espace);
    
    List<Tutoriel> getTutorielByCategorie(Collection<Integer> listeCategorie);
}
