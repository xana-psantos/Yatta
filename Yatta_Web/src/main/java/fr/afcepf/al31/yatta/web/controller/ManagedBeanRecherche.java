package fr.afcepf.al31.yatta.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilArticle;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilBoutique;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilEspaceTuto;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilTuto;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Tutoriel;

@Component("mbRecherche")
// @ManagedBean(name="mbRecherche")
@SessionScope
public class ManagedBeanRecherche {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private ManagedBeanNavigation mbNavig;

    @Autowired
    private ManagedBeanApplication mbApplication;

    @Autowired
    private ManagedBeanArticle mbArticle;

    @Autowired
    private ManagedBeanBoutique mbBoutique;

    @Autowired
    private IGestionUtilTuto gestionUtilTuto;
    
    @Autowired
    private IGestionUtilMisc gestionUtilMisc;

    @Autowired
    private IGestionUtilArticle gestionUtilArticle;

    @Autowired
    private IGestionUtilBoutique gestionUtilBoutique;

    @Autowired
    private IGestionUtilEspaceTuto gestionUtilEspaceTuto;

    // TODO Cartouche Boutique

    private List<Boutique> boutiques;
    private List<Boutique> boutiquesAll;
    private List<Article> articles;
    private List<Article> articlesAll;
    private List<Tutoriel> tutoriels;
    private List<Tutoriel> tutorielsAll;
    private List<Membre> membres;
    private List<Membre> membresAll;
    private List<EspaceTutoriel> espaceTutos;
    private List<EspaceTutoriel> espaceTutosAll;
    private String boutonPMBoutiques;
    private String boutonPMArticles;
    private String boutonPMTuto;
    private String boutonPMMembres;
    private String boutonPMEspaceTutos;

    private Collection<Integer> listeCategories;
    private Map<Integer, Boolean> booleanCheckboxByCategorieId;
    private List<Article> listeArticlesFromBoutique;
    private List<Categorie> listeCategoriesBoutique;
    private Map<Boutique, List<Categorie>> categoriesBoutique;
    private List<Tutoriel> listeTutosFromEspaceTuto;
    private List<Categorie> listeCategoriesEspaceTuto;
    private Map<EspaceTutoriel, List<Categorie>> categoriesEspaceTuto;

    private Boolean boolCheckBoxBoutique;

    @PostConstruct
    public void init() {

        try {
            // Fonction pour affecter les categories
            affectationCategories();
            // Block pour remplir les listes
            boutiquesAll = gestionUtilBoutique.rechercherBoutiquesParCategorieArticle(listeCategories);
  
            articlesAll = gestionUtilArticle.getArticleByCategorie(listeCategories);

            tutorielsAll = gestionUtilTuto.getTutorielByCategorie(listeCategories);

            espaceTutosAll = gestionUtilEspaceTuto.rechercherEspaceTutorielParCategorieTutoriel(listeCategories);

            // Si la liste des tutoriels est plus petit que 4, on prend toute la
            // liste
            // Sinon on prend seulement les 4 premiers, puis idem pour autres
            // objets affichés

            boutiques = (boutiquesAll.size() <= 4) ? boutiquesAll : boutiquesAll.subList(0, 4);
            tutoriels = (tutorielsAll.size() <= 4) ? tutorielsAll : tutorielsAll.subList(0, 4);
            articles = (articlesAll.size() <= 4) ? articlesAll : articlesAll.subList(0, 4);
            espaceTutos = (espaceTutosAll.size() <= 4) ? espaceTutosAll : espaceTutosAll.subList(0, 4);

            // Initialisation bouton icon "plus"
            boutonPMBoutiques = "plus";
            boutonPMArticles = "plus";
            boutonPMTuto = "plus";
            boutonPMMembres = "plus";
            boutonPMEspaceTutos = "plus";

            boolCheckBoxBoutique = true;
            membresAll = gestionUtilMisc.getAllMembreOrderByDateInscription();
            membres = (membresAll.size() <= 4) ? membresAll : membresAll.subList(0, 4);
            setBoutiquesToMembres(membres);

        } catch (Exception e) {
            log.fatal("init() aurait du marcher ! ");
        }
    }

