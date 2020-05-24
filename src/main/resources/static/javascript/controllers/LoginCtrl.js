/**
 * 
 */

angular.module('myApp').controller('LoginCtrl', ['$scope', '$rootScope', '$location', 'pagina', 'UserService', 'CryptoService', function($scope, $rootScope, $location, pagina, UserService, CryptoService) {

    $rootScope.mensagem = "Bem vindo à página de login de usuários";

    $scope.pagina = pagina;

    $rootScope.defineAtivo(pagina);

    $rootScope.nivelAlerta('info');

    $scope.submitted = false;

    $scope.errorMessage = [];

    $scope.login = function(formName) {
        $scope.submitted = true;

        if (formName.$valid) {
            $scope.usuario.senha = CryptoService.cfCryptoHttpInterceptor.plugin.encode($scope.usuario.senha, cfCryptoHttpInterceptor.base64Key);
            UserService.login($scope.usuario)
                .then(function success(response) {
                        if (response.status == 200) {
                            var token = this;
                            token = response.headers('Authorization');
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
                        if (response.status == 403) {
                            var errorMessageJson = this;
                            errorMessageJson = response.data;
                            $scope.errorMessage = angular.fromJson(errorMessageJson);

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