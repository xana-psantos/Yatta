package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ligne_de_commande")
public class LigneDeCommande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantite", nullable = false)
    private int quantite;

    @ManyToOne
    @JoinColumn(name="id_commande", nullable=false, foreignKey = @ForeignKey(name="FK_commande_ligne_de_commande"))
    private Commande commande;

    @ManyToOne
    @JoinColumn(name="id_article", nullable=false, foreignKey = @ForeignKey(name="FK_article_ligne_de_commande"))
    private Article article;

    public LigneDeCommande() {
        super();
    }

    public LigneDeCommande(Integer paramId, int paramQuantite, Commande paramCommande,
            Article paramArticle) {
        super();
        id = paramId;
        quantite = paramQuantite;
        commande = paramCommande;
        article = paramArticle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer paramId) {
        id = paramId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int paramQuantite) {
        quantite = paramQuantite;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande paramCommande) {
        commande = paramCommande;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article paramArticle) {
        article = paramArticle;
    }

}