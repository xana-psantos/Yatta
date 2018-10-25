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
@Table(name="suspension_espace_tutoriel")
public class SuspensionEspaceTutoriel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_debut", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;

    @Column(name = "date_fin", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;

    @Column(name = "description", nullable = true, length = 300)
    private String description;

    /**
     * Plusieurs suspensions sont reliées à un {@link EspaceTutoriel}
     */
    @ManyToOne
    @JoinColumn(name = "id_espace_tutoriel", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_suspension_espace_tutoriel_espace_tutoriel"))
    private EspaceTutoriel espaceTutoriel;

    
    /**
     * Constructeur par défaut
     */
    public SuspensionEspaceTutoriel() {
        super();
    }

    /**
     * Constructeur avec paramètres
     * @param paramId
     * @param paramDateDebut
     * @param paramDateFin
     * @param paramDescription
     * @param paramEspaceTutoriel
     */
    public SuspensionEspaceTutoriel(Integer paramId, Date paramDateDebut, Date paramDateFin, String paramDescription,
            EspaceTutoriel paramEspaceTutoriel) {
        super();
        id = paramId;
        dateDebut = paramDateDebut;
        dateFin = paramDateFin;
        description = paramDescription;
        espaceTutoriel = paramEspaceTutoriel;
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


    public Date getDateDebut() {
        return dateDebut;
    }


    public void setDateDebut(Date paramDateDebut) {
        dateDebut = paramDateDebut;
    }


    public Date getDateFin() {
        return dateFin;
    }


    public void setDateFin(Date paramDateFin) {
        dateFin = paramDateFin;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String paramDescription) {
        description = paramDescription;
    }


    public EspaceTutoriel getEspaceTutoriel() {
        return espaceTutoriel;
    }


    public void setEspaceTutoriel(EspaceTutoriel paramEspaceTutoriel) {
        espaceTutoriel = paramEspaceTutoriel;
    }
    
    

}