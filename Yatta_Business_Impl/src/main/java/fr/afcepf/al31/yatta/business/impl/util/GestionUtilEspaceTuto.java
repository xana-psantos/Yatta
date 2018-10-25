package fr.afcepf.al31.yatta.business.impl.util;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilEspaceTuto;
import fr.afcepf.al31.yatta.dao.api.IDaoEspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.SuspensionEspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Tutoriel;

@Service
@Transactional
public class GestionUtilEspaceTuto implements IGestionUtilEspaceTuto{

    @Autowired
    private IDaoEspaceTutoriel daoEspaceTutoriel;
 
    @Override
    public EspaceTutoriel getEspaceTutorielById(Integer paramId) {
        
        return daoEspaceTutoriel.findById(paramId);
    }

    @Override
    public List<Categorie> getAllCategoriesEspaceTuto(EspaceTutoriel paramEspaceTuto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EspaceTutoriel getEspaceTutoByTuto(Tutoriel paramTutoriel) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EspaceTutoriel getEspaceTutoByMembre(Membre paramMembre) {
        List<EspaceTutoriel> espacesTutos = daoEspaceTutoriel.findByMembre(paramMembre);
        if(!espacesTutos.isEmpty()) {
            for (EspaceTutoriel espaceTutoriel : espacesTutos) {
                if (espaceTutoriel.getDateCloture() == null) {
                    return espaceTutoriel;
                }
            }
        }
        return null;
    }

    @Override
    public List<SuspensionEspaceTutoriel> getAllSuspensionEspaceTuto(Boutique paramBoutique) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SuspensionEspaceTutoriel getSuspensionEspaceTutoById(Integer paramId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EspaceTutoriel> getAllEspaceTutoriel() {
        // TODO Auto-generated method stub
        return daoEspaceTutoriel.findAll();
    }

    @Override
    public List<EspaceTutoriel> rechercherEspaceTutorielParCategorieTutoriel(Collection<Integer> paramListeCategories) {
        return daoEspaceTutoriel.getEspaceTutorielByCategorie(paramListeCategories);
    }

   

}
