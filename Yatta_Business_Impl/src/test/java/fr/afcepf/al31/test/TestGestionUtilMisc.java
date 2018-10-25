package fr.afcepf.al31.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.entities.Categorie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestGestionUtilMisc {

    @Autowired
    private IGestionUtilMisc gestionUtilMisc;
    
    @Test
    public void testGetAllCategories() {
        try {
            List<Categorie> categories = gestionUtilMisc.getAllCategories();
            Assert.assertNotNull(categories);
            Assert.assertTrue(categories.size() > 0);
            Assert.assertTrue(categories.get(5).getLibelle().equals("Vintage"));
        } catch (Exception e){
            Assert.fail("Ça aurait du marcher");
            e.printStackTrace();
        }
        
    }
}
