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



}]);