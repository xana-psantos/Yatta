package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.IGestionCommande;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.LigneDeCommande;

@Component("mbCommandePassee")
//@ManagedBean(name="mbCommandePassee")
@SessionScope
public class ManageBeanCommandePassee implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private IGestionCommande gestionCommande;
    
    @Autowired
    private ManagedBeanProfil mbProfil;

    
    private Commande commandeSelectionneeDataTable;
    
    
    @PostConstruct
    private void init() {
       commandeSelectionneeDataTable = gestionCommande.getCommandeById(1);
    }
    
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
    
    public void clickSelectionCommandePassee(Commande commande){
        commandeSelectionneeDataTable = commande;
        mbProfil.setShowCommandeRecue(false);
        mbProfil.setShowCommandePassee(true);
    }
    

    public Commande getCommandeSelectionneeDataTable() {
        return commandeSelectionneeDataTable;
    }

    public void setCommandeSelectionneeDataTable(Commande paramCommandeSelectionneeDataTable) {
        commandeSelectionneeDataTable = paramCommandeSelectionneeDataTable;
    }

    
    
    
}
