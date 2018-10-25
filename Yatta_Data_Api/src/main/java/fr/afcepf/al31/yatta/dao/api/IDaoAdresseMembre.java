package fr.afcepf.al31.yatta.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Adresse;
import fr.afcepf.al31.yatta.entities.AdresseMembre;
import fr.afcepf.al31.yatta.entities.Membre;

public interface IDaoAdresseMembre extends JpaRepository<AdresseMembre, Integer> {
    AdresseMembre findByAdresse(Adresse adresse);
    AdresseMembre findByMembre(Membre membre);
}
