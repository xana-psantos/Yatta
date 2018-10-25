package fr.afcepf.al31.yatta.business.impl.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.al31.yatta.business.api.utilitaire.IGestionUtilMisc;
import fr.afcepf.al31.yatta.dao.api.IDaoAdresse;
import fr.afcepf.al31.yatta.dao.api.IDaoAdresseMembre;
import fr.afcepf.al31.yatta.dao.api.IDaoCategorie;
import fr.afcepf.al31.yatta.dao.api.IDaoFavoris;
import fr.afcepf.al31.yatta.dao.api.IDaoMembre;
import fr.afcepf.al31.yatta.dao.api.IDaoMoyenPaiement;
import fr.afcepf.al31.yatta.entities.Adresse;
import fr.afcepf.al31.yatta.entities.AdresseMembre;
import fr.afcepf.al31.yatta.entities.Categorie;
import fr.afcepf.al31.yatta.entities.Commande;
import fr.afcepf.al31.yatta.entities.Commentaire;
import fr.afcepf.al31.yatta.entities.Favoris;
import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Message;
import fr.afcepf.al31.yatta.entities.MoyenPaiement;
import fr.afcepf.al31.yatta.entities.Note;
import fr.afcepf.al31.yatta.entities.Produit;
@Service
@Transactional
public class GestionUtilMisc implements IGestionUtilMisc {
    
    @Autowired
    private IDaoMembre daoMembre;
    
    @Autowired
    private IDaoCategorie daoCategorie;
    
    @Autowired
    private IDaoMoyenPaiement daoMoyenPaiement;
    
    @Autowired
    private IDaoFavoris daoFavoris;
    
    @Autowired
    private IDaoAdresse daoAdresse;
    
    @Autowired
    private IDaoAdresseMembre daoAdresseMembre;
    
    @Override
    public List<Commande> getAllCommandes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Commentaire> getAllCommentaire() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Commentaire getCommentaireById(Integer paramId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Membre> getAllMembre() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Membre getMembreById(Integer paramId) {
        return daoMembre.findOne(paramId);
    }

    @Override
    public List<Membre> getMembreByName(String paramName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Favoris> getMembresFavoris(Membre paramMembre) {
        return daoFavoris.findByMembreSuiveur(paramMembre);
    }

    @Override
    public Integer getNoteMembre(Membre paramMembre) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Message> getMessageByDestinataire(Membre paramMembre) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Message getMessageById(Integer paramId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Note> getAllNotesProduit(Produit paramProduit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Note getNoteProduitById(Integer paramId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Categorie getCategorieById(Integer paramId) {
        return daoCategorie.getOne(paramId);
    }

    @Override
    public List<Membre> getAllMembreOrderByDateInscription() {
        return daoMembre.findAllByOrderByDateInscription();
    }

    @Override
    public List<Membre> getTop4MembreOrderByDateInscription() {
        return daoMembre.findTop4ByOrderByDateInscription();
    }

    @Override
    public List<Categorie> getAllCategories() {
        return daoCategorie.findAll();
    }

    @Override
    public List<MoyenPaiement> getAllMoyenPaiement() {
        return daoMoyenPaiement.findAll();
    }

    @Override
    public List<Favoris> getFollowersMembre(Membre paramMembre) {
        return daoFavoris.findByMembreSuivi(paramMembre);
    }

    @Override
    public List<Categorie> getCategoriesOfTutosByEspaceTuto(Integer paramIdEspaceTutoriel) {
        return daoCategorie.chercherCategoriesTutorielsFromEspaceTuto(paramIdEspaceTutoriel);
    }

    @Override
    public List<Categorie> getCategoriesOfArticlesByBoutique(Integer paramIdBoutique) {
        return daoCategorie.listerCategorieBoutique(paramIdBoutique);
    }

    @Override
    public AdresseMembre AjouterAdresseMembre(AdresseMembre paramAdresseMembre) {
        return daoAdresseMembre.save(paramAdresseMembre);
    }

    @Override
    public Adresse AjouterAdresse(Adresse paramAdresse) {
        return daoAdresse.save(paramAdresse);
    }

    @Override
    public Adresse getAdresseById(Integer paramId) {
        return daoAdresse.findOne(paramId);
    }

    @Override
    public AdresseMembre getAdresseMembreById(Integer paramId) {
        return daoAdresseMembre.findOne(paramId);
    }

    @Override
    public Adresse getAdresseByIdApi(String paramId) {
        return daoAdresse.findByIdApi(paramId);
    }

    @Override
    public AdresseMembre getAdresseMembreByAdresse(Adresse paramAdresse) {
        return daoAdresseMembre.findByAdresse(paramAdresse);
    }

}
