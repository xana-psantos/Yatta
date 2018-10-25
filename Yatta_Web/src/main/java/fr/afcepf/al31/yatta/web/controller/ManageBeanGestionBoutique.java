package fr.afcepf.al31.yatta.web.controller;

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
import fr.afcepf.al31.yatta.entities.Membre;

@Component("mbGestionBoutique")
//@ManagedBean(name="mbGestionBoutique")
@SessionScope
public class ManageBeanGestionBoutique {
    @Autowired
    private ManagedBeanNavigation mbNavigation;
    @Autowired
    private IGestionUtilArticle gestionUtilArticle;
    @Autowired
    private IGestionUtilBoutique gestionUtilBoutique;
    
    private List<Categorie> categoriesBoutique;
    
    private Boutique boutique = null;
    private List<Article> articlesBoutique;
    private Logger log = Logger.getLogger(getClass());
    
    @PostConstruct
    public void init(){
        try {
            if(boutique == null) {
                boutique = gestionUtilBoutique.getBoutiqueById(6);
            }
            if(mbNavigation.getUser() != null) {
                boutique = gestionUtilBoutique.getBoutiqueByMembre((Membre)mbNavigation.getUser());
            }
            categoriesBoutique = gestionUtilBoutique.getAllCategorieBoutiques(boutique);
            articlesBoutique = gestionUtilArticle.getArticlesBoutique(boutique);
            for (Article article : articlesBoutique) {
                article.calculerNoteMoyenne(article);    
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.fatal("l'init() rencontre un obstacle de betises!!!! Il y a FORCEMENT une erreur, dixit Séb");
        } 
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
    
    
}
