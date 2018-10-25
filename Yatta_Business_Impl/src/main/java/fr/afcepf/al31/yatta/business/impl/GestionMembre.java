package fr.afcepf.al31.yatta.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.IGestionMembre;
import fr.afcepf.al31.yatta.dao.api.IDaoEspaceTutoriel;
import fr.afcepf.al31.yatta.dao.api.IDaoMembre;
import fr.afcepf.al31.yatta.entities.ArticleFavoris;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Message;
import fr.afcepf.al31.yatta.entities.Note;
import fr.afcepf.al31.yatta.entities.TutorielFavoris;

@Service
@Transactional
public class GestionMembre implements IGestionMembre {

    @Autowired
    private IDaoMembre daoMembre;
    
    @Autowired
    private IDaoEspaceTutoriel daoEspaceTuto;
    
    @Override
    public ArticleFavoris ajouterArticleFavoris(ArticleFavoris paramArticleFavoris) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArticleFavoris annulerArticleFavoris(ArticleFavoris paramArticleFavoris) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boutique ajouterBoutique(Boutique paramBoutique) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EspaceTutoriel creerEspaceTutoriel(EspaceTutoriel paramEspace) {
        if (paramEspace.getDateCreation() == null) {
            paramEspace.setDateCreation(new Date());
        }
        if (paramEspace.getMembre() != null) {
            Membre membre= daoMembre.getOne(paramEspace.getMembre().getId());
            List<EspaceTutoriel> espaceTuto = daoEspaceTuto.findByMembre(membre);
            if (espaceTuto == null || espaceTuto.isEmpty()) {
                if (paramEspace.getId() == null) {
                    return daoEspaceTuto.save(paramEspace) ;
                }
            }else {
                boolean tousCloture = true;
                for (EspaceTutoriel espaceTutoriel : espaceTuto) {
                    if (espaceTutoriel.getDateCloture() == null ) {
                        tousCloture = false;
                        return paramEspace;
                    }
                }
                if (tousCloture) {
                    return daoEspaceTuto.save(paramEspace) ;
                }
            }
        }
        return paramEspace;
    }

    @Override
    public Membre modifierMembre(Membre paramMembre) {
        if((paramMembre.getId() != null)||(paramMembre.getNom()!= null)||
            (paramMembre.getPrenom() != null) || (paramMembre.getDateNaissance()!= null)||
            (paramMembre.getMail() != null) || (paramMembre.getMotDePasse()!= null)||
            (paramMembre.getPseudonyme() != null)) {
            return daoMembre.save(paramMembre);
        }
        return paramMembre;
    }

    @Override
    public Message envoyerMessage(Message paramMessage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Message archiverMessage(Message paramMessage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Note ajouterNote(Note paramNote) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Note modifierNote(Note paramNote) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Note annulerNote(Note paramNote) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TutorielFavoris ajouterTutorielFavoris(TutorielFavoris paramTutoFavoris) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TutorielFavoris annulerTutorielFavoris(TutorielFavoris paramTutoFavoris) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Commentaire ajouterCommentaire(Commentaire paramCommentaire) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Commentaire modifierCommentaire(Commentaire paramCommentaire) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Commentaire annulerCommentaire(Commentaire paramCommentaire) {
        // TODO Auto-generated method stub
        return null;
    }

}
