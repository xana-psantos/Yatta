package fr.afcepf.al31.yatta.business.api;

import java.util.List;

import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Tutoriel;

public interface IGestionRecherche {
    
    List<Article> getArticlePlusVus();

    List<Article> getArticlesMieuxNotes();

    List<Article> getArticlesPlusVendus();

    List<Article> getNouveauxArticles();
    
    List<Tutoriel> getTutorielPlusVus();

    List<Tutoriel> getNouveauxTuto();

    List<Tutoriel> getTutorielMieuxNotes();
    
    //Ajouter les recherches multi-criteres
}
