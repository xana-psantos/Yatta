package fr.afcepf.al31.yatta.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.MoyenPaiement;

@Component("mbApplication")
// @ManagedBean(name = "mbApplication")
@SessionScope
public class ManagedBeanApplication {

    private Logger log = Logger.getLogger(getClass());
    private List<Categorie> categories;
    private HashMap<String, Integer> categoriesMap;
    private String catSelected;
    private List<MoyenPaiement> moyensPaiement;

    @Autowired
    private IGestionUtilMisc gestionUtilMisc;

    @PostConstruct
    public void init() {
        try {
            categories = gestionUtilMisc.getAllCategories();
            categoriesMap = new HashMap<>();
            for (Categorie cat : categories) {
                categoriesMap.put(cat.getLibelle(), cat.getId());
            }
            moyensPaiement = gestionUtilMisc.getAllMoyenPaiement();
        } catch (Exception e) {
            log.fatal("init() aurais du marcher ! ");
        }
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> paramCategories) {
        categories = paramCategories;
    }

    public List<MoyenPaiement> getMoyensPaiement() {
        return moyensPaiement;
    }

    public void setMoyensPaiement(List<MoyenPaiement> paramMoyensPaiement) {
        moyensPaiement = paramMoyensPaiement;
    }

    public IGestionUtilMisc getGestionUtilMisc() {
        return gestionUtilMisc;
    }

    public void setGestionUtilMisc(IGestionUtilMisc paramGestionUtilMisc) {
        gestionUtilMisc = paramGestionUtilMisc;
    }

    public String getCatSelected() {
        return catSelected;
    }

    public void setCatSelected(String paramCatSelected) {
        catSelected = paramCatSelected;
    }

    public HashMap<String, Integer> getCategoriesMap() {
        return categoriesMap;
    }

    public void setCategoriesMap(HashMap<String, Integer> paramCategoriesMap) {
        categoriesMap = paramCategoriesMap;
    }

}
