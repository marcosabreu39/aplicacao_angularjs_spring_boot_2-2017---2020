package br.agenda3.facade;

import br.agenda3.model.Usuario;

public interface UsuarioFacade {

	void adicionarUsuario(Usuario usuario);

	boolean emailJaCadastrado(String email);

	boolean loginJaCadastrado(String login);

	boolean emailJaCadastradoParaOutroId(Integer id, String email);

	boolean loginJaCadastradoParaOutroId(Integer id, String login);

	boolean usuarioJaCadastrado(String login, String senha);

	Usuario obterUsuario(String login);

	void atualizarUsuario(Usuario usuario);

	
}
