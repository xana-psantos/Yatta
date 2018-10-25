package fr.afcepf.al31.yatta.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="favoris")
public class Favoris {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * un {@link Membre} suivi par un autre
     */
    @ManyToOne
    @JoinColumn(name="id_membre_suivi", 
                foreignKey= @ForeignKey(name="FK_membre_suivi_favoris"))
    private Membre membreSuivi;
    
    /**
     * un {@link Membre} qui en suit un autre
     */
    @ManyToOne
    @JoinColumn(name="id_membre_suiveur", 
                foreignKey= @ForeignKey(name="FK_membre_suiveur_favoris"))
    private Membre membreSuiveur;

    
    public Favoris() {
        super();
    }


    public Favoris(Integer paramId, Membre paramMembreSuivi, Membre paramMembreSuiveur) {
        super();
        id = paramId;
        membreSuivi = paramMembreSuivi;
        membreSuiveur = paramMembreSuiveur;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer paramId) {
        id = paramId;
    }


    public Membre getMembreSuivi() {
        return membreSuivi;
    }


    public void setMembreSuivi(Membre paramMembreSuivi) {
        membreSuivi = paramMembreSuivi;
    }


    public Membre getMembreSuiveur() {
        return membreSuiveur;
    }


    public void setMembreSuiveur(Membre paramMembreSuiveur) {
        membreSuiveur = paramMembreSuiveur;
    }
}
