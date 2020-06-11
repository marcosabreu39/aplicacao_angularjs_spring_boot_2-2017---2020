/**
 * 
 */
angular.module('myApp').controller('HomeCtrl', function($scope, $rootScope, pagina) {


    /* if (!angular.equals($rootScope.mensagem, 'Usuário logado com sucesso!' &&
            !angular.equals($rootScope.mensagem, 'Logout realizado com sucesso!'))) {
        $rootScope.mensagem = "Bem vindo à página inicial";
        $rootScope.nivelAlerta('info');
    } */

    if (!$rootScope.mensagem.endsWith('sucesso!')) {
        $rootScope.mensagem = "Bem vindo à página inicial";
    }
    $rootScope.nivelAlerta('info');
    $scope.pagina = pagina;
    $rootScope.defineAtivo(pagina);


});