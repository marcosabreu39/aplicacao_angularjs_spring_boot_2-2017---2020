/**
 * 
 */

angular.module('myApp').controller('DetalhesContatoCtrl', ['$scope', '$rootScope', 'pagina', function($scope, $rootScope, pagina) {

    if (!$rootScope.mensagem.endsWith('sucesso!')) {
        $rootScope.mensagem = "Bem vindo à página de detalhes do contato";
    }

    $scope.pagina = pagina;

    $rootScope.defineAtivo(pagina);

    $rootScope.nivelAlerta('info');

    var contatoParaVisualizar = $rootScope.contatoSelecionado;

    if (angular.isUndefined(contatoParaVisualizar.nome) || angular.isUndefined(contatoParaVisualizar.telefone)) {
        $rootScope.redirect('home');
    } else {
        $scope.contato = contatoParaVisualizar;
    }
}]);