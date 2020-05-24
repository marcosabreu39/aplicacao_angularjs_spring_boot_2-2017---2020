/**
 * 
 */
angular.module('myApp').controller('HomeCtrl', function($scope, $rootScope, pagina, UserService) {

    $scope.recuperarLogin = function() {
        if (!$rootScope.logado) {
            UserService.recuperarLogin()
                .then(function success(response) {
                        if (response.status == 200) {
                            var loginRecuperado = this;
                            loginRecuperadoJson = response.data;
                            var loginRecuperado = angular.fromJson(loginRecuperadoJson);
                            $rootScope.loginLogado = loginRecuperado.loginLogado;
                            $rootScope.logado = true;
                        }
                    },
                    function error(response) {
                        if (response.status == 404) {
                            var recuperado = this;
                            recuperado = response.data;
                        }

                    });
        }
    }

    $scope.recuperarLogin();

    if (!angular.equals($rootScope.mensagem, 'Usuário logado com sucesso!')) {
        $rootScope.mensagem = "Bem vindo à página inicial";
        $rootScope.nivelAlerta('info');
    }

    $scope.pagina = pagina;
    $rootScope.defineAtivo(pagina);


});