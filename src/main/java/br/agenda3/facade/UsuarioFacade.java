package br.agenda3.facade;

import br.agenda3.model.Usuario;

public interface UsuarioFacade {

	void adicionarUsuario(Usuario usuario);

	boolean loginJaCadastrado(String login);

	boolean loginJaCadastradoParaOutroId(Integer id, String login);

	boolean emailJaCadastrado(String email);

	boolean emailJaCadastradoParaOutroId(Integer id, String email);

	boolean usuarioJaCadastrado(String login, String senha);

	Usuario obterUsuario(String login);

	void atualizarUsuario(Usuario usuario);

	public Usuario buscarUsuario(Integer id);	
}
