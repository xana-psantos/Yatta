package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilArticle;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.Note;

@Component("mbArticle")
//@ManagedBean(name="mbArticle")
@SessionScope
public class ManagedBeanArticle implements Serializable {

    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private IGestionUtilArticle gestionUtilArticle;

    private Article article;
    private Article articleAvecNotes;
    private Article articleAvecCommentaires;
    private List<Note> notes;
    private double noteMoyenne;
    private List<Commentaire> commentaires;
    private Commentaire commentaire;
    private int activeIndex = 0;

    @PostConstruct
    public void init(){
        try {
            if(article == null) {
                article = gestionUtilArticle.getArticleById(12);
            }
            articleAvecNotes = gestionUtilArticle.rechercherArticleAvecNotes(article);
            notes = articleAvecNotes.getNotes();
            noteMoyenne = calculerNoteMoyenneArticle(articleAvecNotes);
            articleAvecCommentaires = gestionUtilArticle.rechercherArticleAvecCommentaires(article);
            commentaires = articleAvecCommentaires.getCommentaires();

        } catch (Exception e) {

            log.fatal("l'init() rencontre un obstacle de betises!!!! Il y a FORCEMENT une erreur, dixit Séb");
        } 
    }
    
    public double calculerNoteMoyenneArticle(Article article) {
        double noteMBout = 0.0;
        if ( notes.size() != 0 ) {
            int somme=0; 
            for (Note note : notes) {
                somme+= note.getNote();
            }
            noteMBout = ((double)(somme)) / ((double)notes.size());
        }else {
            noteMBout= 0.0;
        }
        return noteMBout;
    }

    //GETTERS SETTERS
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article paramArticle) {
        article = paramArticle;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int paramActiveIndex) {
        activeIndex = paramActiveIndex;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> paramNotes) {
        notes = paramNotes;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> paramCommentaires) {
        commentaires = paramCommentaires;
    }

    public Article getArticleAvecNotes() {
        return articleAvecNotes;
    }

    public void setArticleAvecNotes(Article paramArticleAvecNotes) {
        articleAvecNotes = paramArticleAvecNotes;
    }

    public Article getArticleAvecCommentaires() {
        return articleAvecCommentaires;
    }

    public void setArticleAvecCommentaires(Article paramArticleAvecCommentaires) {
        articleAvecCommentaires = paramArticleAvecCommentaires;
    }

    public double getNoteMoyenne() {
        return noteMoyenne;
    }

    public void setNoteMoyenne(double paramNoteMoyenne) {
        noteMoyenne = paramNoteMoyenne;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire paramCommentaire) {
        commentaire = paramCommentaire;
    }

    
    
}
