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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "produit")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "titre", nullable = false, length = 150)
    private String titre;
    
    @Column(name = "description", nullable = true, length = 5000)
    private String description;

    @Column(name = "date_ajout", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAjout;

    @Column(name = "date_retrait", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetrait;
    
    @Column(name = "nbre_vue", nullable = false)
    private int nbreVue;
    
    /**
     * Association avec la classe {@link Categorie}.
     */
    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false, 
    foreignKey = @ForeignKey(name = "FK_produit_categorie"))
    private Categorie categorie;
    
    /**
     * Un tutoriel peut avoir plusieurs {@link Note}
     */
    @OneToMany(mappedBy = "produit")
    private List<Note> notes;
    
    /**
     * Un tutoriel peut avoir plusieurs {@link Commentaire}
     */
    @OneToMany(mappedBy = "produit")
    private List<Commentaire> commentaires;


    /**
     * Constructeur par défaut.
     */
    public Produit() {
        super();
    }

    /**
     * Constructeur avec paramètres.
     * @param paramId
     * @param paramDescription
     * @param paramDateAjout
     * @param paramDateRetrait
     * @param paramNbreVue
     * @param paramCategorie
     * @param paramTitre
     */
    public Produit(Integer paramId, String paramDescription, Date paramDateAjout, Date paramDateRetrait,
            int paramNbreVue, Categorie paramCategorie, String paramTitre) {
        super();
        id = paramId;
        description = paramDescription;
        dateAjout = paramDateAjout;
        dateRetrait = paramDateRetrait;
        nbreVue = paramNbreVue;
        categorie = paramCategorie;
        titre = paramTitre;
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

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date paramDateAjout) {
        dateAjout = paramDateAjout;
    }

    public Date getDateRetrait() {
        return dateRetrait;
    }

    public void setDateRetrait(Date paramDateRetrait) {
        dateRetrait = paramDateRetrait;
    }

    public int getNbreVue() {
        return nbreVue;
    }

    public void setNbreVue(int paramNbreVue) {
        nbreVue = paramNbreVue;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie paramCategorie) {
        categorie = paramCategorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String paramTitre) {
        titre = paramTitre;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> paramNotes) {
        notes = paramNotes;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> paramCommentaires) {
        commentaires = paramCommentaires;
    }
    
    
    
}
