package fr.afcepf.al31.yatta.business.api.utilitaire;

import java.util.List;

import fr.afcepf.al31.yatta.entities.Adresse;
import fr.afcepf.al31.yatta.entities.AdresseMembre;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Favoris;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Message;
import fr.afcepf.al31.yatta.entities.MoyenPaiement;
import fr.afcepf.al31.yatta.entities.Note;
import fr.afcepf.al31.yatta.entities.Produit;

public interface IGestionUtilMisc {

    List<Commande> getAllCommandes();
    
    List<Commentaire> getAllCommentaire();

    Commentaire getCommentaireById(Integer id);
    
    List<Membre> getAllMembre();
    
    List<Membre> getAllMembreOrderByDateInscription();
    
    List<Membre> getTop4MembreOrderByDateInscription();
    
    Membre getMembreById(Integer id);
    
    List<Membre> getMembreByName(String name);
    
    List<Favoris> getMembresFavoris(Membre membre);
    

    Integer getNoteMembre(Membre membre);
    
    List<Message> getMessageByDestinataire(Membre membre);

    Message getMessageById(Integer id);
    
    List<Note> getAllNotesProduit(Produit produit);

    Note getNoteProduitById(Integer id);
    
    Categorie getCategorieById(Integer id);
    
    List<Categorie> getCategoriesOfTutosByEspaceTuto(Integer IdEspaceTutoriel);
    
    List<Categorie> getCategoriesOfArticlesByBoutique(Integer IdBoutique);
    
    List<Categorie> getAllCategories();
    
    List<MoyenPaiement> getAllMoyenPaiement();
    
    List<Favoris> getFollowersMembre(Membre membre);
    
    AdresseMembre AjouterAdresseMembre(AdresseMembre adresseMembre);
    
    Adresse AjouterAdresse(Adresse adresse);
    
    Adresse getAdresseById(Integer id);
    
    AdresseMembre getAdresseMembreById(Integer id);
    
    Adresse getAdresseByIdApi(String id);
    
    AdresseMembre getAdresseMembreByAdresse (Adresse adresse);
}
