package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.IGestionCommande;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.entities.Adresse;
import fr.afcepf.al31.yatta.entities.AdresseMembre;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.LigneDeCommande;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.MoyenPaiement;


@Component("mbPanier")
//@ManagedBean(name="mbPanier")
@SessionScope
public class ManagedBeanPanier implements Serializable {

    private static final long serialVersionUID = 1L;


    @Autowired
    private IGestionCommande gestionCommande;

    @Autowired
    private ManagedBeanNavigation mbNav;
    @Autowired
    private IGestionUtilMisc gestionUtilMisc;
    private List<Commande> listeCommandesMembre;
    private Double prixTotal= 0.0;
    private int compteurPagePourRendered=1;
    @Autowired
    private ManagedBeanConnexion mbConnexion;
    @Autowired
    private ManageBeanCommandeProfil mbCommandeProfil;
    private int nbArticles = 0;
    private Article article;
    private Adresse adresseLivraisonduMembre = new Adresse();

    private int quantite = 0;
    private Adresse adresseFacturation = new Adresse();
    
    private Boolean adresseLivraison =false;
    
    private static Logger log = Logger.getLogger(ManagedBeanPanier.class);
    
    private MoyenPaiement moyenPaiement;
    
    private List<MoyenPaiement> moyensPaiement;
    private String numeroCarte = "1234567891011";
    private String dateExpiration = "12/2020";
    private String crytogramme = "123";
    private MoyenPaiement moyenPaiementSelct;
   // @Enumerated(EnumType.STRING)
   // private Pays enumPays;

    //private Pays[] listePays;
    @Autowired
    private ManagedBeanProfil mbProfil; 
    
    @PostConstruct
    public void init(){

        //Récupération des commandes non validées de l'acheteur 
        if (mbNav.getUser()!=null && mbNav.getUser().getClass()== Membre.class) {
            //listeCommandesMembre =  gestionCommande.getCommandePanier((Membre) mbNav.getUser());
            listeCommandesMembre =  gestionCommande.getCommandePanier(gestionUtilMisc.getMembreById(8));

        }
        else {
            listeCommandesMembre=new ArrayList<>();
        }

        moyensPaiement = gestionUtilMisc.getAllMoyenPaiement();
        moyenPaiementSelct = new MoyenPaiement();
       // listeCommandesMembre =  gestionCommande.getCommandePanier(gestionUtilMisc.getMembreById(8));

        nbArticles = 0;
        for (Commande commande : listeCommandesMembre) {
            nbArticles+= commande.getLignesDeCommande().size();
        }
        //Pour demo, par defaut adresse Lieve
        adresseLivraisonduMembre = gestionUtilMisc.getAdresseById(9);
        

        //Calcul du prix du panier
        prixTotal = 0.0;
        for (Commande commande : listeCommandesMembre) {
            for (LigneDeCommande ligne : commande.getLignesDeCommande()) {
                prixTotal += ligne.getArticle().getPrix() * ligne.getQuantite();
            } 
            //prixTotal += gestionCommande.calculerPrixHTCommande(commande); 
        }
    }

    //Navigation entre les diverses des commandes
    public void UnePageEnArriere() {
        if (compteurPagePourRendered > 1 ) {
            compteurPagePourRendered = compteurPagePourRendered - 1;
        }   

    }

    public void UnePageEnAvant() {
        if (compteurPagePourRendered == 1 && mbNav.getUser()==null) {
            compteurPagePourRendered = 4;
        }
        if (compteurPagePourRendered < 3 )  {
            compteurPagePourRendered = compteurPagePourRendered + 1;
        }   

    }
    public void annuler() {
        mbNav.goToAccueil();;
    }
    public void connect() {
        log.debug("je suis au debut");
        mbConnexion.connect();
        this.compteurPagePourRendered = 2;
        log.debug("je suis à la fin");
        mbNav.goToPanier();
    }
    
    
    public void passerCommande() {
        //POur test
        adresseLivraison=true;
        if (adresseLivraison) {
            adresseFacturation = adresseLivraisonduMembre;
        }
        AdresseMembre adresseMembreLivraison = gestionUtilMisc.getAdresseMembreByAdresse(adresseLivraisonduMembre);
        AdresseMembre adresseMembreFacturation = gestionUtilMisc.getAdresseMembreByAdresse(adresseFacturation);
        //TODO : recupe et stockage des adresses
        
        //Pour demo
        //Todo: recuperer vrai moyen de payement
        moyenPaiementSelct = moyensPaiement.get(1);
        
        if(mbNav.getUser()!=null && mbNav.getUser().getClass()== Membre.class) {
            for (Commande commande : listeCommandesMembre) {
                commande.setAcheteur((Membre)mbNav.getUser());
                commande.setMoyenPaiement(moyenPaiementSelct);
                commande.setAdresseFacturation(adresseMembreLivraison);
                commande.setAdresseLivraison(adresseMembreFacturation);
                commande = gestionCommande.passerCommande(commande, (Membre)mbNav.getUser());
                log.debug("je suis apres passerCommande");
            }  
        }
        compteurPagePourRendered = 5;
        adresseLivraison=false;
        listeCommandesMembre = new ArrayList<>();
        nbArticles = 0;
        prixTotal = 0.0;
        mbProfil.setActiveIndex(ManagedBeanProfil.getIndexCommande());
        mbProfil.init();
        mbCommandeProfil.init();
        log.debug("après mbCommande.init dans passer commande");
        mbNav.goToProfil();
    }
    
