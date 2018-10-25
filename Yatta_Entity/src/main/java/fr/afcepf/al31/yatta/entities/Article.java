package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "article")
@NamedQueries({
        @NamedQuery(name="Article.listerArticlesBoutique",
            query="SELECT distinct a FROM Article a  LEFT JOIN FETCH a.notes"
                   + " WHERE a.boutique.id = ?1 and a.dateRetrait is null"),
        @NamedQuery(name="Article.listerNotesParArticle",
        query="SELECT distinct a FROM Article a  LEFT JOIN FETCH a.notes"
               + " WHERE a.id = ?1 and a.dateRetrait is null"),
        @NamedQuery(name="Article.listerCommentairesParArticle",
        query="SELECT distinct a FROM Article a  LEFT JOIN FETCH a.commentaires"
               + " WHERE a.id = ?1 and a.dateRetrait is null"),
        @NamedQuery(name="Article.afficherParBoutiqueOrderByNoteDesc",
            query="Select distinct n.produit from Note n where n.produit.boutique = ?1 and n.produit.dateRetrait is null"),
        @NamedQuery(name="Article.rechercheByCategorie", 
            query="select a from Article a where a.categorie.id in (?1) and a.dateRetrait is null"),
        @NamedQuery(name="Article.rechercheByBoutiqueByCategorie", 
        query="select a from Article a where a.categorie.id = ?1 and a.boutique.id = ?2 and a.dateRetrait is null")
})
public class Article extends Produit implements Serializable {
   
    private static final long serialVersionUID = 1L;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name="image", nullable=false, length=200)
    private String image;

    @Column(name="prix", nullable = false, columnDefinition="Decimal(10,2)")
    private Double prix;

    /**
     * Association avec la classe {@link Boutique}.
     */
    @ManyToOne
    @JoinColumn(name = "id_boutique", nullable = false, 
    foreignKey = @ForeignKey(name = "FK_article_boutique"))
    private Boutique boutique;
    
    /**
     * Un article peut appartenir à plusieurs {@link ArticlesFavoris}
     */
    @OneToMany(mappedBy = "article")
    private List<ArticleFavoris> articlesFavoris;
    
    /**
     * Un article peut appartenir à plusieurs {@link LigneDeCommande}
     */
    @OneToMany(mappedBy = "article")
    private List<LigneDeCommande> lignesDeCommande;
    
    @Transient
    private Double noteMoyenne;

    /**
     * Constructeur par défaut.
     */
    public Article() {
        super();
    }

    public Article(Integer paramId, String paramDescription, Date paramDateAjout, Date paramDateRetrait,
            int paramNbreVue, Categorie paramCategorie, String paramTitre, int paramStock, String paramImage,
            Double paramPrix, Boutique paramBoutique) {
        super(paramId, paramDescription, paramDateAjout, paramDateRetrait, paramNbreVue, paramCategorie, paramTitre);
        stock = paramStock;
        image = paramImage;
        prix = paramPrix;
        boutique = paramBoutique;
    }

    public void calculerNoteMoyenne(Article article) {
        if ( (!article.getNotes().isEmpty()) ) {
            int somme=0; 
            for (Note note : article.getNotes()) {
                somme+= note.getNote();
            }
            noteMoyenne = ((double)(somme)) / ((double)article.getNotes().size());
        }else {
            noteMoyenne= 0.0;
        }
    }
    
    public Double getNoteMoyenne() {
        return noteMoyenne;
    }

    public void setNoteMoyenne(Double paramNoteMoyenne) {
        noteMoyenne = paramNoteMoyenne;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int paramStock) {
        stock = paramStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String paramImage) {
        image = paramImage;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double paramPrix) {
        prix = paramPrix;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique paramBoutique) {
        boutique = paramBoutique;
    }

    public List<ArticleFavoris> getArticlesFavoris() {
        return articlesFavoris;
    }

    public void setArticlesFavoris(List<ArticleFavoris> paramArticlesFavoris) {
        articlesFavoris = paramArticlesFavoris;
    }

    public List<LigneDeCommande> getLignesDeCommande() {
        return lignesDeCommande;
    }

    public void setLignesDeCommande(List<LigneDeCommande> paramLignesDeCommande) {
        lignesDeCommande = paramLignesDeCommande;
    }

    
    
}