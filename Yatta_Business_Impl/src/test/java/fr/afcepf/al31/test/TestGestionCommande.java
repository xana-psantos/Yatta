package fr.afcepf.al31.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.log.Log;

import fr.afcepf.al31.yatta.business.api.IGestionCommande;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Membre;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestGestionCommande {

    private Logger log = Logger.getLogger(getClass());
    
    @Autowired
    private IGestionCommande gestionCommande;
    
    @Autowired
    private IGestionUtilMisc gestionUtilMisc;
    
    private Membre membre;
    private List<Commande> listeLignesCommandesMembres;
     
    @Test
    public void TestRetourLignesCommandePanier() {
         membre = gestionUtilMisc.getMembreById(8);
         
         listeLignesCommandesMembres =  gestionCommande.getCommandePanier(membre);
         
         Assert.assertTrue(listeLignesCommandesMembres.size() == 1);
         Assert.assertTrue(listeLignesCommandesMembres.get(0).getId() == 12);
         Assert.assertNotNull(listeLignesCommandesMembres.get(0).getLignesDeCommande().get(0));
    }
    @Test
    public void testGetByID() {
        Commande com = gestionCommande.getCommandeById(1);
        Assert.assertNotNull(com);
        Assert.assertTrue(com.getLignesDeCommande().size()==5);
    }
    @Test
    public void testCommandePassees() {
        Membre membre = gestionUtilMisc.getMembreById(10);
        List<Commande> commandes = gestionCommande.getCommandeByMembre(membre);
        for (Commande commande : commandes) {
           log.debug("id commande :" +commande.getId());
        }
    }
}
