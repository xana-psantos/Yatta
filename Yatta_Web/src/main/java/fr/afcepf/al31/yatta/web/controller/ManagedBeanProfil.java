package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilArticle;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilBoutique;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilEspaceTuto;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilTuto;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.ArticleFavoris;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Favoris;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Tutoriel;
import fr.afcepf.al31.yatta.entities.TutorielFavoris;

@Component("mbProfil")
//@ManagedBean(name="mbProfil")
@SessionScope
public class ManagedBeanProfil implements Serializable{

    private static final long serialVersionUID = 1L;

    
    @Autowired
    private ManagedBeanNavigation mbNavigation;
    
    @Autowired
    private ManagedBeanBoutique mbBoutique;
    
    @Autowired
    private IGestionUtilMisc gestionMisc;
    
    @Autowired
    private IGestionUtilBoutique gestionUtilBoutique;
    
    @Autowired
    private IGestionUtilArticle gestionUtilArticle;
    
    @Autowired
    private IGestionUtilEspaceTuto gestionUtilEspaceTuto;
    
    @Autowired
    private IGestionUtilTuto gestionUtilTuto;
    
    private Membre membreProfil = null;
    private Boutique boutiqueProfil;
    private EspaceTutoriel espaceTutoProfil;
    private boolean membreProfilestConnecte = false;
    
    private static final  int INDEX_COMMANDE = 3;
    private static final  int INDEX_PROFIL = 0;
    private static final  int INDEX_FAVORIS = 1;
    private static final  int INDEX_FOLLOWERS = 2;
    private static final  int INDEX_GESTION_BOUTIQUE = 4;
    private static final  int INDEX_GESTION_ESPACE_TUTO = 5;
    private static final  int INDEX_COMMANDE_RECUE = 6;
    private static final  int INDEX_COMMANDE_PASSE = 7;
    
    public static int getIndexCommandePasse() {
        return INDEX_COMMANDE_PASSE;
    }

    private int activeIndex = INDEX_COMMANDE;
    //Favoris
    private List<ArticleFavoris> articlesFavoris;
    private List<TutorielFavoris> tutorielFavoris;
    private List<Favoris> membreFavoris;
    private List<Favoris> membreFollowers;
 
    private List<Article> articlesPlusAchetes;
    private List<Article> articlesPlusVus;
    private List<Article> articlesPlusAimes;
    
    private List<Tutoriel> tutoPlusAimes;
    private List<Tutoriel> tutoPlusVus;
    private boolean showCommandePassee = false;
    private boolean showCommandeRecue = false;
    

    @PostConstruct
    public void init() {
        //Pour test 
        if ( membreProfil == null) {
            membreProfil = gestionMisc.getMembreById(6);
        }
        
        if (mbNavigation.getUser()!= null) {
            if (mbNavigation.getUser().getId() == membreProfil.getId()) {
                membreProfilestConnecte = true;
            }
        }
        //Chargement Boutique
        boutiqueProfil = gestionUtilBoutique.getBoutiqueByMembre(membreProfil);
        
        // chargement espace Tuto
        espaceTutoProfil = gestionUtilEspaceTuto.getEspaceTutoByMembre(membreProfil);
        
        //Todo chargement list articles
        articlesFavoris = gestionUtilArticle.getArticlesFavoris(membreProfil);

        //Todo chargement list tuto
        tutorielFavoris = gestionUtilTuto.getTutorielFavoris(membreProfil);
        
        //chargement list Membre
        membreFavoris = gestionMisc.getMembresFavoris(membreProfil);
        membreFollowers = gestionMisc.getFollowersMembre(membreProfil);
        //set Boutique et espaceTuto pour chaque favoris
        for (Favoris favoris : membreFavoris) {
            setterBoutiqueEspaceTutoMembre(favoris.getMembreSuivi());
        }
        for (Favoris favoris : membreFollowers) {
            setterBoutiqueEspaceTutoMembre(favoris.getMembreSuiveur());
        }
    }
    
    public void ajouterFavoris() {
        //ToDo implementer méthode ajouter favoris
    }
    
    public void redirigerVersBoutique() {
        mbBoutique.setBoutique(boutiqueProfil);
        mbBoutique.init();
        mbNavigation.goToBoutique();
    }
    
    public void redirigerVersEspaceTuto() {
        
    }
    
    private void setterBoutiqueEspaceTutoMembre(Membre membre) {
        Boutique boutique = gestionUtilBoutique.getBoutiqueByMembre(membre);
        membre.setBoutique(boutique);
        EspaceTutoriel espaceTuto = gestionUtilEspaceTuto.getEspaceTutoByMembre(membre);
        membre.setEspaceTutoriel(espaceTuto);
    };
    public List<ArticleFavoris> getArticlesFavoris() {
        return articlesFavoris;
    }

