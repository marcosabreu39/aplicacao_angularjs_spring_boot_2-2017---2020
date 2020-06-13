package br.agenda3.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}