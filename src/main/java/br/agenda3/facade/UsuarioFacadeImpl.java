package br.agenda3.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.agenda3.model.Usuario;
import br.agenda3.repository.UsuarioRepository;

@Component
public class UsuarioFacadeImpl implements UsuarioFacade {

	@Autowired
	UsuarioRepository usuarioRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioFacadeImpl.class);

	@Override
	public boolean loginJaCadastrado(String login) {
		boolean existe = false;
		try {
			existe = !usuarioRepository.obterLogin(login).isEmpty();
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao verificar o login!", e);
		}

		return existe;
	}

	@Override
	public boolean emailJaCadastrado(String email) {
		boolean existe = false;
		try {
			existe = !usuarioRepository.checarEmail(email).isEmpty();
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao verificar o e-mail!", e);
		}

		return existe;
	}

	@Override
	public boolean emailJaCadastradoParaOutroId(Integer id, String email) {
		boolean retorno = false;
		try {
			retorno = !usuarioRepository.checarEmail(id, email).isEmpty();
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao verificar o e-mail!", e);
		}
		return retorno;
	}

	@Override
	public boolean loginJaCadastradoParaOutroId(Integer id, String login) {
		boolean retorno = false;
		try {
			retorno = !usuarioRepository.checarLogin(id, login).isEmpty();
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao verificar o login!", e);
		}
		return retorno;
	}

	@Override
	public void adicionarUsuario(Usuario usuario) {
		try {
			usuarioRepository.save(usuario);
			LOGGER.info("Usuário inserido com sucesso!");
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao tentar adicionar o Usuário!", e);
		}
	}

	@Override
	public boolean usuarioJaCadastrado(String login, String senha) {
		boolean existe = false;
		try {
			existe = !usuarioRepository.checarUsuario(login, senha).isEmpty();
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao verificar o usuário", e);
		}

		return existe;
	}

	@Override
	public Usuario obterUsuario(String login) {
		Usuario usuario = null;
		try {
			usuario = usuarioRepository.obterLogin(login).get(0);
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao tentar obter o usuário", e);
		}
		return usuario;
	}

	@Override
	public void atualizarUsuario(Usuario usuario) {
		try {
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			LOGGER.error("Ocorreu erro ao tentar atualizar o usuário!", e);
		}
	}

}
