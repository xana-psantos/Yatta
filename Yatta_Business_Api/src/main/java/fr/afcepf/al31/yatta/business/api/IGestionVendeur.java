package fr.afcepf.al31.yatta.business.api;

import fr.afcepf.al31.yatta.entities.Article;
import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.CompteBancaire;
import fr.afcepf.al31.yatta.entities.SuspensionBoutique;

public interface IGestionVendeur {
    
    Article ajouterArticle(Article article);

    Article modifierArticle(Article article);

    Article retirerArticle(Article article);
    
    Boutique modifierBoutique(Boutique boutique);

    Boutique fermerBoutique(Boutique boutique);
    
    CompteBancaire ajouterCompteBancaire(CompteBancaire compte);

    CompteBancaire modifierCompteBancaire(CompteBancaire compte);
    
    SuspensionBoutique modifierSuspension(SuspensionBoutique suspension);

    SuspensionBoutique suspendreBoutique(SuspensionBoutique suspension);

    SuspensionBoutique annulerSuspension(SuspensionBoutique suspension);
}
