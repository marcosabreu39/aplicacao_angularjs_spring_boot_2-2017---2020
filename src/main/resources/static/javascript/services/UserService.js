/**
 * 
 */
angular.module('myApp').service('UserService', ['$http', function($http) {

    this.saveUser = function saveUser(usuario) {
        return $http({
            method: 'POST',
            url: 'usuario',
            params: { nome: usuario.nome, email: usuario.email, login: usuario.login, senha: usuario.senha, dataCadastro: usuario.dataCadastro },
            headers: 'Accept:application/json'
        });
    }

    this.login = function login(usuario) {
        return $http({
            method: 'POST',
            url: 'usuarioLogon',
            data: angular.toJson(usuario, true)
        });
    }

    this.obterDadosUser = function login(usuario, header) {
        return $http({
            method: 'POST',
            url: 'usuarioDados',
            params: { login: usuario.login },
            headers: header
        });
    }

    this.atualizarUser = function login(usuario, senhaBanco, header) {
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
    }


}]);