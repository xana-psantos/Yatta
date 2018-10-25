package fr.afcepf.al31.yatta.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Categorie;

public interface IDaoCategorie extends JpaRepository<Categorie, Integer> {

    List<Categorie> listerCategorieBoutique(Integer idBoutique);
    
    List<Categorie> chercherCategoriesTutorielsFromEspaceTuto(Integer IdEspaceTutoriel);
}
