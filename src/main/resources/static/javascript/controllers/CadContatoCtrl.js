/**
 * 
 */

angular.module('myApp').controller('CadContatoCtrl', ['$scope', '$rootScope', 'pagina', 'ContatoService', function($scope, $rootScope, pagina, ContatoService) {

    $rootScope.mensagem = "Bem vindo à página de cadastro de contatos";

    $scope.pagina = pagina;

    $rootScope.defineAtivo(pagina);

    $rootScope.nivelAlerta('info');

    $scope.submitted = false;

    $scope.errorMessage = [];

    $scope.saveContato = function(formName) {
        $scope.submitted = true;

        if (formName.$valid) {
            var c = $scope.contato.telefone;
            if (c != null && !angular.equals(c, '')) {
                $scope.contato.telefone = $rootScope.removerMascara(c);
            }
            ContatoService.saveContato($scope.contato, $rootScope.loginLogado, $rootScope.header)
                .then(function success(response) {
                        if (response.status == 200) {
                            $rootScope.mensagem = 'Contato cadastrado!';
                            $rootScope.nivelAlerta('success');
                            $scope.submitted = false;
                            $scope.contato = null;
                        }
                    },
                    function error(response) {
                        if (response.status == 400) {
                            var retornoJson = this;
                            retornoJson = response.data;
                            var errors = angular.fromJson(retornoJson.errors);
                            var jsonErrors = '';

                            for (var i = 0; i < errors.length; i++) {
                                formName[errors[i].field].$setValidity('errorServer', false);
                                if (i == 0) {
                                    jsonErrors = '{';
                                }
                                var body = '\"' + errors[i].field + '\"' + ':  ' + '\"' + errors[i].defaultMessage + '\"';
                                jsonErrors = jsonErrors + body;
                                if (i == errors.length - 1) {
                                    var end = '}';
                                    jsonErrors = jsonErrors + end;
                                    $scope.errorMessage = angular.fromJson(jsonErrors);
                                } else {
                                    var limit = ',';
                                    jsonErrors = jsonErrors + limit;
                                }
                            }

                            $rootScope.mensagem = 'Erro ao tentar cadastrar o contato!'
                            $rootScope.nivelAlerta('danger');

                        } else if (response.status == 500) {

                            $rootScope.mensagem = 'Erro ao tentar cadastrar o contato!!';
                            $rootScope.nivelAlerta('danger');

                        } else {

                            /* var retorno = response.data; */
                            $rootScope.mensagem = 'Erro ao tentar cadastrar o contato!!';
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
            if (formName['telefone'].$invalid) {
                formName['telefone'].$setDirty(true);
            }
            if (formName['endereco'].$invalid) {
                formName['endereco'].$setDirty(true);
            }
            if (formName['observacao'].$invalid) {
                formName['observacao'].$setDirty(true);
            }
        }

    }

}]);