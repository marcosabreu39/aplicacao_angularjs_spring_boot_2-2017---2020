package br.agenda3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.agenda3.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
    
    @Query("SELECT c FROM Contato c WHERE c.email =?1")
	List<Contato> checarEmail(String email) throws Exception;

    @Query("SELECT c FROM Contato c WHERE c.email =?2 and c.id <>?1")
    List<Contato> checarEmail(Integer id, String email) throws Exception;
}