package fr.afcepf.al31.yatta.business.api;

import java.util.Date;
import java.util.List;

import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.LigneDeCommande;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.MoyenPaiement;

public interface IGestionCommande {

    Commande getCommandeById(Integer id);

    List<Commande> getCommandeByBoutique(Boutique boutique);

    List<Commande> getCommandeByMembre(Membre membre);
    
    List<Commande> getCommandePanier(Membre acheteur);

    Commande creerCommande(Commande commande);

    Commande ajouterLigneDeCommande(LigneDeCommande ligneCommande);

    Commande validerCommande(Commande commande);
    
    Commande ajouterDateLivraison(Commande paramCommande, Date dateLivraison);

    Commande modifierCommande(Commande commande);

    List<LigneDeCommande> getAllLignesCommande(Commande commande);

    Commande annulerCommande(Commande commande);

    boolean payerCommande(Commande commande);
    
    Commande passerCommande(Commande commande, Membre membre);

    Boolean verifierStock(LigneDeCommande ligne);
    
    void editerFacture(Commande commande);

    void envoyerFactureParMail(Commande commande);

    Commande ajouterNoteAvisCommande(Commande commande);

    Double calculerPrixHTCommande(Commande commande);

    Double calculerPrixTTCCommande(Commande commande);
    
    List<Commande> ajouterArticlePanier(List<Commande> panier, Article article, int quantite);
    
    MoyenPaiement getMoyenPaiementByID(Integer id);
}
