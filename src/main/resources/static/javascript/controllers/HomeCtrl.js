/**
 * 
 */
angular.module('myApp').controller('HomeCtrl', function($scope, $rootScope, pagina) {

    if (!$rootScope.mensagem.endsWith('sucesso!')) {
        $rootScope.mensagem = "Bem vindo à página inicial";
    }
    $rootScope.nivelAlerta('info');
    $scope.pagina = pagina;
    $rootScope.defineAtivo(pagina);


});