package br.agenda3.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.agenda3.model.Usuario;
import br.agenda3.repository.UsuarioRepository;

@Component
public class UsuarioFacadeImpl implements UsuarioFacade {

	@Autowired
	UsuarioRepository usuarioRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioFacadeImpl.class);

//	@Override
//	public boolean jaCadastrado(String nomeAtributo, String atributo) {
//		boolean existe = false;
//		try {
//			existe = usuarioRepository.find(nomeAtributo, atributo).isEmpty();
//		} catch (Exception e) {
//			LOGGER.error("Ocorreu um erro ao verificar o atributo!", e);
//		}
//
//		return existe;
//	}

	@Override
	public boolean loginJaCadastrado(String login) {
		boolean existe = false;
		try {
			existe = !usuarioRepository.checarLogin(login).isEmpty();
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao verificar o login!", e);
		}

		return existe;
	}

	@Override
	public boolean emailJaCadastrado(String login) {
		boolean existe = false;
		try {
			existe = !usuarioRepository.checarEmail(login).isEmpty();
		} catch (Exception e) {
			LOGGER.error("Ocorreu um erro ao verificar o e-mail!", e);
		}

		return existe;
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

}
