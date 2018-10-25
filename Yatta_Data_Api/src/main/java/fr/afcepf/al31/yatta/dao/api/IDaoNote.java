package fr.afcepf.al31.yatta.dao.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afcepf.al31.yatta.entities.Note;

public interface IDaoNote extends JpaRepository<Note, Integer>{

    List<Integer> listerNotesArticlesBoutique(Integer idBoutique);
}
