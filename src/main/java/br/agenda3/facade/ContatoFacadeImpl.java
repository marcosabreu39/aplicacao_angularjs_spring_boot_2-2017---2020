package br.agenda3.facade;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.agenda3.model.Contato;
import br.agenda3.repository.ContatoRepository;

@Component
public class ContatoFacadeImpl implements ContatoFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContatoFacadeImpl.class);

    @Autowired
    private ContatoRepository contatoRepository;

    @Override
    public boolean emailJaCadastrado(String email) {
        boolean retorno = false;
        try {
            retorno = !contatoRepository.checarEmail(email).isEmpty();
        } catch (Exception e) {
            LOGGER.error("Ocorreu erro ao verificar o contato!", e);
        }
        return retorno;
    }

    @Override
    public boolean emailJaCadastradoParaOutroId(Integer id, String email) {
        boolean retorno = false;
        try {
            retorno = !contatoRepository.checarEmail(id, email).isEmpty();
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro ao verificar o e-mail!", e);
        }
        return retorno;
    }

    @Override
    public Contato buscarContato(Integer id) {
        Optional<Contato> retorno = null;
        try {
            retorno = contatoRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro ao tentar atualizar o contato!", e);
        }
        return retorno.get();
    }

    @Override
	public void atualizarContato(Contato contato) {
		try {
			contatoRepository.save(contato);
		} catch (Exception e) {
			LOGGER.error("Ocorreu erro ao tentar atualizar o contato!", e);
		}
	}
}