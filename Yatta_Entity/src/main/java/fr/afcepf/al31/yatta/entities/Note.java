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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "note")
@NamedQuery(name="Note.listerNotesArticlesBoutique",
query="SELECT lc.commande.note" + 
        " FROM LigneDeCommande lc" + 
        " WHERE lc.article.boutique.id = ?1 AND lc.commande.note IS NOT NULL")
public class Note implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "note", nullable = false)
    private int note;

    @Column(name = "avis", nullable = true, length = 512)
    private String avis;
    
    @Column(name = "date_publication", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePublication;
    
    @Column(name = "date_derniere_maj", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereMaj;

    @Column(name = "date_suppresion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSuppression;

    /**
     * Une note est posté par un {@link Membre}.
     */
    @ManyToOne
    @JoinColumn(name = "id_membre", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_note_membre"))  
    private Membre membre;
    
    /**
     * Une note concerne un {@link Produit}.
     */
    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_note_produit"))
    private Produit produit;

    
    public Note() {
        super();
    }
    
    /**
     * Constructeur avec Paramètres.
     * @param paramId
     * @param paramNote
     * @param paramAvis
     * @param paramMembre
     * @param paramProduit
     */
    public Note(Integer paramId, int paramNote, String paramAvis, Membre paramMembre, Produit paramProduit, 
                Date paramDatePublication) {
        super();
        id = paramId;
        note = paramNote;
        avis = paramAvis;
        membre = paramMembre;
        produit = paramProduit;
        datePublication = paramDatePublication;
    }


    /**
     * Getters and Setters.
     */    
    public Integer getId() {
        return id;
    }

    public void setId(Integer paramId) {
        id = paramId;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int paramNote) {
        note = paramNote;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String paramAvis) {
        avis = paramAvis;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre paramMembre) {
        membre = paramMembre;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit paramProduit) {
        produit = paramProduit;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date paramDatePublication) {
        datePublication = paramDatePublication;
    }

    public Date getDateDerniereMaj() {
        return dateDerniereMaj;
    }

    public void setDateDerniereMaj(Date paramDateDerniereMaj) {
        dateDerniereMaj = paramDateDerniereMaj;
    }

    public Date getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(Date paramDateSuppression) {
        dateSuppression = paramDateSuppression;
    }
}