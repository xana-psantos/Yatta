package fr.afcepf.al31.yatta.business.impl.util;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilArticle;
import fr.afcepf.al31.yatta.dao.api.IDaoArticle;
import fr.afcepf.al31.yatta.dao.api.IDaoArticleFavoris;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.ArticleFavoris;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.Membre;

@Service
@Transactional
public class GestionUtilArticle implements IGestionUtilArticle {

    @Autowired
    private IDaoArticle daoArticle;
    @Autowired
    private IDaoArticleFavoris daoArticleFavoris;

    @Override
    public List<Article> getAllArticle() {

        return daoArticle.findAll();
    }

    @Override
    public List<Article> getArticleByName(String paramName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Article getArticleById(Integer paramId) {

        return daoArticle.findOne(paramId);
    }

    @Override
    public List<Article> getArticlesBoutique(Boutique paramBoutique) {

        return daoArticle.listerArticlesBoutique(paramBoutique.getId());
    }

    @Override
    public List<Article> getArticlesCategorie(Categorie paramCategorie) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ArticleFavoris> getArticlesFavoris(Membre paramMembre) {
        return daoArticleFavoris.findByMembre(paramMembre);
    }

    @Override
    public List<Article> getArticlesCommandes(Commande paramCommande) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double getNoteMoyenneArticle(Article paramArticle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Commentaire> getAllCommentaireArticle(Article paramArticle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Article> getAllArticleOrderByNbreVue() {
        return daoArticle.findAllByOrderByNbreVueDesc();
    }

    @Override
    public List<Article> getTop4ArticleOrderByNbreVue() {
        return daoArticle.findTop4ByOrderByNbreVueDesc();
    }

    @Override
    public List<Article> getArticleByCategorie(Collection<Integer> paramListeCategorie) {
        return daoArticle.rechercheByCategorie(paramListeCategorie);
    }

    @Override
    public Article rechercherArticleAvecNotes(Article paramArticle) {
        return daoArticle.listerNotesParArticle(paramArticle.getId());
    }

    @Override
    public Article rechercherArticleAvecCommentaires(Article paramArticle) {
        return daoArticle.listerCommentairesParArticle(paramArticle.getId());
    }

}
