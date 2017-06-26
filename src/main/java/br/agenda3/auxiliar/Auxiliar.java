package br.agenda3.auxiliar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.agenda3.dao.UsuarioDao;

@Component
public class Auxiliar {

	@Autowired
	private UsuarioDao usuarioDao;

	public Date gerarDataEhoraAtual() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataEhoraCadastroSTR = fmt.format(data);
		Date dataEhoraAtual = null;
		try {
			dataEhoraAtual = (Date) fmt.parse(dataEhoraCadastroSTR);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataEhoraAtual;
	}

	public String checarAtributo(String nomeAtributo, String atributo) {
		String msgAtributo = null;
		
		try {
			msgAtributo = usuarioDao.searchByAttribute(nomeAtributo, atributo)
					? "Esse " + nomeAtributo + " já está cadastrado." : "";
		} catch (Exception e) {
			// TODO: handle exception
		}

		return msgAtributo;

	}

}
