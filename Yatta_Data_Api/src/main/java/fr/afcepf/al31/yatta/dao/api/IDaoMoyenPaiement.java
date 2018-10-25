package fr.afcepf.al31.yatta.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.MoyenPaiement;

public interface IDaoMoyenPaiement extends JpaRepository<MoyenPaiement, Integer>{

}