    public void setArticlesFavoris(List<ArticleFavoris> paramArticlesFavoris) {
        articlesFavoris = paramArticlesFavoris;
    }

    public ManagedBeanNavigation getMbNavigation() {
        return mbNavigation;
    }

    public void setMbNavigation(ManagedBeanNavigation paramMbNavigation) {
        mbNavigation = paramMbNavigation;
    }

    public Boutique getBoutiqueProfil() {
        return boutiqueProfil;
    }

    public void setBoutiqueProfil(Boutique paramBoutiqueProfil) {
        boutiqueProfil = paramBoutiqueProfil;
    }

    public EspaceTutoriel getEspaceTutoProfil() {
        return espaceTutoProfil;
    }

    public void setEspaceTutoProfil(EspaceTutoriel paramEspaceTutoProfil) {
        espaceTutoProfil = paramEspaceTutoProfil;
    }


    public Membre getMembreProfil() {
        return membreProfil;
    }


    public void setMembreProfil(Membre paramMembreProfil) {
        membreProfil = paramMembreProfil;
    }


    public boolean isMembreProfilestConnecte() {
        return membreProfilestConnecte;
    }


    public void setMembreProfilestConnecte(boolean paramMembreProfilestConnecte) {
        membreProfilestConnecte = paramMembreProfilestConnecte;
    }


    public List<Article> getArticlesPlusAchetes() {
        return articlesPlusAchetes;
    }

    public void setArticlesPlusAchetes(List<Article> paramArticlesPlusAchetes) {
        articlesPlusAchetes = paramArticlesPlusAchetes;
    }

    public List<Article> getArticlesPlusVus() {
        return articlesPlusVus;
    }

    public void setArticlesPlusVus(List<Article> paramArticlesPlusVus) {
        articlesPlusVus = paramArticlesPlusVus;
    }

    public List<Article> getArticlesPlusAimes() {
        return articlesPlusAimes;
    }

    public void setArticlesPlusAimes(List<Article> paramArticlesPlusAimes) {
        articlesPlusAimes = paramArticlesPlusAimes;
    }

    public List<Tutoriel> getTutoPlusAimes() {
        return tutoPlusAimes;
    }

    public void setTutoPlusAimes(List<Tutoriel> paramTutoPlusAimes) {
        tutoPlusAimes = paramTutoPlusAimes;
    }

    public List<Tutoriel> getTutoPlusVus() {
        return tutoPlusVus;
    }

    public void setTutoPlusVus(List<Tutoriel> paramTutoPlusVus) {
        tutoPlusVus = paramTutoPlusVus;
    }
    
    public List<TutorielFavoris> getTutorielFavoris() {
        return tutorielFavoris;
    }

    public void setTutorielFavoris(List<TutorielFavoris> paramTutorielFavoris) {
        tutorielFavoris = paramTutorielFavoris;
    }

    public List<Favoris> getMembreFavoris() {
        return membreFavoris;
    }

    public void setMembreFavoris(List<Favoris> paramMembreFavoris) {
        membreFavoris = paramMembreFavoris;
    }

    public List<Favoris> getMembreFollowers() {
        return membreFollowers;
    }

    public void setMembreFollowers(List<Favoris> paramMembreFollowers) {
        membreFollowers = paramMembreFollowers;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int paramActiveIndex) {
        activeIndex = paramActiveIndex;
    }

    public static int getIndexCommande() {
        return INDEX_COMMANDE;
    }

    public static int getIndexProfil() {
        return INDEX_PROFIL;
    }

    public static int getIndexFavoris() {
        return INDEX_FAVORIS;
    }

    public static int getIndexFollowers() {
        return INDEX_FOLLOWERS;
    }

    public static int getIndexGestionBoutique() {
        return INDEX_GESTION_BOUTIQUE;
    }

    public static int getIndexGestionEspaceTuto() {
        return INDEX_GESTION_ESPACE_TUTO;
    }

    public static int getIndexCommandeRecue() {
        return INDEX_COMMANDE_RECUE;
    }

    public boolean isShowCommandePassee() {
        return showCommandePassee;
    }

    public void setShowCommandePassee(boolean paramShowCommandePassee) {
        showCommandePassee = paramShowCommandePassee;
    }

    public boolean isShowCommandeRecue() {
        return showCommandeRecue;
    }

    public void setShowCommandeRecue(boolean paramShowCommandeRecue) {
        showCommandeRecue = paramShowCommandeRecue;
    }
    
    
}
