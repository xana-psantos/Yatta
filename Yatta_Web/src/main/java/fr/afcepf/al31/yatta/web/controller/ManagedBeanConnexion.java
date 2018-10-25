package fr.afcepf.al31.yatta.web.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import fr.afcepf.al31.yatta.business.api.IGestionConnexion;
import fr.afcepf.al31.yatta.entities.Membre;

@Component("mbConnexion")
@SessionScope
public class ManagedBeanConnexion implements Serializable {

    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(getClass());
    private String login = "lieve.verheyden@yahoo.com";
    private String password = "liever";
    private Membre newMembre = new Membre();
    
    
    @Autowired
    private ManagedBeanNavigation mbNavig;
    @Autowired
    private ManagedBeanProfil mbProfil;
    
    @Autowired
    private IGestionConnexion gestionConnexion;
    
    public void connect() {
        try {
            mbNavig.setUser(gestionConnexion.authentification(login, password));
            if(mbNavig.getUser().getClass().equals(Membre.class))
            {
                mbProfil.setMembreProfil((Membre)mbNavig.getUser());
                mbProfil.init();
            }
            if (mbNavig.getUser() == null) {
                log.info("Identifiant / Mot de passe erronés.");
            }
        } catch (Exception e) {
            log.fatal("connect() aurais du marcher ! ");
        }
    }
    
    public void deconnexion() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        mbNavig.setUser(null);
        setLogin("juliendiberardino@gmail.com");
        setPassword("juldib");
        mbNavig.goToAccueil();
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String paramLogin) {
        login = paramLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String paramPassword) {
        password = paramPassword;
    }

    public Membre getNewMembre() {
        return newMembre;
    }

    public void setNewMembre(Membre paramNewMembre) {
        newMembre = paramNewMembre;
    }

}
