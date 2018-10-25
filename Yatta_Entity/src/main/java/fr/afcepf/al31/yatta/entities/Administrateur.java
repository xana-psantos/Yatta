package fr.afcepf.al31.yatta.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="administrateur")
public class Administrateur extends Personne implements  Serializable{

    private static final long serialVersionUID = 1L;
    
}