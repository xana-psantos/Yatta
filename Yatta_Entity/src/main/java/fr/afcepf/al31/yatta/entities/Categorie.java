package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categorie")
@NamedQueries({
    @NamedQuery(name="Categorie.listerCategorieBoutique",
            query="SELECT DISTINCT a.categorie " + 
                    " FROM Article a" + 
                    " WHERE a.boutique.id = ?1 AND a.dateRetrait IS NULL"),
    @NamedQuery(name="Categorie.chercherCategoriesTutorielsFromEspaceTuto",
            query="SELECT DISTINCT t.categorie FROM Tutoriel t WHERE t.espaceTutoriel.id = ?1 AND t.dateRetrait IS NULL")
})

public class Categorie implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;
    
    @OneToMany(mappedBy= "categorie")
    private List<Produit> produits;

    public Categorie() {
        super();
    }

    public Categorie(Integer paramId, String paramLibelle) {
        super();
        id = paramId;
        libelle = paramLibelle;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> paramProduits) {
        produits = paramProduits;
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
    
}