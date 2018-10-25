package fr.afcepf.al31.yatta.exception;

/**
 * Exception de l'application.
 * @author xaninha
 *
 */
public class ExceptionCustom extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     * Code d'erreur de l'exception.
     */
    private ExceptionEnum codeErreur = ExceptionEnum.CA_MARCHE_PAS;
    /**
     * Default constructor.
     */
    public ExceptionCustom() {
    }
    /**
     * 
     * @param paramMessage le message a envoyer
     * @param paramCode le code d'erreur
     */
    public ExceptionCustom(String paramMessage, ExceptionEnum paramCode) {
        super(paramMessage);
        setCodeErreur(paramCode);
    }

    public ExceptionEnum getCodeErreur() {
        return codeErreur;
    }

    public void setCodeErreur(ExceptionEnum paramCodeErreur) {
        codeErreur = paramCodeErreur;
    }
}
