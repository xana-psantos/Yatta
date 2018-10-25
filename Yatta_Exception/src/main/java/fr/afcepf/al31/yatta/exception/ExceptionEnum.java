package fr.afcepf.al31.yatta.exception;
/**
 * Liste des codes d'erreur de l'application.
 * @author xaninha
 *
 */
public enum ExceptionEnum {
    /**
     * On ne sait pas, mais ça ne marche pas.
     */
    CA_MARCHE_PAS,
    /**
     * Le serveur ne repond pas.
     */
    SERVEUR_DONNEES_HS,
    /**
     * probleme de not-null dans le systeme de persistence.
     */
    DATA_VIOLATION_CONTRAINTE_DONNEES_NULL,
    /**
     * probleme de volume de données dans le systeme de persistence.
     */
    DATA_VIOLATION_CONTRAINTE_DONNEES_TOO_LONG,
    /**
     * probleme de cle etrangere.
     */
    DATA_VIOLATION_CONTRAINTE_INTEGRITE,
    /**
     * le mail existe (dans regle metier voir SFD).
     */
    BUSINESS_MAIL_EXISTE,
    /**
     * le nom existe.
     */
    BUSINESS_NOM_EXISTE,
    /**
     * le mail et le nom existent
     */
    BUSINESS_MAIL_NOM_EXISTENT,
    /**
     * la date de la rencontre est au passé
     */
}
