package br.agenda3.auxiliar;

import java.io.Serializable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.agenda3.dao.UsuarioDao;

public class UniqueLoginImpl implements ConstraintValidator<UniqueLogin, String>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1151552441553428879L;
		
	/*@Autowired*/
	UsuarioDao usuarioDao;
	
	@Override
	public void initialize(UniqueLogin uniqueLogin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext Context) {

		usuarioDao = new UsuarioDao();
		
		boolean valid = usuarioDao.searchByAttribute("login", value) ? false : true;

		return valid;
	}

}
