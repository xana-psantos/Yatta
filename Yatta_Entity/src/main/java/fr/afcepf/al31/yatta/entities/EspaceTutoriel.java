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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="espace_tutoriel")
@NamedQuery(name = "EspaceTutoriel.getEspaceTutorielByCategorie",
query = "SELECT DISTINCT t.espaceTutoriel FROM Tutoriel t where t.categorie.id in (?1) and t.espaceTutoriel.dateCloture is null")
public class EspaceTutoriel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descriptif", nullable = true, length = 1000)
    private String descriptif;

    @Column(name = "image", nullable = true, length = 50)
    private String image;
    
    @Column(name = "nom", nullable = true, length = 150)
    private String nom;

    @Column(name = "date_creation", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "date_cloture", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCloture;

    /**
     * Un espace tutoriel peut avoir plusieurs {@link Tutoriel}
     */
    @OneToMany(mappedBy = "espaceTutoriel")
    private List<Tutoriel> tutoriels;

    /**
     * Un espace tutoriel est relié à un {@link Membre}
     */
    @OneToOne
    @JoinColumn(name = "id_membre", nullable = false, 
    foreignKey = @ForeignKey(name = "FK_espace_tutoriel_membre"))
    private Membre membre;

    /**
     * Un espace tutoriel peut avoir plusieurs {@link SuspensionEspaceTutoriel}
     */
    @OneToMany(mappedBy = "espaceTutoriel")
    private List<SuspensionEspaceTutoriel> suspensions;
    
    /**
     * Constructeur par défaut
     */
    public EspaceTutoriel() {
        super();
    }
    
    /**
     * Constructeur avec paramètres
     * @param paramId
     * @param paramDescriptif
     * @param paramImage
     * @param paramDateCreation
     * @param paramDateCloture
     * @param paramMembre
     */
    public EspaceTutoriel(Integer paramId, String paramDescriptif, String paramImage, Date paramDateCreation,
            Date paramDateCloture, Membre paramMembre, String paramNom) {
        super();
        id = paramId;
        descriptif = paramDescriptif;
        image = paramImage;
        dateCreation = paramDateCreation;
        dateCloture = paramDateCloture;
        membre = paramMembre;
        nom = paramNom;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String paramNom) {
        nom = paramNom;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String paramDescriptif) {
        descriptif = paramDescriptif;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String paramImage) {
        image = paramImage;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date paramDateCreation) {
        dateCreation = paramDateCreation;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date paramDateCloture) {
        dateCloture = paramDateCloture;
    }

    public List<Tutoriel> getTutoriels() {
        return tutoriels;
    }

    public void setTutoriels(List<Tutoriel> paramTutoriels) {
        tutoriels = paramTutoriels;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre paramMembre) {
        membre = paramMembre;
    }

    public List<SuspensionEspaceTutoriel> getSuspensions() {
        return suspensions;
    }

    public void setSuspensions(List<SuspensionEspaceTutoriel> paramSuspensions) {
        suspensions = paramSuspensions;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    

}