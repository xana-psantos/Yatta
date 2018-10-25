package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "boutique")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Boutique.getBoutiqueParCommande",
            query = "SELECT DISTINCT lg.article.boutique FROM LigneDeCommande lg WHERE lg.commande.id = ?1"),
    @NamedQuery(name = "Boutique.getBoutiqueByCategorie",
            query = "SELECT DISTINCT a.boutique FROM Article a where a.categorie.id in (?1) and a.boutique.dateFermeture is null")
})

public class Boutique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nom", nullable = false, length = 150)
    private String nom;
    

    @Column(name = "description", nullable = true, length = 1000)
    private String description;

    @Column(name="image", nullable=true, length=200)
    private String image;

    @Column(name = "date_ouverture", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOuverture;

    @Column(name = "date_fermeture", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFermeture;

    /**
     * Association avec la classe {@link Membre}.
     */
    @OneToOne
    @JoinColumn(name = "id_membre", nullable = false, 
    foreignKey = @ForeignKey(name = "FK_boutique_membre"))
    private Membre membre;

    /**
     * Liste d'association avec la classe {@link Article}.
     */
    @OneToMany(mappedBy = "boutique")
    private List<Article> articles;

    //CORRIGE :
    //http://www.java2s.com/Tutorials/Java/JPA/0720__JPA_OneToOne_Join_Column.htm
    /**
     * Association avec la classe {@link CompteBancaire}.
     */
    @OneToOne(mappedBy = "boutique")
    private CompteBancaire compteBancaire;

    /**
     * Liste d'association avec la classe {@link SuspensionBoutique}.
     */
    @OneToMany(mappedBy = "boutique")
    private List<SuspensionBoutique> suspensions;

    /**
     * Constructeur par défaut.
     */
    public Boutique() {
        super();
    }

    /** 
     * Constructeur avec paramètres.
     * @param paramId
     * @param paramDescription
     * @param paramImage
     * @param paramDateOuverture
     * @param paramDateFermeture
     * @param paramMembre
     * @param paramCompteBancaire
     */
    public Boutique(Integer paramId, String paramDescription, String paramImage, String paramNom,
            Date paramDateOuverture, Date paramDateFermeture, Membre paramMembre,
            CompteBancaire paramCompteBancaire) {
        super();
        id = paramId;
        description = paramDescription;
        image = paramImage;
        nom = paramNom;
        dateOuverture = paramDateOuverture;
        dateFermeture = paramDateFermeture;
        membre = paramMembre;
        compteBancaire = paramCompteBancaire;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String paramDescription) {
        description = paramDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String paramImage) {
        image = paramImage;
    }

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(Date paramDateOuverture) {
        dateOuverture = paramDateOuverture;
    }

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(Date paramDateFermeture) {
        dateFermeture = paramDateFermeture;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre paramMembre) {
        membre = paramMembre;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> paramArticles) {
        articles = paramArticles;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire paramCompteBancaire) {
        compteBancaire = paramCompteBancaire;
    }

    public List<SuspensionBoutique> getSuspensions() {
        return suspensions;
    }

    public void setSuspensions(List<SuspensionBoutique> paramSuspensions) {
        suspensions = paramSuspensions;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String paramNom) {
        nom = paramNom;
    }

}