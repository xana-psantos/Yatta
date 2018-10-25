package fr.afcepf.al31.yatta.business.impl.util;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilTuto;
import fr.afcepf.al31.yatta.dao.api.IDaoTutoriel;
import fr.afcepf.al31.yatta.dao.api.IDaoTutorielFavoris;
import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Tutoriel;
import fr.afcepf.al31.yatta.entities.TutorielFavoris;

@Service
@Transactional
public class GestionUtilTuto implements IGestionUtilTuto{

    @Autowired
    private IDaoTutoriel daoTutoriel;

    @Autowired
    private IDaoTutorielFavoris daoTutorielFavoris;
    
    @Override
    public List<Commentaire> getAllCommentaireTuto(Tutoriel paramTutoriel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EspaceTutoriel> getEspaceTutorielByName(String paramName) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<Tutoriel> getAllTutoriel() {
        return daoTutoriel.findAll();

    }

    @Override
    public Tutoriel getTutorielById(Integer paramId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Tutoriel> getTutorielByName(String paramName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double noteMoyenneTutoriel(Tutoriel paramTutoriel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TutorielFavoris> getTutorielFavoris(Membre paramMembre) {
        return daoTutorielFavoris.findByMembre(paramMembre);
    }

    @Override
    public List<Tutoriel> getAllTutorielOrderByNbreVue() {
        return daoTutoriel.findAllByOrderByNbreVueDesc();
    }

    @Override
    public List<Tutoriel> getTop4TutorielOrderByNbreVue() {
        return daoTutoriel.findTop4ByOrderByNbreVueDesc();
    }



    @Override
    public List<Tutoriel> getAllTutorielByEspaceTutorielAndDateRetraitIsNull(EspaceTutoriel paramEspace) {

        return daoTutoriel.findAllByEspaceTutorielAndDateRetraitIsNull(paramEspace);
    }

    @Override
    public List<Tutoriel> getAllTutorielByEspaceTutorielAndDateRetraitIsNullOrderByDateAjoutDesc(EspaceTutoriel paramEspace) {

        return daoTutoriel.findAllByEspaceTutorielAndDateRetraitIsNullOrderByDateAjoutDesc(paramEspace);
    }

    @Override
    public List<Tutoriel> getTutorielByCategorie(Collection<Integer> paramListeCategorie) {
        
        return daoTutoriel.rechercheByCategorie(paramListeCategorie);
    }


}
