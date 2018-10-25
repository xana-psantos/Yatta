package fr.afcepf.al31.yatta.business.api.utilitaire;

import java.util.Collection;
import java.util.List;

import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.SuspensionEspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Tutoriel;

public interface IGestionUtilEspaceTuto {

    List<EspaceTutoriel> getAllEspaceTutoriel();

    EspaceTutoriel getEspaceTutorielById(Integer id);

    List<Categorie> getAllCategoriesEspaceTuto(EspaceTutoriel espaceTuto);

    EspaceTutoriel getEspaceTutoByTuto(Tutoriel tutoriel);

    EspaceTutoriel getEspaceTutoByMembre(Membre membre);

    List<SuspensionEspaceTutoriel> getAllSuspensionEspaceTuto(Boutique boutique);
    
    SuspensionEspaceTutoriel getSuspensionEspaceTutoById(Integer id);
    
    List<EspaceTutoriel> rechercherEspaceTutorielParCategorieTutoriel(Collection<Integer> listeCategories);

}
