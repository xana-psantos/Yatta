package fr.afcepf.al31.yatta.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.IGestionCreateur;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilEspaceTuto;
import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilTuto;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Tutoriel;

@Component("mbGestionEspaceTuto")
//@ManagedBean(name="mbGestionEspaceTuto")
@SessionScope
public class ManagedBeanGestionEspaceTuto {
    
    private Logger log = Logger.getLogger(getClass());
    
    @Autowired
    private ManagedBeanNavigation mbNavigation;
    
    @Autowired
    private IGestionUtilTuto gestionUtilTuto;
    
    @Autowired
    private IGestionUtilEspaceTuto gestionUtilEspaceTuto;
    
    @Autowired
    private IGestionCreateur gestionCreateur;
    private EspaceTutoriel espaceTutoriel = null;
    List<Tutoriel> tutoriels;
    
    private Part fileUpload;
    private String fileContent;
    
    @PostConstruct
    public void init(){
        try {
            if(espaceTutoriel == null) {
                espaceTutoriel = gestionUtilEspaceTuto.getEspaceTutorielById(6);
            }
            if(mbNavigation.getUser() != null) {
                espaceTutoriel = gestionUtilEspaceTuto.getEspaceTutoByMembre((Membre)mbNavigation.getUser());
            }
            tutoriels = gestionUtilTuto.getAllTutorielByEspaceTutorielAndDateRetraitIsNull(espaceTutoriel);
            
        } catch (Exception e) {
            e.printStackTrace();
            log.fatal("l'init() rencontre un obstacle de betises!!!! Il y a FORCEMENT une erreur, dixit Séb");
        } 
    }
    
    private void upload() {
        try {
          InputStream in = fileUpload.getInputStream();
          File file = new File("C:/Users/Formation/Desktop/upload/"+fileUpload.getSubmittedFileName());
          file.createNewFile();
          FileOutputStream fos = new FileOutputStream(file);
          byte[] buffer = new byte[1024];
          int lenght;
          
          while ((lenght = in.read(buffer))>0) {
            fos.write(buffer, 0, lenght);
        }
          fos.close();
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    public void modifierEspaceTuto() {
        upload();
        espaceTutoriel.setImage("C:/Users/Formation/Desktop/upload/"+fileUpload.getSubmittedFileName());
        espaceTutoriel = gestionCreateur.modifierEspaceTutoriel(espaceTutoriel);    
    }
    public EspaceTutoriel getEspaceTutoriel() {
        return espaceTutoriel;
    }

    public void setEspaceTutoriel(EspaceTutoriel paramEspaceTutoriel) {
        espaceTutoriel = paramEspaceTutoriel;
    }

    public List<Tutoriel> getTutoriels() {
        return tutoriels;
    }

    public void setTutoriels(List<Tutoriel> paramTutoriels) {
        tutoriels = paramTutoriels;
    }

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part paramFileUpload) {
        fileUpload = paramFileUpload;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String paramFileContent) {
        fileContent = paramFileContent;
    }
    

}
