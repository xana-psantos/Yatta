package fr.afcepf.al31.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilBoutique;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Note;

import java.util.List;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestGestionUtilBoutique {

    @Autowired
    private IGestionUtilBoutique testGestionBoutique;
    
    @Autowired
    private IGestionUtilMisc gestionUtilMisc;
    
    @Test
    public void testGetBoutiqueById() {
        Boutique testBoutique = testGestionBoutique.getBoutiqueById(2);
        Assert.assertNotNull(testBoutique);
        Assert.assertTrue(testBoutique.getId() == 2);
    }
    
    @Test
    public void testGetBoutiqueByMembre() {
        Membre membre =  gestionUtilMisc.getMembreById(12);
        Boutique boutique = testGestionBoutique.getBoutiqueByMembre(membre);
        Assert.assertNull(boutique);
        membre = gestionUtilMisc.getMembreById(2);
        boutique = testGestionBoutique.getBoutiqueByMembre(membre);
        Assert.assertTrue(boutique.getId() == 1);
    }
    
    @Test
    public void testGetAllCategoriesByBoutique() {
        Boutique testBoutique = testGestionBoutique.getBoutiqueById(3);
        List<Categorie> testCategories = testGestionBoutique.getAllCategorieBoutiques(testBoutique);
        Assert.assertNotNull(testCategories);
        //Assert.assertTrue(testCategories.size() == 2);
    }
    
    @Test
    public void testGetAllNoteByBoutique() {
        Boutique testBoutique = testGestionBoutique.getBoutiqueById(6); 
        List<Integer> testNotes = testGestionBoutique.getAllNoteByBoutique(testBoutique);
        Assert.assertNotNull(testNotes);
        //Assert.assertTrue(testNotes.size() == 5);      
    }
    
    @Test
    public void testFindAllByBoutiqueOrderByNbreVueDesc() {
        Boutique testBoutique = testGestionBoutique.getBoutiqueById(6);
        List<Article> testArticles = testGestionBoutique.findAllByBoutiqueOrderByNbreVueDesc(testBoutique);
        Assert.assertNotNull(testArticles);
        Assert.assertTrue(testArticles.size() == 12);
        for (int i = 0; i < testArticles.size()-1; i++) {
            Assert.assertTrue(testArticles.get(i).getNbreVue() >= testArticles.get(i+1).getNbreVue());
        } 
    }
    
    @Test
    public void testFindAllByBoutiqueOrderByPrixAsc() {
        Boutique testBoutique = testGestionBoutique.getBoutiqueById(6);
        List<Article> testArticles = testGestionBoutique.findAllByBoutiqueOrderByPrixAsc(testBoutique);
        Assert.assertNotNull(testArticles);
        Assert.assertTrue(testArticles.size() == 12);
        for (int i = 0; i < testArticles.size()-1; i++) {
            Assert.assertTrue(testArticles.get(i).getPrix() <= testArticles.get(i+1).getPrix());
        }      
    }
    
    @Test
    public void testFindAllByBoutiqueOrderByDateAjoutDesc() {
        Boutique testBoutique = testGestionBoutique.getBoutiqueById(6);
        List<Article> testArticles = testGestionBoutique.findAllByBoutiqueOrderByDateAjoutDesc(testBoutique);
        Assert.assertNotNull(testArticles);
        Assert.assertTrue(testArticles.size() == 12);
        for (int i = 0; i < testArticles.size()-1; i++) {
            Assert.assertTrue(testArticles.get(i).getDateAjout().getTime() >= testArticles.get(i+1).getDateAjout().getTime());
        }
    }
    
    /*@Test
    public void testGetVentesByBoutique() {
        Boutique testBoutique = testGestionBoutique.getBoutiqueById(6);
        List<Commande> testCommandes = testGestionBoutique.getVentesByBoutique(testBoutique);
        Assert.assertNotNull(testCommandes);
        Assert.assertTrue(testCommandes.size() == 11);
    }*/
    
    /*@Test
    public void testRechercheArticlesByBoutiqueByCategorie() {
        Boutique testBoutique = testGestionBoutique.getBoutiqueById(3);
        List<Categorie> testCats = testGestionBoutique.getAllCategorieBoutiques(testBoutique);
        System.out.println("************************ NBRE CAT" + testCats.size()); //2
        for (Categorie categorie : testCats) {
            System.out.println(categorie.getId()); // 4 et 2
        }
        Assert.assertNotNull(testCats);
        Assert.assertTrue(testCats.size() == 2);
        Assert.assertTrue(testCats.get(0).getId() == 4);
        Assert.assertTrue(testCats.get(1).getId() == 2);
  
    }*/
}
