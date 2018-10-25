package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.entities.Personne;

@Component("mbNavigation")
//@ManagedBean(name="mbNavigation")
@SessionScope
public class ManagedBeanNavigation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ManagedBeanApplication mbApplication;
    
    private String pageAccueil = "/accueil.yatta?faces-redirect=true";
    private String pageBoutique = "/boutique.yatta?faces-redirect=true";
    private String pageRecherche = "/recherche.yatta?faces-redirect=true";
    private String pageProfil = "/profil.yatta?faces-redirect=true";
    private String pageArticle = "/article.yatta?faces-redirect=true";
    private String pageEspaceTuto = "/espaceTutoriels.yatta?faces-redirect=true";
    private String pagePanier = "/pagePanier.yatta?faces-redirect=true";

    private Personne user;

    private void navTo(String page) {
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance()
                .getApplication().getNavigationHandler();
        handler.performNavigation(page);
    }

    public void goToAccueil() {
        navTo(pageAccueil);
    }

    public void goToBoutique() {
        navTo(pageBoutique);
    }

    public void goToRechecher() {
        navTo(pageRecherche);
    }
    
    public void goToProfil() {
        navTo(pageProfil);
    }
    
    public void goToArticle() {
        navTo(pageArticle);
    }
    
    public void goToEspaceTuto() {
        navTo(pageEspaceTuto);
    }
    
    public void goToPanier() {
        navTo(pagePanier);
    }

    public String getPageAccueil() {
        return pageAccueil;
    }

    public void setPageAccueil(String paramPageAccueil) {
        pageAccueil = paramPageAccueil;
    }

    public String getPageBoutique() {
        return pageBoutique;
    }

    public void setPageBoutique(String paramPageBoutique) {
        pageBoutique = paramPageBoutique;
    }

    public String getPageRecherche() {
        return pageRecherche;
    }

    public void setPageRecherche(String paramPageRecherche) {
        pageRecherche = paramPageRecherche;
    }

    public Personne getUser() {
        return user;
    }

    public void setUser(Personne paramUser) {
        user = paramUser;
    }

    public ManagedBeanApplication getMbApplication() {
        return mbApplication;
    }

    public void setMbApplication(ManagedBeanApplication paramMbApplication) {
        mbApplication = paramMbApplication;
    }

    public String getPageProfil() {
        return pageProfil;
    }

    public void setPageProfil(String paramPageProfil) {
        pageProfil = paramPageProfil;
    }

    public String getPageArticle() {
        return pageArticle;
    }

    public void setPageArticle(String paramPageArticle) {
        pageArticle = paramPageArticle;
    }

    public String getPageEspaceTuto() {
        return pageEspaceTuto;
    }

    public void setPageEspaceTuto(String paramPageEspaceTuto) {
        pageEspaceTuto = paramPageEspaceTuto;
    }

    public String getPagePanier() {
        return pagePanier;
    }

    public void setPagePanier(String paramPagePanier) {
        pagePanier = paramPagePanier;
    }
}
