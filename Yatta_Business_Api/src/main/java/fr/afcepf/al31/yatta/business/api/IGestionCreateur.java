package fr.afcepf.al31.yatta.business.api;

import fr.afcepf.al31.yatta.entities.EspaceTutoriel;
import fr.afcepf.al31.yatta.entities.SuspensionEspaceTutoriel;
import fr.afcepf.al31.yatta.entities.Tutoriel;

public interface IGestionCreateur {

    EspaceTutoriel modifierEspaceTutoriel(EspaceTutoriel espace);

    EspaceTutoriel annulerEspaceTutoriel(EspaceTutoriel espace);

    Tutoriel ajouterTutoriel(Tutoriel tutoriel);

    Tutoriel modifierTutoriel(Tutoriel tutoriel);

    Tutoriel annulerTutoriel(Tutoriel tutoriel);

    SuspensionEspaceTutoriel modifierSuspensionEspaceTuto(SuspensionEspaceTutoriel suspension);

    SuspensionEspaceTutoriel ajouterSuspensionEspaceTuto(SuspensionEspaceTutoriel suspension);

    SuspensionEspaceTutoriel annulerSuspensionEspaceTuto(SuspensionEspaceTutoriel suspension);
}
