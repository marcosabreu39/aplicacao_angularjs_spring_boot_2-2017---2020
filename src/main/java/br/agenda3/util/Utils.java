package br.agenda3.util;

import br.agenda3.model.Contato;
import br.agenda3.model.Usuario;

public class Utils {

    public static Usuario atualizarObjetoUsuario(Usuario usuarioParaAtualizar, Usuario usuario) {
        if (!usuarioParaAtualizar.getId().equals(usuario.getId()))
            usuarioParaAtualizar.setId(usuario.getId());
        if (!usuarioParaAtualizar.getNome().equals(usuario.getNome()))
            usuarioParaAtualizar.setNome(usuario.getNome());
        if (!usuarioParaAtualizar.getEmail().equals(usuario.getEmail()))
            usuarioParaAtualizar.setEmail(usuario.getEmail());
        if (!usuarioParaAtualizar.getDataCadastro().equals(usuario.getDataCadastro()))
            usuarioParaAtualizar.setDataCadastro(usuario.getDataCadastro());
        if (!usuarioParaAtualizar.getLogin().equals(usuario.getLogin()))
            usuarioParaAtualizar.setLogin(usuario.getLogin());
        if (!usuarioParaAtualizar.getSenha().equals(usuario.getSenha()))
            usuarioParaAtualizar.setSenha(usuario.getSenha());

        return usuarioParaAtualizar;
    }

    public static Contato prepararObjetoContato(Contato contatoParaPersistir, Contato contato) {
        if (null != contato.getId() && !"".equals(contato.getId()))
            contatoParaPersistir.setId(contato.getId());
        if (null != contato.getNome() && !"".equals(contato.getNome()))
            contatoParaPersistir.setNome(contato.getNome());
        if (null != contato.getEmail() && !"".equals(contato.getEmail()))
            contatoParaPersistir.setEmail(contato.getEmail());
            if (null != contato.getTelefone() && !"".equals(contato.getTelefone()))
            contatoParaPersistir.setTelefone(contato.getTelefone());
        if (null != contato.getEndereco() && !"".equals(contato.getEndereco()))
            contatoParaPersistir.setEndereco(contato.getEndereco());
        if (null != contato.getObservacao() && !"".equals(contato.getObservacao()))
            contatoParaPersistir.setObservacao(contato.getObservacao());

        return contatoParaPersistir;
    }
}