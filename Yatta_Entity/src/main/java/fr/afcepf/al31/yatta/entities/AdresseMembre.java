package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

@Entity
@Table(name="adresse_membre")
public class AdresseMembre implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_membre", nullable = false, foreignKey = @ForeignKey(name = "FK_membre_adresse_membre"))
    private Membre membre;

    @ManyToOne
    @JoinColumn(name = "id_adresse", nullable = false, foreignKey = @ForeignKey(name = "FK_adresse_adresse_membre"))
    private Adresse adresse;

    @OneToMany(mappedBy="adresseLivraison")
    private List<Commande> commandeLivraisons;
    
    @OneToMany(mappedBy="adresseFacturation")
    private List<Commande> commandeFacturation;
    
    @OneToMany(mappedBy="adresseExpedition")
    private List<Commande> commandeExpedition;
    
    
    public AdresseMembre() {
        super();
    }

    public AdresseMembre(Integer paramId, Membre paramMembre, Adresse paramAdresse) {
        super();
        id = paramId;
        membre = paramMembre;
        adresse = paramAdresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer paramId) {
        id = paramId;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre paramMembre) {
        membre = paramMembre;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse paramAdresse) {
        adresse = paramAdresse;
    }

    public List<Commande> getCommandeLivraisons() {
        return commandeLivraisons;
    }

    public void setCommandeLivraisons(List<Commande> paramCommandeLivraisons) {
        commandeLivraisons = paramCommandeLivraisons;
    }

    public List<Commande> getCommandeFacturation() {
        return commandeFacturation;
    }

    public void setCommandeFacturation(List<Commande> paramCommandeFacturation) {
        commandeFacturation = paramCommandeFacturation;
    }

    public List<Commande> getCommandeExpedition() {
        return commandeExpedition;
    }

    public void setCommandeExpedition(List<Commande> paramCommandeExpedition) {
        commandeExpedition = paramCommandeExpedition;
    }

    
    

    
}