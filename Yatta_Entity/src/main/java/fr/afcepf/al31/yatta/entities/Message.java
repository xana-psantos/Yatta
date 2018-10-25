package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="message")
public class Message implements Serializable {

   private static final long serialVersionUID = 1L;
   
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Integer id;
   
   @Column (name="contenu", nullable=false, length=255)
   private String contenu;
   
   @Column (name="objet", nullable=false, length=255)
   private String objet;
   
   @Column (name="date_envoi", nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dateEnvoi;
   
   @Column (name="date_archivage", nullable=true)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dateArchivage;
   
   
   /**
    *association vers {@link Membre} en tant qu'émetteur.
    */
   @ManyToOne
   @JoinColumn(name = "id_personne_emetteur", nullable = false,
   foreignKey = @ForeignKey(name="FK_Emetteur_Message"))
   private Personne emetteur;
   
   /**
    *association vers {@link Membre} en tant que destinataire.
    */
   @ManyToOne
   @JoinColumn(name = "id_personne_destinataire", nullable = false,
   foreignKey = @ForeignKey(name="FK_Destinataire_Message"))
   private Personne destinataire;

   
    public Message() {
        
    }


    public Message(Integer paramId, String paramContenu, String paramObjet, Date paramDateEnvoi,
            Personne paramEmetteur, Personne paramDestinataire) {
        super();
        id = paramId;
        contenu = paramContenu;
        objet = paramObjet;
        dateEnvoi = paramDateEnvoi;
        emetteur = paramEmetteur;
        destinataire = paramDestinataire;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer paramId) {
        id = paramId;
    }


    public String getContenu() {
        return contenu;
    }


    public void setContenu(String paramContenu) {
        contenu = paramContenu;
    }


    public String getObjet() {
        return objet;
    }


    public void setObjet(String paramObjet) {
        objet = paramObjet;
    }


    public Date getDateEnvoi() {
        return dateEnvoi;
    }


    public void setDateEnvoi(Date paramDateEnvoi) {
        dateEnvoi = paramDateEnvoi;
    }


    public Date getDateArchivage() {
        return dateArchivage;
    }


    public void setDateArchivage(Date paramDateArchivage) {
        dateArchivage = paramDateArchivage;
    }


    public Personne getEmetteur() {
        return emetteur;
    }


    public void setEmetteur(Personne paramEmetteur) {
        emetteur = paramEmetteur;
    }


    public Personne getDestinataire() {
        return destinataire;
    }


    public void setDestinataire(Personne paramDestinataire) {
        destinataire = paramDestinataire;
    }   
}
