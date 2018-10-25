package fr.afcepf.al31.yatta.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Personne;

public interface IDaoPersonne extends JpaRepository<Personne, Integer> {
    Personne authentification(String identifiant, String motDePasse);
}
