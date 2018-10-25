package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tutoriel_favoris")
public class TutorielFavoris implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "libelle", nullable = true, length = 50)
    private String libelle;

    /**
     * Plusieurs tutoriels favoris sont reliés à un {@link Tutoriel}
     */
    @ManyToOne
    @JoinColumn(name = "id_tutoriel", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_tutoriel_favoris_tutoriel"))
    private Tutoriel tutoriel;

    /**
     * Plusieurs tutoriels favoris sont reliés à un {@link Membre}
     */
    @ManyToOne
    @JoinColumn(name = "id_membre", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_tutoriel_favoris_membre"))
    private Membre membre;

    /**
     * Constructeur par défaut
     */
    public TutorielFavoris() {
        super();
    }

    /**
     *Constructeur avec paramètres 
     * @param paramId
     * @param paramLibelle
     * @param paramTutoriel
     * @param paramMembre
     */
    public TutorielFavoris(Integer paramId, String paramLibelle, Tutoriel paramTutoriel, Membre paramMembre) {
        super();
        id = paramId;
        libelle = paramLibelle;
        tutoriel = paramTutoriel;
        membre = paramMembre;
    }

    /**
     * Getters and setters
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer paramId) {
        id = paramId;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String paramLibelle) {
        libelle = paramLibelle;
    }

    public Tutoriel getTutoriel() {
        return tutoriel;
    }

    public void setTutoriel(Tutoriel paramTutoriel) {
        tutoriel = paramTutoriel;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre paramMembre) {
        membre = paramMembre;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    

}