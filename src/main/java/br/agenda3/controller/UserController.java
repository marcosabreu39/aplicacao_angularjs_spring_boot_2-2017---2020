package br.agenda3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.agenda3.dao.UsuarioDao;
import br.agenda3.model.Usuario;

@RestController
public class UserController {
	
	@Autowired
	Usuario usuario;
	
	@Autowired	
	UsuarioDao usuarioDao;

	@PostMapping(value = "/usuario")
	public ResponseEntity<Object> saveUser(@Valid Usuario usuario, BindingResult br, Model model) {

		ResponseEntity<Object> retorno = null;
		
		Map<String, String>erros = new HashMap<>();

		if (br.hasErrors()) {
			List<FieldError> errors = br.getFieldErrors();
			for (FieldError error : errors) {
				
				erros.put(error.getField(), error.getDefaultMessage());

				retorno = new ResponseEntity<>(erros, HttpStatus.CONFLICT);
			}
		} else {
			
			usuarioDao.persist(usuario);

			retorno = new ResponseEntity<>(HttpStatus.CREATED);
		}
		return retorno;

	}
}
