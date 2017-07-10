package br.agenda3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.agenda3.model.Usuario;

@RestController
public class UserController {

	@PostMapping(value = "/usuario")
	public ResponseEntity<Object> saveUser(@Valid Usuario usuario, BindingResult br, Model model) {

		ResponseEntity<Object> retorno = null;
		
		Map<String, String>erros = new HashMap<>();

		if (br.hasErrors()) {
			List<FieldError> errors = br.getFieldErrors();
			for (FieldError error : errors) {
				/*System.out.println(error.getField() + " " + error.getDefaultMessage());*/
				erros.put(error.getField(), error.getDefaultMessage());

				retorno = new ResponseEntity<>(erros, HttpStatus.CONFLICT);
			}
		} else {
			/* persist aqui */

			retorno = new ResponseEntity<>(HttpStatus.CREATED);
		}
		return retorno;

	}
}
