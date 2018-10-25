package fr.afcepf.al31.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al31.yatta.business.api.IGestionConnexion;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Personne;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestGestionConnexion {
    
    private static final Membre membreExistant = new Membre();
    
    static {
        membreExistant.setMotDePasse("juldib");
        membreExistant.setMail("juliendiberardino@gmail.com");
        membreExistant.setPseudonyme("Le pharmacien");
    }

    @Autowired
    private IGestionConnexion gestionConnexion;
    
    /**
     * Test connexion par mail;
     */
    @Test
    public void testAuthentificationByMail() {
        try {
        Personne persConnecte = 
                gestionConnexion.authentification(membreExistant.getMail(),membreExistant.getMotDePasse());
        Assert.assertNotNull(persConnecte);
        Assert.assertNotNull(persConnecte.getId());
        Assert.assertNotNull(persConnecte.getNom());
        Assert.assertNotNull(persConnecte.getPrenom());
        Assert.assertNotNull(persConnecte.getMail());
        Assert.assertNotNull(persConnecte.getMotDePasse());
        Assert.assertEquals(persConnecte.getMail(), membreExistant.getMail());
        Assert.assertEquals(persConnecte.getMotDePasse(), membreExistant.getMotDePasse());
        } catch (Exception e) {
            Assert.fail("Ça aurait du marcher");
            e.printStackTrace();
        }
    }
    /**
     * Test connexion par pseudonyme;
     */
    @Test
    public void testAuthentificationByPseudonyme() {
        try {
        Personne persConnecte = 
                gestionConnexion.authentification(membreExistant.getPseudonyme(),membreExistant.getMotDePasse());
        Assert.assertNotNull(persConnecte);
        Assert.assertNotNull(persConnecte.getId());
        Assert.assertNotNull(persConnecte.getNom());
        Assert.assertNotNull(persConnecte.getPrenom());
        Assert.assertNotNull(persConnecte.getMail());
        Assert.assertNotNull(persConnecte.getMotDePasse());
        Assert.assertEquals(persConnecte.getMail(), membreExistant.getMail());
        Assert.assertEquals(persConnecte.getMotDePasse(), membreExistant.getMotDePasse());
        } catch (Exception e) {
            Assert.fail("Ça aurait du marcher");
            e.printStackTrace();
        }
    }
}
