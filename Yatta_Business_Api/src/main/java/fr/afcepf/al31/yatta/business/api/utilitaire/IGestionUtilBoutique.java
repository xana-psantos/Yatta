package fr.afcepf.al31.yatta.business.api.utilitaire;

import java.util.Collection;
import java.util.List;

import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Note;
import fr.afcepf.al31.yatta.entities.SuspensionBoutique;
import fr.afcepf.al31.yatta.entities.Tutoriel;

public interface IGestionUtilBoutique {
    
    List<Boutique> getAllBoutique();

    Boutique getBoutiqueById(Integer id);

    List<Boutique> getBoutiqueByName(String name);

    Boutique getBoutiqueByMembre(Membre membre);

    Boutique getBoutiqueByArticle(Article article);
    
    List<Categorie> getAllCategorieBoutiques(Boutique boutique);
    
    List<SuspensionBoutique> getAllSuspensionBoutique(Boutique boutique);
    
    SuspensionBoutique getSuspensionById(Integer id);
    
    List<Tutoriel> getAllTutorielByCategorie(Categorie categorie);
    
    List<Integer> getAllNoteByBoutique(Boutique boutique);
    
    List<Article> findAllByBoutiqueOrderByNbreVueDesc(Boutique boutique);
    
    List<Article> afficherParBoutiqueOrderByNoteDesc(Boutique boutique);
   
    List<Article> findAllByBoutiqueOrderByPrixAsc(Boutique boutique);
   
    List<Article> findAllByBoutiqueOrderByDateAjoutDesc(Boutique boutique);
    
    List<Commande> getVentesByBoutique(Boutique boutique);
    
    List<Article> rechercheArticlesByBoutiqueByCategorie(Categorie categorie, Boutique boutique);
    
    List<Boutique> rechercherBoutiquesParCategorieArticle(Collection<Integer> listeCategories);
}
