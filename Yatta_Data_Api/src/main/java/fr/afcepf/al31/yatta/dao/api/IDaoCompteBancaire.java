package fr.afcepf.al31.yatta.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.CompteBancaire;

public interface IDaoCompteBancaire extends JpaRepository<CompteBancaire, Integer>{

}
