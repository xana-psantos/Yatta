package fr.afcepf.al31.yatta.dao.api;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Boutique;
import fr.afcepf.al31.yatta.entities.Membre;

public interface IDaoBoutique extends JpaRepository<Boutique, Integer> {
    List<Boutique> findByMembre(Membre membre);
    Boutique getBoutiqueParCommande(Integer id);
    List<Boutique> getBoutiqueByCategorie(Collection<Integer> listeCategories);

}
