package fr.afcepf.al31.yatta.business.impl.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilBoutique;
import fr.afcepf.al31.yatta.dao.api.IDaoArticle;
import fr.afcepf.al31.yatta.dao.api.IDaoBoutique;
import fr.afcepf.al31.yatta.dao.api.IDaoCategorie;
import fr.afcepf.al31.yatta.dao.api.IDaoCommande;
import fr.afcepf.al31.yatta.dao.api.IDaoNote;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.SuspensionBoutique;
import fr.afcepf.al31.yatta.entities.Tutoriel;
@Service
@Transactional
public class GestionUtilBoutique implements IGestionUtilBoutique {
    @Autowired
    private IDaoBoutique daoBoutique;
    @Autowired
    private IDaoCategorie daoCategorie;
    @Autowired
    private IDaoNote daoNote;
    @Autowired
    private IDaoArticle daoArticle;
    @Autowired
    private IDaoCommande daoCommande;
    
    @Override
    public List<Boutique> getAllBoutique() {
        return daoBoutique.findAll();
    }

    @Override
    public Boutique getBoutiqueById(Integer paramId) {

        return daoBoutique.findOne(paramId);
    }

    @Override
    public List<Boutique> getBoutiqueByName(String paramName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boutique getBoutiqueByMembre(Membre paramMembre) {
        List<Boutique> boutiques = daoBoutique.findByMembre(paramMembre);
        if (!boutiques.isEmpty() ) {
            for (Boutique boutique : boutiques) {
                if (boutique.getDateFermeture() == null) {
                    return boutique;
                }
            }
 
        }
        return null;
    }

    @Override
    public Boutique getBoutiqueByArticle(Article paramArticle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Categorie> getAllCategorieBoutiques(Boutique paramBoutique) {
        
        return daoCategorie.listerCategorieBoutique(paramBoutique.getId());
    }

    @Override
    public List<SuspensionBoutique> getAllSuspensionBoutique(Boutique paramBoutique) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SuspensionBoutique getSuspensionById(Integer paramId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Tutoriel> getAllTutorielByCategorie(Categorie paramCategorie) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Integer> getAllNoteByBoutique(Boutique paramBoutique) {
        return daoNote.listerNotesArticlesBoutique(paramBoutique.getId());
    }

    @Override
    public List<Article> findAllByBoutiqueOrderByNbreVueDesc(Boutique paramBoutique) {

        return daoArticle.findAllByBoutiqueOrderByNbreVueDesc(paramBoutique);
    }

    @Override
    public List<Article> afficherParBoutiqueOrderByNoteDesc(Boutique paramBoutique) {
        List<Article> articlesNotés = daoArticle.findAllByBoutiqueOrderByNbreVueDesc(paramBoutique);
        for (Article article : articlesNotés) {
            article.calculerNoteMoyenne(article);
        } 
        return articlesNotés.stream().sorted(Comparator.comparing(Article::getNoteMoyenne).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Article> findAllByBoutiqueOrderByPrixAsc(Boutique paramBoutique) {
        return daoArticle.findAllByBoutiqueOrderByPrixAsc(paramBoutique);
    }

    @Override
    public List<Article> findAllByBoutiqueOrderByDateAjoutDesc(Boutique paramBoutique) {
        return daoArticle.findAllByBoutiqueOrderByDateAjoutDesc(paramBoutique);
    }

    @Override
    public List<Commande> getVentesByBoutique(Boutique paramBoutique) {
        return daoCommande.getVentesByBoutique(paramBoutique.getId());
    }

    @Override
    public List<Article> rechercheArticlesByBoutiqueByCategorie(Categorie paramCategorie, Boutique paramBoutique) {
        
        return daoArticle.rechercheByBoutiqueByCategorie(paramCategorie.getId(), paramBoutique.getId());
    }

    @Override
    public List<Boutique> rechercherBoutiquesParCategorieArticle(Collection<Integer> paramListeCategories) {
        return daoBoutique.getBoutiqueByCategorie(paramListeCategories);
    }

  



}
