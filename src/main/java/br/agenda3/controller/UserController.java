package br.agenda3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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

	// @Autowired
	// private HttpSession session;

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
				usuarioFacade.adicionarUsuario(usuario);

				retorno = new ResponseEntity<>(HttpStatus.CREATED);
			}

		} catch (final Exception e) {
			LOGGER.error("Ocorreu erro ao processar o novo usuário!", e);
		}
		return retorno;

	}

	@RequestMapping(value = "/usuarioLogon")
	public void login() {

		// ResponseEntity<Object> retorno = null;

		// this.usuario = usuario;

		// final Map<String, String> erros = new HashMap<>();

		try {
			/* if (usuarioFacade.usuarioJaCadastrado(this.usuario.getLogin(), this.usuario.getSenha())) {

				session.setAttribute("usuarioLogado", this.usuario);

				retorno = new ResponseEntity<>(HttpStatus.OK);
			} else {

				erros.put("login", "Usuário ou senha inválidos!");
				erros.put("senha", "Usuário ou senha inválidos!");

				retorno = new ResponseEntity<>(erros, HttpStatus.FORBIDDEN);

			} */

		} catch (final Exception e) {
			LOGGER.error("Ocorreu erro ao processar o usuário!", e);
		}
		// return retorno;

	}

	@PostMapping(value = "/usuarioLogout")
	public ResponseEntity<Object> logout(final Usuario usuario, HttpSession session, HttpServletRequest request) {

		this.usuario = usuario;

		final Map<String, String> erros = new HashMap<>();
		// session = Utils.obterSessao();
		session = request.getSession(false);

		if (session != null) {
			final Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

			if (usuarioLogado != null && !usuarioLogado.equals("")) {

				if (usuario.getLogin().equals(usuarioLogado.getLogin())) {
					// session = Utils.obterSessao();
					session.removeAttribute("usuarioLogado");
					// session.invalidate();
					// detalhesUsuario.setUsuario(null);
					return new ResponseEntity<>(HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>(erros, HttpStatus.CONFLICT);
				}
			} else {
				// session = Utils.obterSessao();
				// session.invalidate();
				return new ResponseEntity<>(erros, HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<>(erros, HttpStatus.CONFLICT);

	}

	@GetMapping(value = "/usuarioNaSessao")
	public ResponseEntity<Object> recuperarLogin(final HttpSession session) {

		// session = Utils.obterSessao();
		usuario = (Usuario) session.getAttribute("usuarioLogado");

		if (null != usuario  && !"".equals(usuario)) {
			final Map<String, String> u = new HashMap<>();
			u.put("loginLogado", usuario.getLogin());

			return new ResponseEntity<Object>(u, HttpStatus.OK);

		} else {
			final Map<String, String> objson = new HashMap<>();

			objson.put("logado", "false");

			return new ResponseEntity<Object>(objson, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/semSessao")
	public RedirectView atualizarSessao() {

		return new RedirectView("default.html");

	}
}