    public void afficherAdresseLivraison() {
        adresseLivraison = true;
    }
    
    public void ajouterAuPanier(Article paramArticle, int paramQuantité) {

        log.debug(paramQuantité);
        listeCommandesMembre = gestionCommande.ajouterArticlePanier(listeCommandesMembre, paramArticle, paramQuantité);
        nbArticles = 0;
        for (Commande commande : listeCommandesMembre) {
            nbArticles+= commande.getLignesDeCommande().size();
        }
      //Calcul du prix du panier
        prixTotal = 0.0;
        for (Commande commande : listeCommandesMembre) {
            for (LigneDeCommande ligne : commande.getLignesDeCommande()) {
                prixTotal += ligne.getArticle().getPrix() * ligne.getQuantite();
            } 
            //prixTotal += gestionCommande.calculerPrixHTCommande(commande); 
        }
        quantite =0;
    }
    
    public void annulerQuantite() {
        mbNav.goToAccueil();
    }
    public void recupererArticle(Article paramArticle) {
        log.debug("je suis dans recup article");
        article = paramArticle;
        log.debug(paramArticle);
    }

    public int getQuantite() {
        return quantite;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article paramArticle) {
        article = paramArticle;
    }

    public void setQuantite(int paramQuantite) {
        quantite = paramQuantite;
    }

    public int getNbArticles() {
        return nbArticles;
    }

    public void setNbArticles(int paramNbArticles) {
        nbArticles = paramNbArticles;
    }

    public String getNumeroCarte() {
        return numeroCarte;
    }

    public void setNumeroCarte(String paramNumeroCarte) {
        numeroCarte = paramNumeroCarte;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String paramDateExpiration) {
        dateExpiration = paramDateExpiration;
    }

    public String getCrytogramme() {
        return crytogramme;
    }

    public void setCrytogramme(String paramCrytogramme) {
        crytogramme = paramCrytogramme;
    }

    public MoyenPaiement getMoyenPaiementSelct() {
        return moyenPaiementSelct;
    }

    public void setMoyenPaiementSelct(MoyenPaiement paramMoyenPaiementSelct) {
        moyenPaiementSelct = paramMoyenPaiementSelct;
    }

    public List<MoyenPaiement> getMoyensPaiement() {
        return moyensPaiement;
    }

    public void setMoyensPaiement(List<MoyenPaiement> paramMoyensPaiement) {
        moyensPaiement = paramMoyensPaiement;
    }

    public MoyenPaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(MoyenPaiement paramMoyenPaiement) {
        moyenPaiement = paramMoyenPaiement;
    }

    public Adresse getAdresseLivraisonduMembre() {
        return adresseLivraisonduMembre;
    }

    public void setAdresseLivraisonduMembre(Adresse paramAdresseLivraisonduMembre) {
        adresseLivraisonduMembre = paramAdresseLivraisonduMembre;
    }

    public Adresse getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(Adresse paramAdresseFacturation) {
        adresseFacturation = paramAdresseFacturation;
    }

    public Boolean getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(Boolean paramAdresseLivraison) {
        adresseLivraison = paramAdresseLivraison;
    }

    public ManagedBeanConnexion getMbConnexion() {
        return mbConnexion;
    }

    public void setMbConnexion(ManagedBeanConnexion paramMbConnexion) {
        mbConnexion = paramMbConnexion;
    }

    public int getCompteurPagePourRendered() {
        return compteurPagePourRendered;
    }
    public void setCompteurPagePourRendered(int paramCompteurPagePourRendered) {
        compteurPagePourRendered = paramCompteurPagePourRendered;
    }


    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double paramPrixTotal) {
        prixTotal = paramPrixTotal;
    }


    public IGestionCommande getGestionCommande() {
        return gestionCommande;
    }



    public void setGestionCommande(IGestionCommande paramGestionCommande) {
        gestionCommande = paramGestionCommande;
    }



    public ManagedBeanNavigation getMbNav() {
        return mbNav;
    }



    public void setMbNav(ManagedBeanNavigation paramMbNav) {
        mbNav = paramMbNav;
    }



    public IGestionUtilMisc getGestionUtilMisc() {
        return gestionUtilMisc;
    }



    public void setGestionUtilMisc(IGestionUtilMisc paramGestionUtilMisc) {
        gestionUtilMisc = paramGestionUtilMisc;
    }



    public List<Commande> getListeCommandesMembre() {
        return listeCommandesMembre;
    }



    public void setListeCommandesMembre(List<Commande> paramListeCommandesMembres) {
        listeCommandesMembre = paramListeCommandesMembres;
    }



    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}

