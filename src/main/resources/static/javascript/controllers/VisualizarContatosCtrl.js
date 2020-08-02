/**
 * 
 */

angular.module('myApp').controller('VisualizarContatosCtrl', ['$scope', '$rootScope', 'pagina', 'ContatoService', function($scope, $rootScope, pagina, ContatoService) {

    if (!$rootScope.mensagem.endsWith('removido com sucesso!')) {
        $rootScope.mensagem = "Bem vindo à página de contatos";
        $rootScope.nivelAlerta('info');
    }

    $scope.pagina = pagina;

    $rootScope.defineAtivo(pagina);

    var usuarioParaObter = { 'login': $rootScope.loginLogado };

    $scope.contatos = [];

    $scope.contato = this;

    $rootScope.removerContatoSelecionado = function(contato) {
        var index = $scope.contatos.indexOf(contato);
        $scope.contatos.splice(index, 1);
    }

    /* $scope.setContatoParaRemover = function(contato) {
        $rootScope.contatoParaRemover = contato;
    } */

    $scope.obterContatosUser = function(usuario, header) {

        ContatoService.obterContatosUser(usuario, header).then(function success(response) {
                if (response.status == 200) {
                    var contatosJson = this;
                    contatosJson = response.data;
                    $scope.contatos = angular.fromJson(contatosJson);

                    for (var i = 0; i < $scope.contatos.length; i++) {
                        var telSemMascara = $scope.contatos[i].telefone;
                        $scope.contatos[i].telefone = $rootScope.adicionarMascara(telSemMascara);
                    }
                }
            },
            function error(response) {
                if (response.status == 401) {
                    $rootScope.mensagem = 'Erro ao tentar obter os contatos do usuário!'
                    $rootScope.nivelAlerta('danger');
                } else if (response.status == 403) {
                    $rootScope.mensagem = 'Erro ao tentar obter os contatos do usuário!'
                    $rootScope.nivelAlerta('danger');
                }
            });
    }

    $scope.obterContatosUser(usuarioParaObter, $rootScope.header);

    /* if (!angular.isUndefined($rootScope.contatoParaRemover.nome) && !angular.isUndefined($rootScope.contatoParaRemover.telefone)) {
        removerContato($rootScope.contatoParaRemover);
        $rootScope.contatoParaRemover = {};
    } */

}]);