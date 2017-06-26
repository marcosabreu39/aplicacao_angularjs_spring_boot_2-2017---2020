/**
 * 
 */
angular.module('myApp').controller('HomeCtrl', function($scope, pagina) {
    $scope.mensagem = "Bem vindo à agenda com Angularjs, Spring e Hibernate!";
    $scope.pagina = pagina;
});