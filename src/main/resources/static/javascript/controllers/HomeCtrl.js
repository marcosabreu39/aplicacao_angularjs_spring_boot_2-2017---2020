/**
 * 
 */
angular.module('myApp').controller('HomeCtrl', function($scope, $rootScope, pagina, UserService) {


    if (!angular.equals($rootScope.mensagem, 'Usuário logado com sucesso!')) {
        $rootScope.mensagem = "Bem vindo à página inicial";
        $rootScope.nivelAlerta('info');
    }

    $scope.pagina = pagina;
    $rootScope.defineAtivo(pagina);


});