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
@Table(name="adresse")
public class Adresse implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "id_api", nullable = false, unique = true)
    private String idApi;

    @Column(name = "numero_rue", nullable = true, length = 4)
    private String numeroRue;

    @Column(name = "libelle_rue", nullable = false, length = 50)
    private String libelleRue;

    @Column(name = "libelle_ville", nullable = false, length = 50)
    private String libelleVille;

    @Column(name = "code_postal", nullable = false, length = 5)
    private String codePostal;

    @Column(name = "pays", nullable = false, length = 50)
    private String pays;

    @OneToMany(mappedBy = "adresse")
    private List<AdresseMembre> adressesMembre;

    public Adresse() {
        super();
    }

    public Adresse(Integer paramId, String paramNumeroRue, String paramLibelleRue, String paramLibelleVille,
            String paramCodePostal, String paramPays) {
        super();
        id = paramId;
        numeroRue = paramNumeroRue;
        libelleRue = paramLibelleRue;
        libelleVille = paramLibelleVille;
        codePostal = paramCodePostal;
        pays = paramPays;
    }

    public String getIdApi() {
        return idApi;
    }

    public void setIdApi(String paramIdApi) {
        idApi = paramIdApi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer paramId) {
        id = paramId;
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(String paramNumeroRue) {
        numeroRue = paramNumeroRue;
    }

    public String getLibelleRue() {
        return libelleRue;
    }

    public void setLibelleRue(String paramLibelleRue) {
        libelleRue = paramLibelleRue;
    }

    public String getLibelleVille() {
        return libelleVille;
    }

    public void setLibelleVille(String paramLibelleVille) {
        libelleVille = paramLibelleVille;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String paramCodePostal) {
        codePostal = paramCodePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String paramPays) {
        pays = paramPays;
    }

    public List<AdresseMembre> getAdressesMembre() {
        return adressesMembre;
    }

    public void setAdressesMembre(List<AdresseMembre> paramAdressesMembre) {
        adressesMembre = paramAdressesMembre;
    }
    
    
    

}