package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;

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
import javax.persistence.Table;

@Entity
@Table(name = "article_favoris")
@Inheritance(strategy = InheritanceType.JOINED)
public class ArticleFavoris implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "libelle", nullable = true, length = 120)
    private String libelle;

    /**
     * Association avec la classe {@link Membre}.
     */
    @ManyToOne
    @JoinColumn(name = "id_membre", nullable = false, 
    foreignKey = @ForeignKey(name = "FK_article_favoris_membre"))
    private Membre membre;

    /**
     * Association avec la classe {@link Membre}.
     */
    @ManyToOne
    @JoinColumn(name = "id_article", nullable = false, 
    foreignKey = @ForeignKey(name = "FK_article_favoris_article"))
    private Article article;
    
    /**
     * Constructeur par défaut.
     */
    public ArticleFavoris() {
        super();
    }

    /** 
     * Constructeur avec paramètres.
     * @param paramId
     * @param paramLibelle
     * @param paramMembre
     * @param paramArticle
     */
    public ArticleFavoris(Integer paramId, String paramLibelle, Membre paramMembre,
            Article paramArticle) {
        super();
        id = paramId;
        libelle = paramLibelle;
        membre = paramMembre;
        article = paramArticle;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String paramLibelle) {
        libelle = paramLibelle;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre paramMembre) {
        membre = paramMembre;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article paramArticle) {
        article = paramArticle;
    }
  
}