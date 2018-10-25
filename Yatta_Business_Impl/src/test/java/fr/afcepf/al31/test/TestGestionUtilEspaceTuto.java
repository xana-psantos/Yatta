package fr.afcepf.al31.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilEspaceTuto;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestGestionUtilEspaceTuto {
    
    @Autowired
    private IGestionUtilEspaceTuto gestionUtilEspaceTuto;
    
    @Autowired
    private IGestionUtilMisc gestionUtilMisc;
    
    @Test
    public void testGetEspaceTutoByMembre() {
        Membre membre =  gestionUtilMisc.getMembreById(12);
        EspaceTutoriel espaceTuto = gestionUtilEspaceTuto.getEspaceTutoByMembre(membre);
        Assert.assertNull(espaceTuto);
        membre = gestionUtilMisc.getMembreById(2);
        espaceTuto = gestionUtilEspaceTuto.getEspaceTutoByMembre(membre);
        Assert.assertTrue(espaceTuto.getId() == 1);
    }
}
