package br.agenda3.model;

import java.io.Serializable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.agenda3.facade.UsuarioFacade;

@Component
public class UniqueEmailImpl implements ConstraintValidator<UniqueEmail, String>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	UsuarioFacade usuarioFacade;

	private static final Logger LOGGER = LoggerFactory.getLogger(UniqueEmailImpl.class);

	@Override
	public void initialize(UniqueEmail uniqueEmail) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = false;
		try {
			valid = !usuarioFacade.emailJaCadastrado(value);
		} catch (Exception e) {
			LOGGER.error("Ocorreu erro no momento de verificar o e-mail!", e);
		}

		return valid;
	}
}
