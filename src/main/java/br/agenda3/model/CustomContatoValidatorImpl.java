package br.agenda3.model;

import java.io.Serializable;

import javax.validation.ConstraintValidator;

    
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.agenda3.facade.ContatoFacade;
import javax.validation.ConstraintValidatorContext;

public class CustomContatoValidatorImpl implements ConstraintValidator<CustomContatoValidator, Contato>, Serializable { 
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
    private ContatoFacade contatoFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomContatoValidatorImpl.class);

    @Override
    public void initialize(CustomContatoValidator customContatoValidator) {
    }

    @Override
    public boolean isValid(Contato contato, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

            return emailIsValid(contato, context);
    }

    public boolean emailIsValid(Contato contato, ConstraintValidatorContext context) {
        boolean valid = false;
        try {
            if (contato.getId() == null) {
                valid = !contatoFacade.emailJaCadastrado(contato.getEmail());
            } else {
                valid = !contatoFacade.emailJaCadastradoParaOutroId(contato.getId(), contato.getEmail());
            }

            if (!valid) {
                context.buildConstraintViolationWithTemplate("Este e-mail já está cadastrado!").addPropertyNode("email").addConstraintViolation();
            }

        } catch (Exception e) {
            LOGGER.error("Ocorreu erro no momento de verificar o e-mail!", e);
        }

        return valid;
    }
}