    private void affectationCategories() {

        listeCategories = new ArrayList<Integer>();
        if (mbApplication.getCatSelected().isEmpty()) {
            for (Categorie cat : mbApplication.getCategories()) {
                listeCategories.add(cat.getId());
            }
        } else {
            for (String s : Arrays.asList(mbApplication.getCatSelected().split(","))) {
                int id = mbApplication.getCategoriesMap().get(s);
                listeCategories.add(id);
            }
        }
        booleanCheckboxByCategorieId = new HashMap<Integer, Boolean>();
        for (Integer categorieId : listeCategories) {
            booleanCheckboxByCategorieId.put(categorieId, true);
        }
    }

    public void afficherPlusOuMoinsBoutiques() {
        try {
            if (boutiques.size() <= 4) {
                boutiques = boutiquesAll;
                boutonPMBoutiques = "minus";
            } else {
                boutiques = boutiquesAll.subList(0, 4);
                boutonPMBoutiques = "plus";
            }
        } catch (Exception e) {
            log.fatal("afficherPlusOuMoinsBoutiques() aurait du marcher ! ");
        }
    }

    public void afficherPlusOuMoinsArticles() {
        try {
            if (articles.size() <= 4) {
                articles = articlesAll;
                boutonPMArticles = "minus";
            } else {
                articles = articlesAll.subList(0, 4);
                boutonPMArticles = "plus";
            }
        } catch (Exception e) {
            log.fatal("afficherPlusOuMoinsArticles() aurait du marcher ! ");
        }
    }

    public void afficherPlusOuMoinsTutos() {

        try {
            if (tutoriels.size() <= 4) {
                tutoriels = tutorielsAll;
                boutonPMTuto = "minus";
            } else {
                tutoriels = tutorielsAll.subList(0, 4);
                boutonPMTuto = "plus";
            }
        } catch (Exception e) {
            log.fatal("afficherPlusOuMoinsTutos() aurait du marcher ! ");
        }
    }

    public void afficherPlusOuMoinsMembres() {
        try {
            if (membres.size() == 4) {
                membres = membresAll;
            } else {
                membres = membresAll.subList(0, 4);
                boutonPMMembres = "plus";
            }

        } catch (Exception e) {
            log.fatal("afficherPlusOuMoinsMembres() aurais du marcher ! ");
        }
    }

    public void afficherPlusOuMoinsEspaceTutos() {

        try {
            if (espaceTutos.size() <= 4) {
                espaceTutos = espaceTutosAll;
                boutonPMEspaceTutos = "minus";
            } else {
                espaceTutos = espaceTutosAll.subList(0, 4);
                boutonPMEspaceTutos = "plus";
            }
        } catch (Exception e) {
            log.fatal("afficherPlusOuMoinsEspaceTutos() aurait du marcher ! ");
        }
    }

    private void setBoutiquesToMembres(List<Membre> membres) {
        for (Membre membre : membres) {
            membre.setBoutique(gestionUtilBoutique.getBoutiqueByMembre(membre));
            membre.setEspaceTutoriel(gestionUtilEspaceTuto.getEspaceTutoByMembre(membre));
        }
    }

    public List<Categorie> chercherCategoriesEspaceTuto(Integer idEsp) {
        EspaceTutoriel esp = gestionUtilEspaceTuto.getEspaceTutorielById(idEsp);
        listeTutosFromEspaceTuto = gestionUtilTuto.getAllTutorielByEspaceTutorielAndDateRetraitIsNull(esp);

        listeCategoriesEspaceTuto = gestionUtilMisc.getCategoriesOfTutosByEspaceTuto(idEsp);
        categoriesEspaceTuto = new HashMap<EspaceTutoriel, List<Categorie>>();

        // remplir la liste des catégories des espaces tuto (à partir des
        // catégories de chaque tutoriel)
        categoriesEspaceTuto.put(esp, listeCategoriesEspaceTuto);

        return categoriesEspaceTuto.get(esp);
    }

