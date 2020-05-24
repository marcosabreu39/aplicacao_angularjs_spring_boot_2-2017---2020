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

    /* this.login = function login(usuario) {
        return $http({
            method: 'POST',
            url: 'usuarioLogon',
            params: { login: usuario.login, senha: usuario.senha },
            headers: 'Accept:application/json'
        });
    } */

    this.login = function login(usuario) {
        return $http({
            method: 'POST',
            url: 'usuarioLogon',
            // headers: { '\'Access-Control-Expose-Headers\'': '\'Authorization\'' },
            data: angular.toJson(usuario, true),
        });
    }

    this.logout = function(usuario) {
        return $http({
            method: 'POST',
            url: 'logout',
            // params: { login: usuario.login },
            headers: 'Accept:application/json'
        });
    }

    this.atualizarSessao = function() {
        return $http({
            method: 'GET',
            url: 'semSessao',
            headers: 'Accept:application/json'
        });
    }

    this.recuperarLogin = function() {
        return $http({
            method: 'GET',
            url: 'usuarioNaSessao',
            headers: 'Accept:application/json'
        });
    }

}]);