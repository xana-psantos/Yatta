package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.IGestionCommande;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.LigneDeCommande;

@Component("mbCommandeRecue")
//@ManagedBean(name="mbCommandeRecue")
@SessionScope
public class ManageBeanCommandeRecue implements Serializable{

    private static final long serialVersionUID = 1L;

    @Autowired
    private IGestionCommande gestionCommande;
    
    @Autowired
    private ManagedBeanProfil mbProfil;
    
    @Autowired
    private ManagedBeanNavigation mbNavig;

    
    private Commande commandeSelectionneeDataTable;
    private Date dateLivraisonCommande = new Date();
   
    

    
    public String recupererVendeurparCommande(Commande commande){
       List<LigneDeCommande> lignesDeCommande= commande.getLignesDeCommande();
       String nomVendeur ="";
       for (LigneDeCommande ligneDeCommande : lignesDeCommande) {
        nomVendeur=ligneDeCommande.getArticle().getBoutique().getMembre().getPseudonyme();
    }
       return nomVendeur;
    }
    
    public Double calculerPrixCommandeTTC(Commande commande){
        return gestionCommande.calculerPrixTTCCommande(commande);
    }
    
    public void clickSelectionCommandeRecue(Commande commande, String typeOfSelection, String indexes){
        commandeSelectionneeDataTable = commande;
        mbProfil.setShowCommandeRecue(true);
        mbProfil.setShowCommandePassee(false);
        mbProfil.setActiveIndex(ManagedBeanProfil.getIndexCommandeRecue());
        mbNavig.goToProfil();
    }
    
    public void clickRetourCommandes(){
        mbProfil.setActiveIndex(ManagedBeanProfil.getIndexCommande());
        mbNavig.goToProfil();
    }
    
    public void validerCommande(Commande commande, Date dateLivraison){
        gestionCommande.ajouterDateLivraison(commande, dateLivraison);
        gestionCommande.validerCommande(commande);
    }
    

    public Commande getCommandeSelectionneeDataTable() {
        return commandeSelectionneeDataTable;
    }

    public void setCommandeSelectionneeDataTable(Commande paramCommandeSelectionneeDataTable) {
        commandeSelectionneeDataTable = paramCommandeSelectionneeDataTable;
    }

    public Date getDateLivraisonCommande() {
        return dateLivraisonCommande;
    }

    public void setDateLivraisonCommande(Date paramDateLivraisonCommande) {
        dateLivraisonCommande = paramDateLivraisonCommande;
    }

    
    
    
}
