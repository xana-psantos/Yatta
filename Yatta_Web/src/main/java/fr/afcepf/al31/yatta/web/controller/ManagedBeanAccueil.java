package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.entities.Categorie;

@Component("mbAccueil")
//@ManagedBean(name = "mbAccueil")
@SessionScope
public class ManagedBeanAccueil implements Serializable {

    private static final long serialVersionUID = 1L;

    private Logger log = Logger.getLogger(getClass());
    
    @Autowired
    private ManagedBeanNavigation mbNav;
    @Autowired
    private ManagedBeanRecherche mbRecherche;
    @Autowired
    private ManagedBeanApplication mbApplication;
    
    private List<Integer> listeCategories;
    private Map<Integer, Boolean> booleanCheckboxByCategorieId;
    
    @PostConstruct
    public void init() {
        log.debug("affectation categories début");
        log.debug(mbApplication.getCatSelected());
        try {
            listeCategories = new ArrayList<Integer>();
            mbApplication.init();
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
        } catch (Exception e) {
            log.fatal("*****BIG MELT DOWN******");;
        }
        booleanCheckboxByCategorieId = new HashMap<Integer, Boolean>();
        for (Integer categorieId : listeCategories) {
            booleanCheckboxByCategorieId.put(categorieId, true);
        }
        log.debug(listeCategories.toString());
        log.debug("affectation categories fin");
    }
    public void goToPageRecherche() {
        mbRecherche.setListeCategories(listeCategories);
        mbRecherche.setBooleanCheckboxByCategorieId(booleanCheckboxByCategorieId);
        mbRecherche.init();
        mbNav.goToRechecher();
    }
    public List<Integer> getListeCategories() {
        return listeCategories;
    }
    public void setListeCategories(List<Integer> paramListeCategories) {
        listeCategories = paramListeCategories;
    }
    public Map<Integer, Boolean> getBooleanCheckboxByCategorieId() {
        return booleanCheckboxByCategorieId;
    }
    public void setBooleanCheckboxByCategorieId(Map<Integer, Boolean> paramBooleanCheckboxByCategorieId) {
        booleanCheckboxByCategorieId = paramBooleanCheckboxByCategorieId;
    }
    
    
    
}
