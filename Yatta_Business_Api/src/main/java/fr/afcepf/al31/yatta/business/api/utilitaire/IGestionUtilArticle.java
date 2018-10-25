package fr.afcepf.al31.yatta.business.api.utilitaire;

import java.util.Collection;
import java.util.List;

import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.ArticleFavoris;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Tutoriel;

public interface IGestionUtilArticle {
    
    List<Article> getAllArticle();
    
    List<Article> getArticleByName(String name);

    Article getArticleById(Integer id);
    
    List<Article> getArticlesBoutique(Boutique boutique);

    List<Article> getArticlesCategorie(Categorie categorie);
    
    List<Article> getAllArticleOrderByNbreVue();
    
    List<Article> getTop4ArticleOrderByNbreVue();

    List<ArticleFavoris> getArticlesFavoris(Membre membre);
    
    List<Article> getArticleByCategorie(Collection<Integer> listeCategorie);

    List<Article> getArticlesCommandes(Commande commande);
    
    Double getNoteMoyenneArticle(Article article);
    
    List<Commentaire> getAllCommentaireArticle(Article article);
    
    Article rechercherArticleAvecNotes(Article article);
    Article rechercherArticleAvecCommentaires(Article article);
    
   
    
}
