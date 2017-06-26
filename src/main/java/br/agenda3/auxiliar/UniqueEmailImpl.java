package br.agenda3.auxiliar;

import java.io.Serializable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class UniqueEmailImpl implements ConstraintValidator<UniqueEmail, String>, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6161669793016139067L;
		
	/*@Autowired*/
	private br.agenda3.dao.UsuarioDao usuarioDao;
	
	@Override
	public void initialize(UniqueEmail uniqueEmail) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		usuarioDao = new br.agenda3.dao.UsuarioDao();
		
		boolean valid = usuarioDao.searchByAttribute("email", value) ? false : true;
		
		return valid;
	}

	

}
