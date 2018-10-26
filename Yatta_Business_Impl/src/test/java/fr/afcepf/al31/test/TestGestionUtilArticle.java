package fr.afcepf.al31.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilArticle;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilBoutique;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-business.xml"})
public class TestGestionUtilArticle {
    private static Logger log = Logger.getLogger(TestGestionUtilArticle.class);
    @Autowired
    private IGestionUtilArticle testGestionArticle;
    @Autowired
    private IGestionUtilBoutique testGestionBoutique;
    
   /* @Test
    public void testGestionUtilArticle () {
        Boutique testBoutique = testGestionBoutique.getBoutiqueById(2);
        List<Article> testArticles = testGestionArticle.getArticlesBoutique(testBoutique);
        Assert.assertNotNull(testArticles);
        Assert.assertTrue(testArticles.size() == 2);
    }*/
    
    @Test
    public void testafficherParBoutiqueOrderByNoteDesc() {
        Boutique boutique = testGestionBoutique.getBoutiqueById(6);
        Assert.assertNotNull(boutique);
        List<Article> articles = testGestionBoutique.afficherParBoutiqueOrderByNoteDesc(boutique);
        log.debug("taille liste :" + articles.size());
        for (Article article : articles) {
            //Assert.assertTrue(article.getBoutique().getId() == 6);
            log.debug("id: "+article.getId() + ", noteMoyenne: " + article.getNoteMoyenne());
        }
    }
    
    @Test
    public void testRechercherArticleAvecNotes() {
        Article testArticle = testGestionArticle.rechercherArticleAvecNotes(testGestionArticle.getArticleById(12));
        List<Note> testNotes = testArticle.getNotes();
        System.out.println("id article:" +testArticle.getId()); //12
        System.out.println("nb notes:" +testNotes.size()); //2      
        for (Note note : testNotes) {
            System.out.println("note:" +note.getNote()); //5 et 4
        }
        Assert.assertNotNull(testArticle);
        Assert.assertTrue(testArticle.getId() == 12);
        Assert.assertNotNull(testNotes);
        Assert.assertTrue(testNotes.size() == 2);
        
        Assert.assertTrue(testNotes.get(0).getNote() == 5);
        Assert.assertTrue(testNotes.get(1).getNote() == 4);     
    }
    
    @Test
    public void testRechercherArticleAvecCommentaires() {
        Article testArticle = testGestionArticle.rechercherArticleAvecCommentaires(testGestionArticle.getArticleById(12));
        List<Commentaire> testComments = testArticle.getCommentaires();

        System.out.println("id article:" +testArticle.getId()); //12
        System.out.println("nb comm:" +testComments.size()); //2  
        for (Commentaire commentaire : testComments) {
            System.out.println("comm:" +commentaire.getId()); //31 et 32
        } 
        Assert.assertNotNull(testArticle);
        Assert.assertTrue(testArticle.getId() == 12);
        Assert.assertNotNull(testComments);
        Assert.assertTrue(testComments.size() == 2);
        
        Assert.assertTrue(testComments.get(0).getId() == 31);
        Assert.assertTrue(testComments.get(1).getId() == 32);
    }
}
