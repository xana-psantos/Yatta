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
@Table(name="suspension_membre")
public class SuspensionMembre implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="date_debut", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;

    @Column(name="date_fin", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;

    @Column(name="description", nullable=true, length=255)
    private String description;

    /**
     * la suspension concerne un {@link Membre}.
     */
    @ManyToOne
    @JoinColumn(name="id_membre", 
                foreignKey=@ForeignKey(name="FK_Membre_Suspension_Membre"))
    private Membre membre;

    public SuspensionMembre() {
        super();
    }

    public SuspensionMembre(Integer paramId, Date paramDateDebut, Date paramDateFin, String paramDescription,
            Membre paramMembre) {
        super();
        id = paramId;
        dateDebut = paramDateDebut;
        dateFin = paramDateFin;
        description = paramDescription;
        membre = paramMembre;
    }

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

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre paramMembre) {
        membre = paramMembre;
    }
}