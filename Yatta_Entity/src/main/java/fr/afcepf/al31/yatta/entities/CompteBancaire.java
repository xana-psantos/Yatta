package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compte_bancaire")
@Inheritance(strategy = InheritanceType.JOINED)
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //length=27 pour iban français, 34 max, voir:
    //https://www.abe-infoservice.fr/banque/compte/code-bic-et-code-iban#1
    @Column(name = "iban", nullable = false, length = 34)
    private String iban;

    //length bic = 8 ou 11, voir: même lien que IBAN
    @Column(name = "bic", nullable = true, length = 11)
    private String bic;

    @Column(name = "nom_titulaire", nullable = false, length = 120)
    private String nomTitulaire;

    @Column(name = "prenom_titulaire", nullable = false, length = 120)
    private String prenomTitulaire;

    @Column(name = "nom_banque", nullable = false, length = 120)
    private String nomBanque;

    //CORRIGE :
    /**
     * Association avec la classe {@link Boutique}.
     */
    @OneToOne
    @JoinColumn(name = "id_boutique") 
    private Boutique boutique;

    /**
     * Constructeur par défaut.
     */
    public CompteBancaire() {
        super();
    }

    /** 
     * Constructeur avec paramètres.
     * @param paramId
     * @param paramIban
     * @param paramBic
     * @param paramNomTitulaire
     * @param paramPrenomTitulaire
     * @param paramNomBanque
     * @param paramBoutique
     */
    public CompteBancaire(Integer paramId, String paramIban, String paramBic,
            String paramNomTitulaire, String paramPrenomTitulaire,
            String paramNomBanque, Boutique paramBoutique) {
        super();
        id = paramId;
        iban = paramIban;
        bic = paramBic;
        nomTitulaire = paramNomTitulaire;
        prenomTitulaire = paramPrenomTitulaire;
        nomBanque = paramNomBanque;
        boutique = paramBoutique;
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

    public String getIban() {
        return iban;
    }

    public void setIban(String paramIban) {
        iban = paramIban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String paramBic) {
        bic = paramBic;
    }

    public String getNomTitulaire() {
        return nomTitulaire;
    }

    public void setNomTitulaire(String paramNomTitulaire) {
        nomTitulaire = paramNomTitulaire;
    }

    public String getPrenomTitulaire() {
        return prenomTitulaire;
    }

    public void setPrenomTitulaire(String paramPrenomTitulaire) {
        prenomTitulaire = paramPrenomTitulaire;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String paramNomBanque) {
        nomBanque = paramNomBanque;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique paramBoutique) {
        boutique = paramBoutique;
    }
    
    
    
    

}