package br.agenda3.util;

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

}