package fr.afcepf.al31.yatta.dao.api;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;

public interface IDaoArticle extends JpaRepository<Article, Integer> {
    List<Article> listerArticlesBoutique(Integer idBoutique);
    
    List<Article> findTop4ByOrderByNbreVueDesc();
    
    List<Article> findAllByOrderByNbreVueDesc();
    
    List<Article> findAllByBoutiqueOrderByNbreVueDesc(Boutique boutique);
    
    List<Article> afficherParBoutiqueOrderByNoteDesc(Boutique boutique);
   
    List<Article> findAllByBoutiqueOrderByPrixAsc(Boutique boutique);
   
    List<Article> findAllByBoutiqueOrderByDateAjoutDesc(Boutique boutique);
    
    List<Article> rechercheByCategorie(Collection<Integer> listeCategories);
    
    List<Article> rechercheByBoutiqueByCategorie(Integer idCategories, Integer idBoutique);
    
    Article listerNotesParArticle(Integer idArticle);
    Article listerCommentairesParArticle(Integer idArticle);
    
}
