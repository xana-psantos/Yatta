package fr.afcepf.al31.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilTuto;
import fr.afcepf.al31.yatta.entities.Tutoriel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestGestionUtilTuto {

    @Autowired
    private IGestionUtilTuto businessTuto;

    @Test
    public void testGetAllTutos () {
        List<Tutoriel> tutoriels = businessTuto.getAllTutoriel();

        Assert.assertNotNull(tutoriels);
        Assert.assertTrue(tutoriels.size() ==31);
        Assert.assertNotNull(tutoriels.get(15).getEspaceTutoriel().getMembre());
    }
    
    @Test
    public void testGetTutosByCategorieEmpty() {
        
        Collection<Integer> ints = new ArrayList<Integer>();
        ints.add(20);
  
        List<Tutoriel> tutoriels = businessTuto.getTutorielByCategorie(ints);
        
        Assert.assertNotNull(tutoriels);
      
    }
    
    @Test
    public void testGetTutosByCategorieFull() {
        Collection<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        
        List<Tutoriel> tutoriels = businessTuto.getTutorielByCategorie(ints);
        
        Assert.assertTrue(tutoriels.size()>0);
      
    }
    
}
