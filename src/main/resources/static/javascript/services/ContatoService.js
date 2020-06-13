angular.module('myApp').service('ContatoService', ['$http', function($http) {

    this.saveContato = function(contato, loginLogado, header) {
        return $http({
            method: 'POST',
            url: 'contato',
            params: { nome: contato.nome, email: contato.email, endereco: contato.endereco, observacao: contato.observacao, loginLogado: loginLogado },
            headers: header
        });
    }

    /* this.obterDadosUser = function login(usuario, header) {
        return $http({
            method: 'POST',
            url: 'usuarioDados',
            params: { login: usuario.login },
            headers: header
        });
    } */

    /* this.atualizarUser = function login(usuario, senhaBanco, header) {
        return $http({
            method: 'PUT',
            url: 'usuario/id',
            params: {
                id: usuario.id,
                nome: usuario.nome,
                email: usuario.email,
                login: usuario.login,
                senha: usuario.senha,
                dataCadastro: usuario.dataCadastro,
                senhaBanco: senhaBanco
            },
            headers: header
        });
    } */


}]);