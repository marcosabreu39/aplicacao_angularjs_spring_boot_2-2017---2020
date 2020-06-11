/**
 * 
 */

angular.module('myApp').controller('CadUserCtrl', ['$scope', '$rootScope', '$filter', 'pagina', 'UserService', function($scope, $rootScope, $filter, pagina, UserService) {

    $rootScope.mensagem = "Bem vindo à página de cadastro de usuários";

    $scope.pagina = pagina;

    $rootScope.defineAtivo(pagina);

    $rootScope.nivelAlerta('info');

    $scope.submitted = false;

    $scope.errorMessage = [];

    $scope.saveUser = function(formName) {
        $scope.submitted = true;

        if (formName.$valid) {
            $scope.usuario.dataCadastro = $filter('date')(new Date(), 'dd/MM/yyyy HH:mm:ss');

            UserService.saveUser($scope.usuario)
                .then(function success(response) {
                        if (response.status == 201) {
                            $rootScope.mensagem = 'Usuário cadastrado com sucesso!';
                            $rootScope.nivelAlerta('success');
                            $scope.submitted = false;
                            $scope.usuario = null;
                        }
                    },
                    function error(response) {
                        if (response.status == 409) {
                            var errorMessageJson = this;
                            errorMessageJson = response.data;
                            $scope.errorMessage = angular.fromJson(errorMessageJson);

                            angular.forEach($scope.errorMessage, function(message, attribute) {

                                formName[attribute].$setValidity('errorServer', false);

                            });

                            $rootScope.mensagem = 'Erro ao tentar cadastrar o usuário!'
                            $rootScope.nivelAlerta('danger');

                        } else {

                            var retorno = response.data;
                            $rootScope.mensagem = 'Erro ao tentar cadastrar o usuário!!';
                            $rootScope.nivelAlerta('danger');
                        }

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