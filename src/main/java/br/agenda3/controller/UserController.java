package br.agenda3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.agenda3.facade.UsuarioFacade;
import br.agenda3.model.Privilegio;
import br.agenda3.model.Usuario;

@RestController
public class UserController {

	@Autowired
	Usuario usuario;

	@Autowired
	UsuarioFacade usuarioFacade;

	@Autowired
	Privilegio privilegio;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PostMapping(value = "/usuario")
	public ResponseEntity<Object> saveUser(@Valid Usuario usuario, BindingResult br) {

		ResponseEntity<Object> retorno = null;

		final Map<String, String> erros = new HashMap<>();

		try {
			if (br.hasErrors()) {
				final List<FieldError> errors = br.getFieldErrors();

				for (final FieldError error : errors) {

					erros.put(error.getField(), error.getDefaultMessage());

					retorno = new ResponseEntity<>(erros, HttpStatus.CONFLICT);
				}
			} else {
				privilegio.setId(2);
				privilegio.setTipo("user");
				usuario.getPrivilegios().add(privilegio);
				usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
				usuarioFacade.adicionarUsuario(usuario);

				retorno = new ResponseEntity<>(HttpStatus.CREATED);
			}

		} catch (final Exception e) {
			LOGGER.error("Ocorreu erro ao processar o novo usuário!", e);
		}
		return retorno;

	}

	/* Toda a validação é feita via Spring Security e JWT */
	@PostMapping(value = "/usuarioLogon")
	public void login() {
	}

	@PostMapping(value = "/usuarioDados")
	public ResponseEntity<Object> obterDadosUser(Usuario usuario) {

		ResponseEntity<Object> retorno = null;

		final Map<String, String> dadosUser = new HashMap<>();

		try {
			this.usuario = usuarioFacade.obterUsuario(usuario.getLogin());

			dadosUser.put("id", this.usuario.getId().toString());
			dadosUser.put("nome", this.usuario.getNome());
			dadosUser.put("email", this.usuario.getEmail());
			dadosUser.put("dataCadastro", this.usuario.getDataCadastro().toString());
			dadosUser.put("login", this.usuario.getLogin());
			dadosUser.put("senha", this.usuario.getSenha());

			retorno = new ResponseEntity<>(dadosUser, HttpStatus.OK);

		} catch (final Exception e) {
			LOGGER.error("Ocorreu erro ao tentar obter os dados do usuário!", e);
		}
		return retorno;

	}

	/* Os erros de validação são enviados diretamente para o frontend via json */
	@PutMapping("/usuario/id")
	public ResponseEntity<Object> atualizarUser(@Valid Usuario usuario, String senhaBanco, BindingResult br) {
		ResponseEntity<Object> retorno = null;
		try {
			if (usuario.getSenha().equals(senhaBanco)) {
				usuarioFacade.atualizarUsuario(usuario);

				retorno = new ResponseEntity<>(HttpStatus.OK);
			} else {
				usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));

				usuarioFacade.atualizarUsuario(usuario);

				retorno = new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			LOGGER.error("Ocorreu erro ao obter o usuário!", e);
			retorno = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	}

}
