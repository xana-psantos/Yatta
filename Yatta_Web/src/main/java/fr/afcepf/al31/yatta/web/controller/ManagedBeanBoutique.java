package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilArticle;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilBoutique;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.Commande;

@Component("mbBoutique")
//@ManagedBean(name="mbBoutique")
@SessionScope
public class ManagedBeanBoutique implements Serializable {

   
    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(getClass());
    
    @Autowired
    private ManagedBeanNavigation mbNav;
    @Autowired
    private ManagedBeanArticle mbArt;
    
    private double noteMoyenneBoutique;       
    private Boutique boutique = null;
    private List<Article> articlesBoutique;
    private List<Categorie> categoriesBoutique;
    private List<Commande> commandesBoutique;
    private List<Integer> notesBoutique;
    private StringBuffer nbVentes = new StringBuffer();
    
    @Autowired
    private IGestionUtilArticle gestionUtilArticle;
    @Autowired
    private IGestionUtilBoutique gestionUtilBoutique;
    

    @PostConstruct
    public void init(){
        try {
            if(boutique == null) {
                boutique = gestionUtilBoutique.getBoutiqueById(6);
            }
            articlesBoutique = gestionUtilArticle.getArticlesBoutique(boutique);
            for (Article article : articlesBoutique) {
                article.calculerNoteMoyenne(article);    
            }
            categoriesBoutique = gestionUtilBoutique.getAllCategorieBoutiques(boutique);
            commandesBoutique = gestionUtilBoutique.getVentesByBoutique(boutique);
            nbVentes.append(commandesBoutique.size()).append(" ");
            if(commandesBoutique.size() == 1) {
                nbVentes.append(" vente");
            } else {
                nbVentes.append(" ventes");
            }
            notesBoutique = gestionUtilBoutique.getAllNoteByBoutique(boutique);
            noteMoyenneBoutique = calculerNoteMoyenneBoutique();
        } catch (Exception e) {

            log.fatal("l'init() rencontre un obstacle de betises!!!! Il y a FORCEMENT une erreur, dixit Séb");
        } 
    }
    
    public void goToFicheArticle(Article article) {
        mbArt.setArticle(article);
        mbNav.goToArticle();
    }
    
    public void filtrerArticlesBoutiqueParNbrVueDesc() {
        articlesBoutique = articlesBoutique.stream().sorted(Comparator.comparing(Article::getNbreVue).reversed()).collect(Collectors.toList());
       
    }
    
    public void filtrerArticlesBoutiqueParPrixAsc() {
        articlesBoutique = articlesBoutique.stream().sorted(Comparator.comparing(Article::getPrix)).collect(Collectors.toList());
    }
    
    public void filtrerArticlesBoutiqueParDateAjoutDesc() {
        articlesBoutique = articlesBoutique.stream().sorted(Comparator.comparing(Article::getDateAjout).reversed()).collect(Collectors.toList());
    }
    
    public void filtrerArticlesParBoutiqueOrderByNoteDesc() {
        if(articlesBoutique.size() != 0 ) {
            articlesBoutique = gestionUtilBoutique.afficherParBoutiqueOrderByNoteDesc(boutique);
        }
    }
    
    public void filtrerArticlesParCategorieEtBoutique() {
        for (Categorie categorie : categoriesBoutique) {
            articlesBoutique = gestionUtilBoutique.rechercheArticlesByBoutiqueByCategorie(categorie, boutique);
        }
    }
    
    public void changerOrdreArticlesBoutique() {
        Collections.reverse(articlesBoutique);    
    }
    
    public double calculerNoteMoyenneBoutique() {
        double noteMBout = 0.0;
        if ( notesBoutique.size() != 0 ) {
            int somme=0; 
            for (Integer note : notesBoutique) {
                somme+= note.intValue();
            }
            noteMBout = ((double)(somme)) / ((double)notesBoutique.size());
        }else {
            noteMBout= 0.0;
        }
        return noteMBout;
    }
    
    //GETTERS SETTERS
    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique paramBoutique) {
        boutique = paramBoutique;
    }

    public List<Article> getArticlesBoutique() {
        return articlesBoutique;
    }

    public void setArticlesBoutique(List<Article> paramArticlesBoutique) {
        articlesBoutique = paramArticlesBoutique;
    }

    public List<Categorie> getCategoriesBoutique() {
        return categoriesBoutique;
    }

    public void setCategoriesBoutique(List<Categorie> paramCategoriesBoutique) {
        categoriesBoutique = paramCategoriesBoutique;
    }

    public List<Commande> getCommandesBoutique() {
        return commandesBoutique;
    }

    public void setCommandesBoutique(List<Commande> paramCommandesBoutique) {
        commandesBoutique = paramCommandesBoutique;
    }

    public StringBuffer getNbVentes() {
        return nbVentes;
    }

    public void setNbVentes(StringBuffer paramNbVentes) {
        nbVentes = paramNbVentes;
    }

   
    public Double getNoteMoyenneBoutique() {
        return noteMoyenneBoutique;
    }

    public void setNoteMoyenneBoutique(Double paramNoteMoyenneBoutique) {
        noteMoyenneBoutique = paramNoteMoyenneBoutique;
    }

    public void setNoteMoyenneBoutique(double paramNoteMoyenneBoutique) {
        noteMoyenneBoutique = paramNoteMoyenneBoutique;
    }

    public List<Integer> getNotesBoutique() {
        return notesBoutique;
    }

    public void setNotesBoutique(List<Integer> paramNotesBoutique) {
        notesBoutique = paramNotesBoutique;
    }

 
    
}
