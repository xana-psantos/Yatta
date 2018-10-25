package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "personne")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "Personne.authentification", 
            query = "SELECT p FROM Personne p "
                    + "WHERE p.id in "
                    + "(SELECT p1.id FROM Personne p1 WHERE (p1.mail = ?1 AND p1.motDePasse = ?2)) "
                    + "or p.id in "
                    + "(SELECT m.id FROM Membre m WHERE (m.pseudonyme = ?1 AND m.motDePasse = ?2))")

public abstract class Personne implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "mail", nullable = false, length = 150)
    private String mail;

    @Column(name = "mot_de_passe", nullable = false, length = 50)
    private String motDePasse;

    @Column(name = "photo", nullable = true, length = 255)
    private String photo;

    /**
     * Une personne peut envoyer plusieurs {@link Message}
     */
    @OneToMany(mappedBy = "emetteur")
    private List<Message> messagesEnvoies;

    /**
     * Une personne peut recevoir plusieurs {@link Message}
     */
    @OneToMany(mappedBy = "destinataire")
    private List<Message> messagesRecus;

    public Personne() {

    }

    public Personne(Integer paramId, String paramNom, String paramPrenom, String paramMail, String paramMotDePasse,
            String paramPhoto) {
        id = paramId;
        nom = paramNom;
        prenom = paramPrenom;
        mail = paramMail;
        motDePasse = paramMotDePasse;
        photo = paramPhoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer paramId) {
        id = paramId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String paramNom) {
        nom = paramNom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String paramPrenom) {
        prenom = paramPrenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String paramMail) {
        mail = paramMail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String paramMotDePasse) {
        motDePasse = paramMotDePasse;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String paramPhoto) {
        photo = paramPhoto;
    }
}