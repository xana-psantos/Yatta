package fr.afcepf.al31.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al31.yatta.business.api.IGestionCommande;
import fr.afcepf.al31.yatta.business.api.IGestionVendeur;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilArticle;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Membre;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestGestionVendeur {
    
    private static final int QUANTITE = 17;
    @Autowired
    private IGestionVendeur gestionVendeur;
    
    @Autowired
    private IGestionCommande gestionCommande;
    
    @Autowired
    private IGestionUtilArticle gestionUtilArticle;
    
    @Autowired
    private IGestionUtilMisc gestionUtilMisc;
    
    private Article article11;
    
    private List<Commande> commandes = new ArrayList<>();
    
    private Membre membre;
    
    @Test
    public void testPasserCommande() {
        //TODO : check values within database
        /*article11 = gestionUtilArticle.getArticleById(11);
        membre = gestionUtilMisc.getMembreById(2);
        commandes = gestionCommande.ajouterArticlePanier(commandes, article11, QUANTITE);
        Assert.assertNotNull(commandes.get(0));
        for (Commande commande : commandes) {
            commande = gestionCommande.passerCommande(commande, membre);
            Assert.assertNotNull(commande.getDateValidation());
            Assert.assertNotNull(commande.getDateLivraison());
        }*/
    }
}
