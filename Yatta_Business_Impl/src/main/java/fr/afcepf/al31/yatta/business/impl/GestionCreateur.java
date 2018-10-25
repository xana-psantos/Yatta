package fr.afcepf.al31.yatta.business.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.IGestionCreateur;
import fr.afcepf.al31.yatta.dao.api.IDaoEspaceTutoriel;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.SuspensionEspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Tutoriel;

@Service
@Transactional
public class GestionCreateur implements IGestionCreateur {
    
    @Autowired
    private IDaoEspaceTutoriel daoEspaceTuto;
    
    
    @Override
    public EspaceTutoriel modifierEspaceTutoriel(EspaceTutoriel paramEspace) {
        if(paramEspace.getId() != null && paramEspace.getMembre() != null) {
            return daoEspaceTuto.save(paramEspace);
        }
        return null;
    }

    @Override
    public EspaceTutoriel annulerEspaceTutoriel(EspaceTutoriel paramEspace) {
        if(paramEspace.getId() != null && paramEspace.getMembre() != null) {
            paramEspace.setDateCloture(new Date());
            return modifierEspaceTutoriel(paramEspace);
        }
        return null;
    }


    @Override
    public Tutoriel ajouterTutoriel(Tutoriel paramTutoriel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Tutoriel modifierTutoriel(Tutoriel paramTutoriel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Tutoriel annulerTutoriel(Tutoriel paramTutoriel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SuspensionEspaceTutoriel modifierSuspensionEspaceTuto(SuspensionEspaceTutoriel paramSuspension) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SuspensionEspaceTutoriel ajouterSuspensionEspaceTuto(SuspensionEspaceTutoriel paramSuspension) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SuspensionEspaceTutoriel annulerSuspensionEspaceTuto(SuspensionEspaceTutoriel paramSuspension) {
        // TODO Auto-generated method stub
        return null;
    }



}
