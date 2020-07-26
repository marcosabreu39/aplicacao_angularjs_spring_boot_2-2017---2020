package br.agenda3.model;

import java.io.Serializable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.agenda3.facade.UsuarioFacade;

public class CustomUsuarioValidatorImpl implements ConstraintValidator<CustomUsuarioValidator, Usuario>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioFacade usuarioFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUsuarioValidatorImpl.class);

    @Override
    public void initialize(CustomUsuarioValidator customUsuarioValidator) {
    }

    @Override
    public boolean isValid(Usuario usuario, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

            return loginIsValid(usuario, context) & emailIsValid(usuario, context);
    }

    public boolean loginIsValid(Usuario usuario, ConstraintValidatorContext context) {
        boolean valid = false;
        try {
            if (usuario.getId() == null) {
                valid = !usuarioFacade.loginJaCadastrado(usuario.getLogin());
            } else {
                valid = !usuarioFacade.loginJaCadastradoParaOutroId(usuario.getId(), usuario.getLogin());
            }

            if (!valid) {
                context.buildConstraintViolationWithTemplate("Este login já está cadastrado!").addPropertyNode("login").addConstraintViolation();
            }

        } catch (Exception e) {
            LOGGER.error("Ocorreu erro no momento de verificar o login!", e);
        }

        return valid;
    } 

    public boolean emailIsValid(Usuario usuario, ConstraintValidatorContext context) {
        boolean valid = false;
        try {
            if (usuario.getId() == null) {
                valid = !usuarioFacade.emailJaCadastrado(usuario.getEmail());
            } else {
                valid = !usuarioFacade.emailJaCadastradoParaOutroId(usuario.getId(), usuario.getEmail());
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
