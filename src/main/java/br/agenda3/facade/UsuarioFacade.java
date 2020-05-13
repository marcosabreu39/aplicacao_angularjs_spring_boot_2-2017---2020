package br.agenda3.facade;

import br.agenda3.model.Usuario;

public interface UsuarioFacade {

	void adicionarUsuario(Usuario usuario);

	boolean emailJaCadastrado(String login);

	boolean loginJaCadastrado(String login);

	boolean usuarioJaCadastrado(String login, String senha);

}