    public List<Categorie> chercherCategoriesBoutique(Integer idBoutique) {

        // TODO Rajouter sur requete Spring-Data : AndDateRetraitIsNull
        Boutique boutique = gestionUtilBoutique.getBoutiqueById(idBoutique);
        listeArticlesFromBoutique = gestionUtilArticle.getArticlesBoutique(boutique);

        listeCategoriesBoutique = gestionUtilMisc.getCategoriesOfArticlesByBoutique(idBoutique);
        categoriesBoutique = new HashMap<Boutique, List<Categorie>>();

        // remplir la liste des catégories des boutiques (à partir des
        // catégories de chaque article)
        categoriesBoutique.put(boutique, listeCategoriesBoutique);

        return categoriesBoutique.get(boutique);
    }

    public void clickCheckboxBoutique() {
        if (boolCheckBoxBoutique == true) {
            boolCheckBoxBoutique = false;
        } else {
            boolCheckBoxBoutique = true;
        }
    }

    public void goToFicheArticle(Article article) {
        mbArticle.setArticle(article);
        mbArticle.init();
        mbNavig.goToArticle();
    }

    public void goToTuto(Tutoriel tuto) {
        mbNavig.goToAccueil();
    }

    public void goToBoutique(Boutique boutique) {
        mbBoutique.setBoutique(boutique);
        mbBoutique.init();
        mbNavig.goToBoutique();
    }

    public void goToEspaceTuto(EspaceTutoriel espTuto) {
        mbNavig.goToAccueil();
    }

    public Collection<Integer> getListeCategories() {
        return listeCategories;
    }

    public void setListeCategories(Collection<Integer> paramListeCategories) {
        listeCategories = paramListeCategories;
    }

    public Map<Integer, Boolean> getBooleanCheckboxByCategorieId() {
        return booleanCheckboxByCategorieId;
    }

    public void setBooleanCheckboxByCategorieId(Map<Integer, Boolean> paramBooleanCheckboxByCategorieId) {
        booleanCheckboxByCategorieId = paramBooleanCheckboxByCategorieId;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> paramArticles) {
        articles = paramArticles;
    }

    public String getBoutonPMArticles() {
        return boutonPMArticles;
    }

    public void setBoutonPMArticles(String paramBoutonPMArticles) {
        boutonPMArticles = paramBoutonPMArticles;
    }

    public List<Tutoriel> getTutoriels() {
        return tutoriels;
    }

    public void setTutoriels(List<Tutoriel> paramTutoriels) {
        tutoriels = paramTutoriels;
    }

    public List<Tutoriel> getTutorielsAll() {
        return tutorielsAll;
    }

    public void setTutorielsAll(List<Tutoriel> paramTutorielsAll) {
        tutorielsAll = paramTutorielsAll;
    }

    public String getBoutonPMTuto() {
        return boutonPMTuto;
    }

    public void setBoutonPMTuto(String paramBoutonPMTuto) {
        boutonPMTuto = paramBoutonPMTuto;
    }

    public List<Membre> getMembres() {
        return membres;
    }

    public void setMembres(List<Membre> paramMembres) {
        membres = paramMembres;
    }

    public String getBoutonPMMembres() {
        return boutonPMMembres;
    }

    public void setBoutonPMMembres(String paramBoutonPMMembres) {
        boutonPMMembres = paramBoutonPMMembres;
    }

    public List<Article> getArticlesAll() {
        return articlesAll;
    }

    public void setArticlesAll(List<Article> paramArticlesAll) {
        articlesAll = paramArticlesAll;
    }

