package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ForeignKey;

@Entity
@NamedQueries({    
            @NamedQuery(name="Commande.getCommandeRecus", 
            query="Select distinct l.commande from LigneDeCommande l where l.article.boutique.membre = ?1 and l.commande.dateAnnulation is null"),
            @NamedQuery(name="Commande.getVentesByBoutique", 
            query="Select distinct l.commande from LigneDeCommande l where l.article.boutique.id = ?1 and l.commande.dateAnnulation is null"),
            @NamedQuery(name="Commande.getCommandePanier", 
            query="SELECT c from Commande c "
                + "LEFT JOIN FETCH c.lignesDeCommande "
                + "WHERE c.acheteur.id = ?1 and c.dateAnnulation is null and c.dateValidation is null"),
            @NamedQuery(name="Commande.afficherCommandeById", 
                        query="SELECT c from Commande c "
                               + "LEFT JOIN FETCH c.lignesDeCommande "
                                + "WHERE c.id = ?1"),
            @NamedQuery(name="Commande.findParAcheteur",
            query="SELECT distinct l.commande from LigneDeCommande l "
                    + "WHERE l.commande.acheteur.id = ?1 and l.commande.dateAnnulation is null")
})
@Table(name = "commande")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="date_livraison", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLivraison;
    
    @Column(name="date_creation", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name="date_validation", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidation;
    
    @Column(name="date_annulation", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnnulation;

    @Column(name="note", nullable = true)
    private Integer note;

    @Column(name="avis", nullable = true, length = 300)
    private String avis;

    @OneToMany(mappedBy = "commande")
    private List<LigneDeCommande> lignesDeCommande;

    @ManyToOne
    @JoinColumn(name="id_moyen_paiement", nullable=true, foreignKey = @ForeignKey(name="FK_moyen_paiement_commande"))
    private MoyenPaiement moyenPaiement;

    @ManyToOne
    @JoinColumn(name="id_membre",nullable=true, foreignKey = @ForeignKey(name="FK_membre_commande"))
    private Membre acheteur;

    @ManyToOne
    @JoinColumn(name="id_adresse_membre_expedition", nullable=true, foreignKey = @ForeignKey(name="FK_adresse_expedition_commande"))
    private AdresseMembre adresseExpedition;

    @ManyToOne
    @JoinColumn(name="id_adresse_membre_livraison", nullable=true, foreignKey = @ForeignKey(name="FK_adresse_livraison_commande"))
    private AdresseMembre adresseLivraison;
    
    @ManyToOne
    @JoinColumn(name="id_adresse_membre_facturation", nullable=true, foreignKey = @ForeignKey(name="FK_adresse_facturation_commande"))
    private AdresseMembre adresseFacturation;

    public Commande() {
        super();
    }

    public Commande(Integer paramId, Date paramDateLivraison, Date paramDateCreation, Date paramDateValidation,
            Integer paramNote, String paramAvis,
            MoyenPaiement paramMoyenPaiement, Membre paramAcheteur, AdresseMembre paramAdresseExpedition,
            AdresseMembre paramAdresseLivraison, AdresseMembre paramAdresseFacturation) {
        super();
        id = paramId;
        dateLivraison = paramDateLivraison;
        dateCreation = paramDateCreation;
        dateValidation = paramDateValidation;
        note = paramNote;
        avis = paramAvis;
        moyenPaiement = paramMoyenPaiement;
        acheteur = paramAcheteur;
        adresseExpedition = paramAdresseExpedition;
        adresseLivraison = paramAdresseLivraison;
        adresseFacturation = paramAdresseFacturation;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer paramId) {
        id = paramId;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date paramDateLivraison) {
        dateLivraison = paramDateLivraison;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date paramDateValidation) {
        dateValidation = paramDateValidation;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer paramNote) {
        note = paramNote;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String paramAvis) {
        avis = paramAvis;
    }

    public List<LigneDeCommande> getLignesDeCommande() {
        return lignesDeCommande;
    }

    public void setLignesDeCommande(List<LigneDeCommande> paramLignesDeCommande) {
        lignesDeCommande = paramLignesDeCommande;
    }

    public MoyenPaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(MoyenPaiement paramMoyenPaiement) {
        moyenPaiement = paramMoyenPaiement;
    }

    public Membre getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Membre paramAcheteur) {
        acheteur = paramAcheteur;
    }

    public AdresseMembre getAdresseExpedition() {
        return adresseExpedition;
    }

    public void setAdresseExpedition(AdresseMembre paramAdresseExpedition) {
        adresseExpedition = paramAdresseExpedition;
    }

    public AdresseMembre getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(AdresseMembre paramAdresseLivraison) {
        adresseLivraison = paramAdresseLivraison;
    }

    public AdresseMembre getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(AdresseMembre paramAdresseFacturation) {
        adresseFacturation = paramAdresseFacturation;
    }



    public Date getDateCreation() {
        return dateCreation;
    }



    public void setDateCreation(Date paramDateCreation) {
        dateCreation = paramDateCreation;
    }

 
}