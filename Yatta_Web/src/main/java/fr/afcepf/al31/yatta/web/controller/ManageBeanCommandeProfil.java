package fr.afcepf.al31.yatta.web.controller;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.IGestionCommande;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Membre;

@Component("mbCommandeProfil")
//@ManagedBean(name="mbCommandeProfil")
@SessionScope
public class ManageBeanCommandeProfil implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private static Logger log = Logger.getLogger(ManageBeanCommandeProfil.class);
    
    @Autowired
    private IGestionUtilMisc gestionUtilMisc;
    
    @Autowired
    private IGestionCommande gestionCommande;
    @Autowired
    private ManagedBeanNavigation mbNav;
    @Autowired
    private ManagedBeanProfil mbProfil;
    
    private boolean showInfosBoutique = true;
    private boolean showCommande = false;
    private boolean showArticle = false;

    private Date maintenant = new Date();
    private List<Commande> commandeRecu;
    private Membre membre;
    
    private File fileUpload;
    
    private List<Commande> commmandePassees;
    @PostConstruct
    public void init() {
        log.debug("au debut d'init du mbCommandeProfil");
        if (membre == null) {
            membre = gestionUtilMisc.getMembreById(6);
        }
        membre = mbProfil.getMembreProfil();
        commandeRecu = gestionCommande.getCommandeByBoutique(mbProfil.getBoutiqueProfil());
        for (Commande commande : commandeRecu) {
            commande.setLignesDeCommande(gestionCommande.getAllLignesCommande(commande));
        }
        commmandePassees = gestionCommande.getCommandeByMembre(membre);
        log.debug("size commande passees" + commmandePassees.size());
        for (Commande commande : commmandePassees) {
            commande.setLignesDeCommande(gestionCommande.getAllLignesCommande(commande));
            log.debug("id commande ="+commande.getId());
        }
    }
    
    public void afficherInfosBoutique() {
        log.debug("dans afficher infosBoutique");
        showInfosBoutique = true;
        showArticle = false;
        showCommande = false;
    }
    
    public void afficherCommande() {
        log.debug("dans afficher commande");
        showInfosBoutique =false;
        showArticle = false;
        showCommande = true;
        log.debug("showCommande " + showCommande);
    }
    public void afficherArticle() {
        log.debug("dans afficher Article");
        showInfosBoutique = false;
        showArticle = true;
        showCommande = false;
    }
    public List<Commande> getCommandeRecu() {
        return commandeRecu;
    }
    public void setCommandeRecu(List<Commande> paramCommandeRecu) {
        commandeRecu = paramCommandeRecu;
    }
    
    public List<Commande> getCommmandePassees() {
        return commmandePassees;
    }
    public void setCommmandePassees(List<Commande> paramCommmandePassees) {
        commmandePassees = paramCommmandePassees;
    }
    public Date getMaintenant() {
        return maintenant;
    }
    public void setMaintenant(Date paramMaintenant) {
        maintenant = paramMaintenant;
    }
    public boolean isShowInfosBoutique() {
        return showInfosBoutique;
    }
    public void setShowInfosBoutique(boolean paramShowInfosBoutique) {
        showInfosBoutique = paramShowInfosBoutique;
    }
    public boolean isShowCommande() {
        return showCommande;
    }
    public void setShowCommande(boolean paramShowCommande) {
        showCommande = paramShowCommande;
    }
    public boolean isShowArticle() {
        return showArticle;
    }
    public void setShowArticle(boolean paramShowArticle) {
        showArticle = paramShowArticle;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File paramFileUpload) {
        fileUpload = paramFileUpload;
    }
  
    
}
