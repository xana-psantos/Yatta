package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tutoriel")
@NamedQuery(name="Tutoriel.rechercheByCategorie", 
            query="select t from Tutoriel t where t.categorie.id in (?1) and t.dateRetrait is null")

public class Tutoriel extends Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "contenu", nullable = false, length = 500)
    private String contenu;

    /**
     * Plusieurs tutoriels sont reliés à un {@link EspaceTutoriel}
     */
    @ManyToOne
    @JoinColumn(name = "id_espace_tutoriel", nullable = false, 
                    foreignKey = @ForeignKey(name = "FK_tutoriel_espace_tutoriel"))
    private EspaceTutoriel espaceTutoriel;

    /**
     * Un tutoriel peut appartenir à plusieurs {@link TutorielFavoris}
     */
    @OneToMany(mappedBy = "tutoriel")
    private List<TutorielFavoris> tutorielsFavoris;

    /**
     * Constructeur par défaut.
     */
    public Tutoriel() {
        super();
    }

    public Tutoriel(Integer paramId, String paramDescription, Date paramDateAjout, Date paramDateRetrait,
            int paramNbreVue, Categorie paramCategorie, String paramTitre, String paramContenu,
            EspaceTutoriel paramEspaceTutoriel) {
        super(paramId, paramDescription, paramDateAjout, paramDateRetrait, paramNbreVue, paramCategorie, paramTitre);
        contenu = paramContenu;
        espaceTutoriel = paramEspaceTutoriel;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String paramContenu) {
        contenu = paramContenu;
    }

    public EspaceTutoriel getEspaceTutoriel() {
        return espaceTutoriel;
    }

    public void setEspaceTutoriel(EspaceTutoriel paramEspaceTutoriel) {
        espaceTutoriel = paramEspaceTutoriel;
    }

    public List<TutorielFavoris> getTutorielsFavoris() {
        return tutorielsFavoris;
    }

    public void setTutorielsFavoris(List<TutorielFavoris> paramTutorielsFavoris) {
        tutorielsFavoris = paramTutorielsFavoris;
    }

}