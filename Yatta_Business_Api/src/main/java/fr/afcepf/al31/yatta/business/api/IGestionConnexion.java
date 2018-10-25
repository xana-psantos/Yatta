package fr.afcepf.al31.yatta.business.api;

import fr.afcepf.al31.yatta.entities.Membre;
import fr.afcepf.al31.yatta.entities.Personne;
import fr.afcepf.al31.yatta.exception.ExceptionCustom;

public interface IGestionConnexion {
    
    /**
     * Fonction pour inscrire un membre.
     * Fait appel à {@link #verifierMail(String) 
     * et #verifierPseudo(String) et DaoMembre.ajouter} 
     * @param membre - le membre a inscrire
     * @return le membre inscrit
     * @throws ExceptionCustom si le mail et/ou le pseudo existent
     */
    Membre inscrireMembre(Membre membre) throws ExceptionCustom;

    /** 
     * Fonction qui vérifie si le mail qu'un potentiel membre
     * souhaite utiliser existe déjà. Fait appel à DaoMembre.
     * @param mail
     * @return 
     * <ul> 
     * <li> true, si le mail est déjà utilisé par un autre membre </li> 
     * <li> false, sinon. </li> 
     * </ul>
     * @throws ExceptionCustom
     */
    boolean verifierMail(String mail) throws ExceptionCustom;

    /**
     * Fonction qui vérifie si le pseudo qu'un potentiel membre
     * souhaite utiliser existe déjà. Fait appel à DaoMembre.
     * @param pseudo
     * @return
     * <ul> 
     * <li> true, si le pseudo est déjà utilisé par un autre membre </li> 
     * <li> false, sinon. </li> 
     * </ul>
     * @throws ExceptionCustom
     */
    boolean verifierPseudo(String pseudo) throws ExceptionCustom;

    /**
     * Fonction pour désinscrire un membre. Fait appel à DaoMembre.
     * @param membre 
     * @return le membre désinscrit.
     * @throws ExceptionCustom
     */
    Membre desinscrireMembre(Membre membre) throws ExceptionCustom;

    /**
     * Fonction pour aunthentifier une personne. Fait appel à DaoPersonne.
     * @param identifiant (le pseudo ou le mot de passe)
     * @param motDePasse
     * @return 
     * <ul> 
     * <li> null si la personne n'est pas trouvé. </li> 
     * <li> une personne avec l'dentifiant et le mdp données. </li> 
     * </ul>
     * @throws ExceptionCustom
     */
    Personne authentification(String identifiant, String motDePasse) throws ExceptionCustom;

}
