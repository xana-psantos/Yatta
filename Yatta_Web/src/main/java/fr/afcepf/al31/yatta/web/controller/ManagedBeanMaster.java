package fr.afcepf.al31.yatta.web.controller;

import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mbMaster")
// @ManagedBean(name = "mbMaster")
@SessionScoped
public class ManagedBeanMaster {

    private Logger log = Logger.getLogger(getClass());
    private String catSelected;
    private String saisieRecherche;
    @Autowired
    private ManagedBeanNavigation mbNavig;
    @Autowired
    private ManagedBeanRecherche mbRecherche;
    @Autowired
    private ManagedBeanProfil mbProfil;
    
    public void goToProfil() {
        mbProfil.setActiveIndex(ManagedBeanProfil.getIndexProfil());
        mbNavig.goToProfil();
    }
    
    public void goToCommandes() {
        mbProfil.setActiveIndex(ManagedBeanProfil.getIndexCommande());
        mbNavig.goToProfil();
    }
    
    public void goToBoutique() {
        mbProfil.setActiveIndex(ManagedBeanProfil.getIndexGestionBoutique());
        mbNavig.goToProfil();
    }
    
    public void goToEspaceTuto() {
        mbProfil.setActiveIndex(ManagedBeanProfil.getIndexGestionEspaceTuto());
        mbNavig.goToProfil();
    }
    
    public void rechercher() {
        mbRecherche.init();
        log.debug("Le mbRecherche aurais du initialiser !!!!");
        mbNavig.goToRechecher();
    }

    public String getSaisieRecherche() {
        return saisieRecherche;
    }

    public void setSaisieRecherche(String paramSaisieRecherche) {
        saisieRecherche = paramSaisieRecherche;
    }

    public String getCatSelected() {
        return catSelected;
    }

    public void setCatSelected(String paramCatSelected) {
        catSelected = paramCatSelected;
    }

}
