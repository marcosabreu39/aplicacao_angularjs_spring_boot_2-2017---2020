package br.agenda3.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.agenda3.facade.UsuarioFacade;
import br.agenda3.model.Contato;
import br.agenda3.model.Usuario;
import br.agenda3.util.Utils;

@RestController
public class ContatoController {

    @Autowired
    private UsuarioFacade usuarioFacade;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ContatoController.class);

    /* Os erros de validação são enviados diretamente para o frontend via json */
    @PostMapping("/contato")
    public ResponseEntity<Object> saveContato(@Valid Contato contato, String loginLogado, BindingResult br) {
        ResponseEntity<Object> retorno = null;
        try {
            Usuario usuario = usuarioFacade.obterUsuario(loginLogado);
            Contato contatoParaPersistir = new Contato();
            contatoParaPersistir = Utils.prepararObjetoContato(contatoParaPersistir, contato);
            contatoParaPersistir.setUsuario(usuario);
            usuario.getContatos().add(contatoParaPersistir);
            usuarioFacade.atualizarUsuario(usuario);

            retorno = new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("Ocorreu erro para salvar o novo contato!", e);
            retorno = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return retorno;
    }
}