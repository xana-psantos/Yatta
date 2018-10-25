package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="moyen_paiement")
public class MoyenPaiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "libelle", nullable = false, length = 20)
    private String libelle;
    
    @Column(name="icone", nullable = false, length = 50)
    private String icone;

    @OneToMany(mappedBy = "moyenPaiement")
    private List<Commande> commandes;

    public MoyenPaiement() {
        super();
    }

    public MoyenPaiement(Integer paramId, String paramLibelle, String paramIcone) {
        super();
        id = paramId;
        libelle = paramLibelle;
        icone = paramIcone;
    }

    
    public String getIcone() {
        return icone;
    }

    public void setIcone(String paramIcone) {
        icone = paramIcone;
    }

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

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> paramCommandes) {
        commandes = paramCommandes;
    }

    

    
}