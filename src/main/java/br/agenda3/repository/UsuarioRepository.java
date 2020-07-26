package br.agenda3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.agenda3.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u WHERE u.login =?1 AND u.senha = ?2")
	List<Usuario> checarUsuario(String login, String senha) throws Exception;

	@Query("SELECT u FROM Usuario u WHERE u.login =?1")
	List<Usuario>checarLogin(String login) throws Exception;

	@Query("SELECT u FROM Usuario u WHERE u.login =?1")
	List<Usuario> obterLogin(String login) throws Exception;

	@Query("SELECT u FROM Usuario u WHERE u.email =?1")
	List<Usuario> checarEmail(String email) throws Exception;

	@Query("SELECT u FROM Usuario u WHERE u.email =?2 and u.email not in (SELECT u.email FROM Usuario u WHERE u.id =?1)")
	List<Usuario> checarEmail(Integer id, String email) throws Exception;

	@Query("SELECT u FROM Usuario u WHERE u.login =?2 and u.login not in (SELECT u.login FROM Usuario u WHERE u.id =?1)")
	List<Usuario>checarLogin(Integer id, String login) throws Exception;

	


}
