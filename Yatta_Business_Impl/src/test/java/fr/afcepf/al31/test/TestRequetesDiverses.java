package fr.afcepf.al31.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al31.yatta.dao.api.IDaoBoutique;
import fr.afcepf.al31.yatta.dao.api.IDaoCommande;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Commande;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestRequetesDiverses {

    @Autowired
    private IDaoBoutique daoBoutique;
    
    @Autowired
    private IDaoCommande daoCommande;
    
    @Test
    public void TestGetBoutiqueByCommande() {
        Commande c = daoCommande.findOne(1);
        Boutique b = daoBoutique.getBoutiqueParCommande(c.getId());
        Assert.assertTrue(b.getId().equals(6));
    }
}
