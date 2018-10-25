package fr.afcepf.al31.yatta.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Adresse;

public interface IDaoAdresse extends JpaRepository<Adresse, Integer>{
    Adresse findByIdApi(String id);
}
