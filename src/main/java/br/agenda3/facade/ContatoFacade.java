package br.agenda3.facade;

import br.agenda3.model.Contato;

public interface ContatoFacade {
    
    boolean emailJaCadastrado(String email);

    boolean emailJaCadastradoParaOutroId(Integer id, String email);
    
    Contato buscarContato(Integer id);

    void atualizarContato(Contato contato);

    void removerContato(Contato contato);
}