package fr.afcepf.al31.yatta.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.IGestionCommande;
import fr.afcepf.al31.yatta.business.api.IGestionVendeur;
import fr.afcepf.al31.yatta.dao.api.IDaoBoutique;
import fr.afcepf.al31.yatta.dao.api.IDaoCommande;
import fr.afcepf.al31.yatta.dao.api.IDaoLigneCommande;
import fr.afcepf.al31.yatta.dao.api.IDaoMoyenPaiement;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.LigneDeCommande;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.MoyenPaiement;
@Service
@Transactional
public class GestionCommande implements IGestionCommande{
    
    private Logger log = Logger.getLogger(getClass());
    @Autowired
    private IDaoBoutique daoBoutique;
    @Autowired
    private IDaoCommande daoCommande;
    @Autowired
    private IDaoLigneCommande daoLigneCommande;
    
    private IDaoMoyenPaiement daoMoyenPaiement;
    
    @Autowired
    private IGestionVendeur GestionVendeur;
    
    @Override
    public Commande getCommandeById(Integer paramId) {
        return daoCommande.afficherCommandeById(paramId);
    }

    @Override
    public List<Commande> getCommandeByBoutique(Boutique paramBoutique) {
        return daoCommande.getCommandeRecus(paramBoutique.getMembre());
    }

    @Override
    public List<Commande> getCommandeByMembre(Membre paramMembre) {
        return daoCommande.findParAcheteur(paramMembre.getId());
    }
    @Override
    public List<Commande> getCommandePanier(Membre paramAcheteur){
        return daoCommande.getCommandePanier(paramAcheteur.getId());
    }

    @Override
    public Commande creerCommande(Commande paramCommande) {
        return daoCommande.save(paramCommande);
    }

    @Override
    public Commande ajouterLigneDeCommande(LigneDeCommande paramLigneCommande) {
        if ((paramLigneCommande.getArticle() != null) && (paramLigneCommande.getCommande() != null)) {
            daoLigneCommande.save(paramLigneCommande);
            return paramLigneCommande.getCommande(); 
        }
        return null;
    }

   
    
    @Override
    public Commande validerCommande(Commande paramCommande) {
        paramCommande.setDateValidation(new Date());
        return daoCommande.save(paramCommande);
    }

    @Override
    public Commande modifierCommande(Commande paramCommande) {
        return daoCommande.save(paramCommande);
    }

    @Override
    public List<LigneDeCommande> getAllLignesCommande(Commande paramCommande) {
        return daoLigneCommande.findByCommande(paramCommande);
    }

    @Override
    public Commande annulerCommande(Commande paramCommande) {
        return null;
    }

    @Override
    public boolean payerCommande(Commande paramCommande) {
       boolean operationReussi = true;
       //TODO lorsque webService implementer changer valeur boolean en cas d'erreur
       return operationReussi;
    }
    
