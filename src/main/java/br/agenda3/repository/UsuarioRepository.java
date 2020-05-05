package br.agenda3.repository;

import org.springframework.data.repository.CrudRepository;

import br.agenda3.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
