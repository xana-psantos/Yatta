package fr.afcepf.al31.yatta.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.IGestionConnexion;
import fr.afcepf.al31.yatta.dao.api.IDaoPersonne;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Personne;
import fr.afcepf.al31.yatta.exception.ExceptionCustom;

@Service
@Transactional
public class GestionConnexion implements IGestionConnexion {
    
    @Autowired
    private IDaoPersonne daoPersonne;
    
    @Override
    public Membre inscrireMembre(Membre paramMembre) throws ExceptionCustom {
        return null;
    }

    @Override
    public boolean verifierMail(String paramMail) throws ExceptionCustom {
        return false;
    }

    @Override
    public boolean verifierPseudo(String paramPseudo) throws ExceptionCustom {
        return false;
    }

    @Override
    public Membre desinscrireMembre(Membre paramMembre) throws ExceptionCustom {
        return null;
    }

    @Override
    public Personne authentification(String paramIdentifiant, String paramMotDePasse) throws ExceptionCustom {
        return daoPersonne.authentification(paramIdentifiant, paramMotDePasse);
    }

}
