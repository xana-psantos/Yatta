package fr.afcepf.al31.yatta.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="membre")
public class Membre extends Personne {

    private static final long serialVersionUID = 1L;

    @Column (name="date_naissance", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;

    @Column (name="pseudonyme", nullable=false, length=50)
    private String pseudonyme;
    
    @Column (name="description", nullable= true, length=500)
    private String description;

    @Column (name="date_inscription", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInscription;

    @Column (name="date_desinscription", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDesinscription;
    
    @Transient
    private Boutique boutique;
    
    @Transient
    private EspaceTutoriel espaceTutoriel;
    
    /**
     * Un membre peut posséder plusieurs {@link TutorielFavoris}
     */
    @OneToMany(mappedBy = "membre")
    private List<TutorielFavoris> tutorielsFavoris;
    
    /**
     * Un membre peut laisser plusieurs {@link Commentaire}
     */
    @OneToMany(mappedBy = "membre")
    private List<Commentaire> commentaires;
    
    /**
     * Un membre peut donner plusieurs {@link NoteArticle}
     */
    @OneToMany(mappedBy = "membre")
    private List<Note> notesProduits;
    
    /**
     * Un membre peut posséder plusieurs {@link ArticleFavoris}
     */
    @OneToMany(mappedBy = "membre")
    private List<ArticleFavoris> articlesFavoris;
    
    /**
     * Un membre peut passer plusieurs {@link Commande}
     */
    @OneToMany(mappedBy = "acheteur")
    private List<Commande> commandes;
    
    /**
     * Un membre peut avoir plusieurs {@link AdresseMembre}
     */
    @OneToMany(mappedBy = "membre")
    private List<AdresseMembre> adressesMembre;
    
    /**
     * Un membre peut etre le favoris de plusieurs autres Membres
     */
    @OneToMany(mappedBy= "membreSuiveur")
    private List<Favoris> followers;

    /**
     * Un membre peut avoir plusieurs membres favoris
     */
    @OneToMany(mappedBy= "membreSuivi")
    private List<Favoris> favoris;

    /**
     * Un membre peut avoir plusieurs {@link SuspensionMembre}
     */
    @OneToMany(mappedBy = "membre")
    private List<SuspensionMembre> suspensions;

    public Membre() {
        super();
    }
    
    public Membre(Integer paramId, String paramNom, String paramPrenom, String paramMail, String paramMotDePasse,
            String paramPhoto, Date paramDateNaissance, String paramPseudonyme, Date paramDateInscription,
            Date paramDateDesinscription) {
        super(paramId, paramNom, paramPrenom, paramMail, paramMotDePasse, paramPhoto);
        dateNaissance = paramDateNaissance;
        pseudonyme = paramPseudonyme;
        dateInscription = paramDateInscription;
        dateDesinscription = paramDateDesinscription;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date paramDateNaissance) {
        dateNaissance = paramDateNaissance;
    }

    public String getPseudonyme() {
        return pseudonyme;
    }

    public void setPseudonyme(String paramPseudonyme) {
        pseudonyme = paramPseudonyme;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date paramDateInscription) {
        dateInscription = paramDateInscription;
    }

    public Date getDateDesinscription() {
        return dateDesinscription;
    }

    public void setDateDesinscription(Date paramDateDesinscription) {
        dateDesinscription = paramDateDesinscription;
    }

    public List<TutorielFavoris> getTutorielsFavoris() {
        return tutorielsFavoris;
    }

    public void setTutorielsFavoris(List<TutorielFavoris> paramTutorielsFavoris) {
        tutorielsFavoris = paramTutorielsFavoris;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> paramCommentaires) {
        commentaires = paramCommentaires;
    }

    public List<ArticleFavoris> getArticlesFavoris() {
        return articlesFavoris;
    }

    public void setArticlesFavoris(List<ArticleFavoris> paramArticlesFavoris) {
        articlesFavoris = paramArticlesFavoris;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> paramCommandes) {
        commandes = paramCommandes;
    }

    public List<AdresseMembre> getAdressesMembre() {
        return adressesMembre;
    }

    public void setAdressesMembre(List<AdresseMembre> paramAdressesMembre) {
        adressesMembre = paramAdressesMembre;
    }

    public List<Favoris> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Favoris> paramFollowers) {
        followers = paramFollowers;
    }

    public List<Favoris> getFavoris() {
        return favoris;
    }

    public void setFavoris(List<Favoris> paramFavoris) {
        favoris = paramFavoris;
    }

    public List<SuspensionMembre> getSuspensions() {
        return suspensions;
    }

    public void setSuspensions(List<SuspensionMembre> paramSuspensions) {
        suspensions = paramSuspensions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String paramDescription) {
        description = paramDescription;
    }

    public List<Note> getNotesProduits() {
        return notesProduits;
    }

    public void setNotesProduits(List<Note> paramNotesProduits) {
        notesProduits = paramNotesProduits;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique paramBoutique) {
        boutique = paramBoutique;
    }

    public EspaceTutoriel getEspaceTutoriel() {
        return espaceTutoriel;
    }

    public void setEspaceTutoriel(EspaceTutoriel paramEspaceTutoriel) {
        espaceTutoriel = paramEspaceTutoriel;
    }
    
}