package fr.afcepf.al31.yatta.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Produit;

public interface IDaoProduit extends JpaRepository<Produit, Integer> {

}
