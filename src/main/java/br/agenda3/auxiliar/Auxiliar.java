package br.agenda3.auxiliar;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Auxiliar {

	public Date gerarDataEhoraAtual() throws Exception {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataEhoraCadastroSTR = fmt.format(data);
		Date dataEhoraAtual = null;		
		dataEhoraAtual = (Date) fmt.parse(dataEhoraCadastroSTR);
		
		return dataEhoraAtual;
	}

}
