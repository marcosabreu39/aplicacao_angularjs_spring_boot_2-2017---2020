angular.module('myApp').service('ContatoService', ['$http', function($http) {

    this.saveContato = function(contato, loginLogado, header) {
        return $http({
            method: 'POST',
            url: 'contato',
            params: { nome: contato.nome, email: contato.email, telefone: contato.telefone, endereco: contato.endereco, observacao: contato.observacao, loginLogado: loginLogado },
            headers: header
        });
    }

    this.obterContatosUser = function(usuario, header) {
        return $http({
            method: 'GET',
            url: 'contatos',
            params: { login: usuario.login },
            headers: header
        });
    }

    this.atualizarContato = function login(contato, header) {
        return $http({
            method: 'PUT',
            url: 'contato/id',
            params: {
                id: contato.id,
                nome: contato.nome,
                email: contato.email,
                telefone: contato.telefone,
                endereco: contato.endereco,
                observacao: contato.observacao
            },
            headers: header
        });
    }


}]);