    @Override
    public Commande passerCommande(Commande paramCommande, Membre membre) {
        try {
            boolean toutStockOk = true;
            //Verif du stock pour chaque ligne de commande.
            for (LigneDeCommande ligne : paramCommande.getLignesDeCommande()) {
                if(verifierStock(ligne) == false) {
                    toutStockOk = false;
                }
            }
            log.debug("toutStockOK = "+toutStockOk);
            //Si stock ok, dimminution des quantité des stocks de chaque article commandé.
            if(toutStockOk) {
                log.debug("coucou je suis apres toutStockOK");
                for (LigneDeCommande ligne : paramCommande.getLignesDeCommande()) {
                    ligne.getArticle().setStock(ligne.getArticle().getStock() - ligne.getQuantite());
                    GestionVendeur.modifierArticle(ligne.getArticle());
                } 
                //Paiement de la commande, si réussi sauvegarde de la commande, sinon réajustement des stocks.
                if (payerCommande(paramCommande)) {
                    log.debug("coucou je suis apres payer");
                   paramCommande = modifierCommande(paramCommande);
                }else {
                    for (LigneDeCommande ligne : paramCommande.getLignesDeCommande()) {
                        ligne.getArticle().setStock(ligne.getArticle().getStock() + ligne.getQuantite());
                        GestionVendeur.modifierArticle(ligne.getArticle());
                    } 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return paramCommande;
    }
    @Override
    public Boolean verifierStock(LigneDeCommande paramLigne) {
        log.debug("dans verif stock");
        boolean estEnStock;
        if(paramLigne.getQuantite() <= paramLigne.getArticle().getStock()){
            estEnStock = true;
        }else {
            estEnStock = false;
        }
        return estEnStock;
    }

    @Override
    public void editerFacture(Commande paramCommande) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void envoyerFactureParMail(Commande paramCommande) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Commande ajouterNoteAvisCommande(Commande paramCommande) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double calculerPrixHTCommande(Commande paramCommande) {
        paramCommande = this.getCommandeById(paramCommande.getId());
        double somme=0.0;
        for (LigneDeCommande ligneDeCommande : paramCommande.getLignesDeCommande()) {
            double totalArticle = ligneDeCommande.getArticle().getPrix() * ligneDeCommande.getQuantite();
            somme += totalArticle;
        }
        return somme;
    }

    @Override
    public Double calculerPrixTTCCommande(Commande paramCommande) {
        return calculerPrixHTCommande(paramCommande);
    }

    @Override
    public List<Commande> ajouterArticlePanier(List<Commande> paramPanier, Article paramArticle, int paramQuantite) {
        // Si la quantité demandé est en stock
        if (paramArticle.getStock() >= paramQuantite) {
            Commande cmdExistante = null;
            // Test pour chaque commande si il y en a une pour la boutique de l'article ajouté.
            for (Commande commande : paramPanier) {
                if (daoBoutique.getBoutiqueParCommande(commande.getId()).getId().equals(paramArticle.getBoutique().getId())) {
                    cmdExistante = commande;
                }
            }
            if (cmdExistante == null) {
                log.debug("La boutique n'est pas dans la commande !");
                cmdExistante = new Commande();
                cmdExistante.setDateCreation(new Date());
                cmdExistante.setLignesDeCommande(new ArrayList<>());
                cmdExistante.getLignesDeCommande().add(new LigneDeCommande(null, paramQuantite, cmdExistante, paramArticle));
                cmdExistante=this.creerCommande(cmdExistante);
                for (LigneDeCommande ligne : cmdExistante.getLignesDeCommande()) {
                    cmdExistante = this.ajouterLigneDeCommande(ligne);
                }
                paramPanier.add(cmdExistante);
            }
            else {
                log.debug("Le boutique est déjà dans la commande !");
                boolean existProduit = false;
                for (LigneDeCommande ldc : cmdExistante.getLignesDeCommande()) {
                    if (ldc.getArticle().getId().equals(paramArticle.getId()))
                    {
                        int qnt = ldc.getQuantite() + paramQuantite;
                        if (qnt < paramArticle.getStock()) {
                            ldc.setQuantite(qnt);
                        }
                        else {
                            ldc.setQuantite(paramArticle.getStock());
                        }
                        existProduit = true;
                    }
                }
                if (!existProduit) {
                    cmdExistante.getLignesDeCommande().add(new LigneDeCommande(null, paramQuantite, cmdExistante, paramArticle));
                    log.debug("On a rajouté la ligne de commande à la commande existante !");
                }
            }
        }
        else {
            log.debug("La quantité demandé est trop importante !");
        }
        
        return paramPanier;
    }

    @Override
    public MoyenPaiement getMoyenPaiementByID(Integer paramId) {
        return daoMoyenPaiement.getOne(paramId);
    }

    @Override
    public Commande ajouterDateLivraison(Commande paramCommande, Date paramDateLivraison) {
        paramCommande.setDateLivraison(paramDateLivraison);
        return daoCommande.save(paramCommande);
    }

 

 


}

