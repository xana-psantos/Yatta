package fr.afcepf.al31.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al31.yatta.business.api.IBusinessBidon;
import fr.afcepf.al31.yatta.business.api.IGestionCreateur;
import fr.afcepf.al31.yatta.business.api.IGestionMembre;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.business.impl.GestionMembre;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestMembre {
    private Logger log = Logger.getLogger(getClass());
    
    @Autowired
    private IBusinessBidon businessBidon;
    
    @Autowired
    private IGestionUtilMisc businessUtilMisc;
    
    @Autowired
    private IGestionMembre businessMembre;
    
    @Autowired
    private IGestionCreateur businessCreateur;

    @Test
    public void testGetMembreById() {
        Membre membre = businessBidon.getMembreById(2);
        Assert.assertNotNull(membre);
        Assert.assertTrue(2 == membre.getId());
    }
    
    @Test
    public void testModifierMembre() {
        Membre membre = businessUtilMisc.getMembreById(2);
        membre.setNom("TestModif");
        membre = businessMembre.modifierMembre(membre);
        Assert.assertNotNull(membre);
        Assert.assertTrue("TestModif" == membre.getNom());
    }
    
    @Test
    public void testModifierMembreSiNull() {
        Membre membre = new Membre();
        membre = businessMembre.modifierMembre(membre);
        Assert.assertNull(membre.getId());
    }
    
    @Test
    public void testAjoutEspaceTuto() {
        EspaceTutoriel espaceTuto = new EspaceTutoriel();
        Assert.assertNull(businessMembre.creerEspaceTutoriel(espaceTuto).getId());
    }
    
    @Test
    public void testCrudEspaceTuto2() {
        EspaceTutoriel espaceTuto = new EspaceTutoriel();
        //test de l'ajout espace tuto avec membre setter
        Membre membre = new Membre();
        membre = businessUtilMisc.getMembreById(5);
        espaceTuto.setMembre(membre);
        espaceTuto = businessMembre.creerEspaceTutoriel(espaceTuto);
        log.debug("id espace tuto :" + espaceTuto.getId());
        Assert.assertNotNull(espaceTuto.getId());
        
        //test de l'ajout espace tuto setter avec membre ayant déjà un espace tuto
        EspaceTutoriel espaceTuto2 = new EspaceTutoriel();
        espaceTuto2.setMembre(membre);
        Assert.assertNull(businessMembre.creerEspaceTutoriel(espaceTuto2).getId());
        
        // test de la modification d'un espace tuto
        espaceTuto.setDescriptif("test");
        espaceTuto = businessCreateur.modifierEspaceTutoriel(espaceTuto);
        Assert.assertTrue(espaceTuto.getDescriptif().equals("test"));
        espaceTuto2.setDescriptif("test");
        espaceTuto2 = businessCreateur.modifierEspaceTutoriel(espaceTuto2);
        Assert.assertNull(espaceTuto2);
        
        // test de la deletion d'un espace tuto (ajout date de cloture)
        espaceTuto = businessCreateur.annulerEspaceTutoriel(espaceTuto);
        Assert.assertNotNull(espaceTuto.getDateCloture());
        espaceTuto2 = new EspaceTutoriel();
        espaceTuto2.setMembre(membre);
        espaceTuto2 = businessMembre.creerEspaceTutoriel(espaceTuto2);
        Assert.assertNotNull(espaceTuto2.getId());
        espaceTuto2 = businessCreateur.annulerEspaceTutoriel(espaceTuto2);
        Assert.assertNotNull(espaceTuto2.getDateCloture());
    }
    
}
