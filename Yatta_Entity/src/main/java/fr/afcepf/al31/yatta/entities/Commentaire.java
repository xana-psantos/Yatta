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
@Table(name = "commentaire")
public class Commentaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contenu", nullable = false, length = 512)
    private String contenu;

    @Column(name = "date_emission", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEmission;
    
    @Column(name = "date_derniere_maj", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereMaj;

    @Column(name = "date_suppresion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSuppression;

    /**
     * Association avec la classe {@link Membre}.
     */
    @ManyToOne
    @JoinColumn(name = "id_membre", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_commentaire_membre"))
    private Membre membre;
    
    /**
     * Association avec la classe {@link Produit}.
     */
    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_commentaire_produit"))
    private Produit produit;

    /**
     * Constructeur par défaut.
     */
    public Commentaire() {
        super();
    }

    public Commentaire(Integer paramId, String paramContenu, Date paramDateEmission, 
            Membre paramMembre, Produit paramProduit) {
        super();
        id = paramId;
        contenu = paramContenu;
        dateEmission = paramDateEmission;
        membre = paramMembre;
        produit = paramProduit;
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

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date paramDateEmission) {
        dateEmission = paramDateEmission;
    }

    public Date getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(Date paramDateSuppression) {
        dateSuppression = paramDateSuppression;
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

    public Date getDateDerniereMaj() {
        return dateDerniereMaj;
    }

    public void setDateDerniereMaj(Date paramDateDerniereMaj) {
        dateDerniereMaj = paramDateDerniereMaj;
    }

}