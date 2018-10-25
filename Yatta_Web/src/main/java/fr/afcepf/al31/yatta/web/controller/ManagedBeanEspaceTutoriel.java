package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilEspaceTuto;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilTuto;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Tutoriel;
import net.bootsfaces.utils.FacesMessages;

//@ManagedBean(name="mbEspaceTuto")
@Component("mbEspaceTuto")
@RequestScope
public class ManagedBeanEspaceTutoriel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private IGestionUtilTuto gestionUtilTuto;
    @Autowired
    private IGestionUtilEspaceTuto gestionUtilEspaceTuto;
    private EspaceTutoriel monEspaceTutoWeshCousin;
    private List<Tutoriel> tutoriels;
    private Tutoriel tutorielLePlusRecent;
    
    @PostConstruct
    public void init() {
        if (monEspaceTutoWeshCousin == null) {
            monEspaceTutoWeshCousin = gestionUtilEspaceTuto.getEspaceTutorielById(6);
        }
            
        
        try {
            tutoriels = gestionUtilTuto.getAllTutorielByEspaceTutorielAndDateRetraitIsNullOrderByDateAjoutDesc(monEspaceTutoWeshCousin);
            tutorielLePlusRecent = tutoriels.get(0);
            tutoriels.remove(0);
            }
            
         catch (Exception e) {
            FacesMessages.warning("Tes tutos sont flingués");
        }
           
      
    }

    public EspaceTutoriel getMonEspaceTutoWeshCousin() {
        return monEspaceTutoWeshCousin;
    }

    public void setMonEspaceTutoWeshCousin(EspaceTutoriel paramMonEspaceTutoWeshCousin) {
        monEspaceTutoWeshCousin = paramMonEspaceTutoWeshCousin;
    }

    public Tutoriel getTutorielLePlusRecent() {
        return tutorielLePlusRecent;
    }

    public void setTutorielLePlusRecent(Tutoriel paramTutorielLePlusRecent) {
        tutorielLePlusRecent = paramTutorielLePlusRecent;
    }

    public IGestionUtilTuto getGestionUtilTuto() {
        return gestionUtilTuto;
    }

    public void setGestionUtilTuto(IGestionUtilTuto paramGestionUtilTuto) {
        gestionUtilTuto = paramGestionUtilTuto;
    }

    public IGestionUtilEspaceTuto getGestionUtilEspaceTuto() {
        return gestionUtilEspaceTuto;
    }

    public void setGestionUtilEspaceTuto(IGestionUtilEspaceTuto paramGestionUtilEspaceTuto) {
        gestionUtilEspaceTuto = paramGestionUtilEspaceTuto;
    }

    public List<Tutoriel> getTutoriels() {
        return tutoriels;
    }

    public void setTutoriels(List<Tutoriel> paramTutoriels) {
        tutoriels = paramTutoriels;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