    public List<EspaceTutoriel> getEspaceTutos() {
        return espaceTutos;
    }

    public void setEspaceTutos(List<EspaceTutoriel> paramEspaceTutos) {
        espaceTutos = paramEspaceTutos;
    }

    public List<EspaceTutoriel> getEspaceTutosAll() {
        return espaceTutosAll;
    }

    public void setEspaceTutosAll(List<EspaceTutoriel> paramEspaceTutosAll) {
        espaceTutosAll = paramEspaceTutosAll;
    }

    public String getBoutonPMEspaceTutos() {
        return boutonPMEspaceTutos;
    }

    public void setBoutonPMEspaceTutos(String paramBoutonPMEspaceTutos) {
        boutonPMEspaceTutos = paramBoutonPMEspaceTutos;
    }

    public List<Tutoriel> getListeTutosFromEspaceTuto() {
        return listeTutosFromEspaceTuto;
    }

    public void setListeTutosFromEspaceTuto(List<Tutoriel> paramListeTutosFromEspaceTuto) {
        listeTutosFromEspaceTuto = paramListeTutosFromEspaceTuto;
    }

    public List<Categorie> getListeCategoriesEspaceTuto() {
        return listeCategoriesEspaceTuto;
    }

    public void setListeCategoriesEspaceTuto(List<Categorie> paramListeCategoriesEspaceTuto) {
        listeCategoriesEspaceTuto = paramListeCategoriesEspaceTuto;
    }

    public Map<EspaceTutoriel, List<Categorie>> getCategoriesEspaceTuto() {
        return categoriesEspaceTuto;
    }

    public void setCategoriesEspaceTuto(Map<EspaceTutoriel, List<Categorie>> paramCategoriesEspaceTuto) {
        categoriesEspaceTuto = paramCategoriesEspaceTuto;
    }

    public List<Boutique> getBoutiques() {
        return boutiques;
    }

    public void setBoutiques(List<Boutique> paramBoutiques) {
        boutiques = paramBoutiques;
    }

    public List<Boutique> getBoutiquesAll() {
        return boutiquesAll;
    }

    public void setBoutiquesAll(List<Boutique> paramBoutiquesAll) {
        boutiquesAll = paramBoutiquesAll;
    }

    public String getBoutonPMBoutiques() {
        return boutonPMBoutiques;
    }

    public void setBoutonPMBoutiques(String paramBoutonPMBoutiques) {
        boutonPMBoutiques = paramBoutonPMBoutiques;
    }

    public List<Article> getListeArticlesFromBoutique() {
        return listeArticlesFromBoutique;
    }

    public void setListeArticlesFromBoutique(List<Article> paramListeArticlesFromBoutique) {
        listeArticlesFromBoutique = paramListeArticlesFromBoutique;
    }

    public List<Categorie> getListeCategoriesBoutique() {
        return listeCategoriesBoutique;
    }

    public void setListeCategoriesBoutique(List<Categorie> paramListeCategoriesBoutique) {
        listeCategoriesBoutique = paramListeCategoriesBoutique;
    }

    public Map<Boutique, List<Categorie>> getCategoriesBoutique() {
        return categoriesBoutique;
    }

    public void setCategoriesBoutique(Map<Boutique, List<Categorie>> paramCategoriesBoutique) {
        categoriesBoutique = paramCategoriesBoutique;
    }

    public Boolean getBoolCheckBoxBoutique() {
        return boolCheckBoxBoutique;
    }

    public void setBoolCheckBoxBoutique(Boolean paramBoolCheckBoxBoutique) {
        boolCheckBoxBoutique = paramBoolCheckBoxBoutique;
    }

    public List<Membre> getMembresAll() {
        return membresAll;
    }

    public void setMembresAll(List<Membre> paramMembresAll) {
        membresAll = paramMembresAll;
    }

}
