package fr.afcepf.al31.yatta.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.IGestionVendeur;
import fr.afcepf.al31.yatta.dao.api.IDaoArticle;
import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.CompteBancaire;
import fr.afcepf.al31.yatta.entities.SuspensionBoutique;
@Service
@Transactional
public class GestionVendeur implements IGestionVendeur {
    @Autowired
    private IDaoArticle daoArticle;
    @Override
    public Article ajouterArticle(Article paramArticle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Article modifierArticle(Article paramArticle) {
        return daoArticle.save(paramArticle);
    }

    @Override
    public Article retirerArticle(Article paramArticle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boutique modifierBoutique(Boutique paramBoutique) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boutique fermerBoutique(Boutique paramBoutique) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompteBancaire ajouterCompteBancaire(CompteBancaire paramCompte) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CompteBancaire modifierCompteBancaire(CompteBancaire paramCompte) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SuspensionBoutique modifierSuspension(SuspensionBoutique paramSuspension) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SuspensionBoutique suspendreBoutique(SuspensionBoutique paramSuspension) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SuspensionBoutique annulerSuspension(SuspensionBoutique paramSuspension) {
        // TODO Auto-generated method stub
        return null;
    }

}
