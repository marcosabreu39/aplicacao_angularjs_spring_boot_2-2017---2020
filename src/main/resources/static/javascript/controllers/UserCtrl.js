/**
 * 
 */

angular.module('myApp').controller('UserCtrl', ['$scope', '$rootScope', 'pagina', 'UserService', function($scope, $rootScope, pagina, UserService) {

    $rootScope.mensagem = "Bem vindo à página de cadastro de usuários";

    $scope.pagina = pagina;

    $rootScope.defineAtivo(pagina);

    $rootScope.nivelAlerta('info');

    $scope.submitted = false;

    $scope.errorMessage = [];

    $scope.saveUser = function(formName) {
        $scope.submitted = true;

        if (formName.$valid) {

            UserService.saveUser($scope.usuario)
                .then(function success(response) {
                        $rootScope.mensagem = 'Usuário cadastrado com sucesso!';
                        $rootScope.nivelAlerta('success');
                        $scope.usuario = null;
                        $scope.submitted = false;

                    },
                    function error(response) {
                        if (response.status == 409) {
                            var errorMessageJson = this;
                            errorMessageJson = response.data;
                            $scope.errorMessage = angular.fromJson(errorMessageJson);

                            angular.forEach($scope.errorMessage, function(message, attribute) {

                                formName[attribute].$setValidity('errorServer', false);

                            });

                            $rootScope.mensagem = 'Erro ao tentar adicionar o usuário!'
                            $rootScope.nivelAlerta('danger');

                        } else {

                            $rootScope.mensagem = 'Erro ao tentar adicionar o usuário!!';
                            $rootScope.nivelAlerta('danger');
                        }
                        /*$scope.mensagem = '';*/

                    });

        } else {
            if (formName['nome'].$invalid) {
                formName['nome'].$setDirty(true);
            }
            if (formName['email'].$invalid) {
                formName['email'].$setDirty(true);
            }
            if (formName['login'].$invalid) {
                formName['login'].$setDirty(true);
            }
            if (formName['senha'].$invalid) {
                formName['senha'].$setDirty(true);
            }
        }

    }






}]);