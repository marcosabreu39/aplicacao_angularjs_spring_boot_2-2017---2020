package br.agenda3.facade;


public interface ContatoFacade {
    
    boolean emailJaCadastrado(String email);

	boolean emailJaCadastradoParaOutroId(Integer id, String email);
}