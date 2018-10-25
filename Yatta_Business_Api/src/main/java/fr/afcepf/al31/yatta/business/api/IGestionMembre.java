package fr.afcepf.al31.yatta.business.api;

import fr.afcepf.al31.yatta.entities.ArticleFavoris;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Message;
import fr.afcepf.al31.yatta.entities.Note;
import fr.afcepf.al31.yatta.entities.TutorielFavoris;

public interface IGestionMembre {

    ArticleFavoris ajouterArticleFavoris(ArticleFavoris articleFavoris);

    ArticleFavoris annulerArticleFavoris(ArticleFavoris articleFavoris);
    
    Boutique ajouterBoutique(Boutique boutique);
    
    EspaceTutoriel creerEspaceTutoriel(EspaceTutoriel espace);
    
    Membre modifierMembre(Membre membre);
    
    Message envoyerMessage(Message message);

    Message archiverMessage(Message message);
    
    Note ajouterNote(Note note);

    Note modifierNote(Note note);

    Note annulerNote(Note note);
    
    TutorielFavoris ajouterTutorielFavoris(TutorielFavoris tutoFavoris);

    TutorielFavoris annulerTutorielFavoris(TutorielFavoris tutoFavoris);
    
    Commentaire ajouterCommentaire(Commentaire commentaire);

    Commentaire modifierCommentaire(Commentaire commentaire);

    Commentaire annulerCommentaire(Commentaire commentaire);
}
