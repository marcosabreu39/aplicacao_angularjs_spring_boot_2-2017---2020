/**
 * 
 */

angular.module('myApp').controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'pagina', 'UserService', function($scope, $rootScope, $location, pagina, UserService) {

    $rootScope.mensagem = "Bem vindo à página de login de usuários";

    $scope.pagina = pagina;

    $rootScope.defineAtivo(pagina);

    $rootScope.nivelAlerta('info');

    $scope.submitted = false;

    $scope.errorMessage = [];

    $scope.login = function(formName) {
        $scope.submitted = true;

        if (formName.$valid) {
            UserService.login($scope.usuario)
                .then(function success(response) {
                        if (response.status == 200) {
                            $rootScope.token = response.headers('Authorization');
                            $rootScope.header = { 'Authorization': $rootScope.token };
                            $rootScope.nivelAlerta('success');
                            $rootScope.loginLogado = $scope.usuario.login;
                            $scope.usuario = null;
                            $scope.submitted = false;
                            $rootScope.logado = true;
                            $location.path("/home");
                            $rootScope.mensagem = 'Usuário logado com sucesso!';
                        }
                    },
                    function error(response) {
                        if (response.status == 401) {
                            var mensagem = { "login": "Usuário ou(e) senha incorretos!", "senha": "Usuário ou(e) senha incorretos!" }
                            $scope.errorMessage = mensagem;

                            angular.forEach($scope.errorMessage, function(message, attribute) {

                                formName[attribute].$setValidity('errorServer', false);

                            });

                            $rootScope.mensagem = 'Erro no login do usuário!'
                            $rootScope.nivelAlerta('danger');

                        } else if (response.status == 403) {
                            var mensagem = { "login": "Usuário ou(e) senha inválidos!", "senha": "Usuário ou(e) senha inválidos" }
                            $scope.errorMessage = mensagem;

                            angular.forEach($scope.errorMessage, function(message, attribute) {

                                formName[attribute].$setValidity('errorServer', false);

                            });

                            $rootScope.mensagem = 'Erro no login do usuário!'
                            $rootScope.nivelAlerta('danger');

                        } else {

                            $rootScope.mensagem = 'Erro no login do usuário!!';
                            $rootScope.nivelAlerta('danger');
                        }

                    });

        } else {
            if (formName['login'].$invalid) {
                formName['login'].$setDirty(true);
            }
            if (formName['senha'].$invalid) {
                formName['senha'].$setDirty(true);
            }
        }

    }

}]);