package br.agenda3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.agenda3.facade.ContatoFacade;
import br.agenda3.facade.UsuarioFacade;
import br.agenda3.model.Contato;
import br.agenda3.model.Usuario;
import br.agenda3.util.Utils;

@RestController
public class ContatoController {

    @Autowired
    private Contato contato;

    @Autowired
    private UsuarioFacade usuarioFacade;

    @Autowired
    private ContatoFacade contatoFacade;

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

        } catch (final Exception e) {
            LOGGER.error("Ocorreu erro para salvar o novo contato!", e);
            retorno = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return retorno;
    }

    @GetMapping(value = "/contatos")
	public ResponseEntity<List<Object>> obterContatosUser(Usuario usuario) {
            
		    ResponseEntity<List<Object>> retorno = null;
            List<Object>contatos = new ArrayList<>();
		try {
			usuario = usuarioFacade.obterUsuario(usuario.getLogin());

            for (Contato contato : usuario.getContatos()) {
                Map<String, String> dadosContato = new HashMap<>();
                    dadosContato.put("id", contato.getId().toString());
                    dadosContato.put("nome", contato.getNome());
                    dadosContato.put("email", contato.getEmail());
                    dadosContato.put("telefone", contato.getTelefone());
                    dadosContato.put("endereco", contato.getEndereco());
                    dadosContato.put("observacao", contato.getObservacao());
                 contatos.add(dadosContato);
                }
          
			retorno = new ResponseEntity<>(contatos, HttpStatus.OK);

		} catch (final Exception e) {
			LOGGER.error("Ocorreu erro ao tentar obter os contatos do usuário!", e);
		}
		return retorno;

    }
    
	@PutMapping("/contato/id")
    public ResponseEntity<Object> atualizarContato(@Valid Contato contato, BindingResult br) {
        ResponseEntity<Object> retorno = null;
        try {
            final Map<String, String> erros = new HashMap<>();
            if (br.hasErrors()) {
                final List<FieldError> errors = br.getFieldErrors();

                for (final FieldError error : errors) {

                    erros.put(error.getField(), error.getDefaultMessage());

                    retorno = new ResponseEntity<>(erros, HttpStatus.CONFLICT);
                }
            } else {
                this.contato = contatoFacade.buscarContato(contato.getId());
                Utils.prepararObjetoContato(this.contato, contato);
                contatoFacade.atualizarContato(this.contato);
                retorno = new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (final Exception e) {
            LOGGER.error("Ocorreu erro ao obter o usuário!", e);
            retorno = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return retorno;
    }
}