package br.agenda3.model;

import java.io.Serializable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.agenda3.facade.ContatoFacade;

public class UniqueAttributeImpl implements ConstraintValidator<UniqueAttribute, String>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(UniqueAttributeImpl.class);

    @Autowired
    private ContatoFacade contatoFacade;

    @Override
    public void initialize(UniqueAttribute uniqueAttribute) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        return emailContatoIsValid(value, context);
    }

    public boolean emailContatoIsValid(String email, ConstraintValidatorContext context) {
        boolean valid = false;
        try {
            return !contatoFacade.emailJaCadastrado(email);
        } catch (Exception e) {
            LOGGER.error("Ocorreu erro para validar o e-mail!", e);
        }
        return valid;
    }
}
