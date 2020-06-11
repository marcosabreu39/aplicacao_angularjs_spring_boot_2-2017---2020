/**
 * 
 */

angular.module('myApp').controller('DadosUserCtrl', ['$scope', '$rootScope', '$filter', 'pagina', 'UserService', function($scope, $rootScope, $filter, pagina, UserService) {

    $rootScope.mensagem = "Bem vindo à página de dados cadastrais do usuário";

    $scope.pagina = pagina;

    $rootScope.defineAtivo(pagina);

    $rootScope.nivelAlerta('info');

    $scope.submitted = false;

    $scope.errorMessage = [];

    var usuarioParaObter = { 'login': $rootScope.loginLogado };

    var senhaBanco = '';

    $scope.ativo = true;

    $scope.inativo = false;

    $scope.classVisualizar = 'btn btn-secondary custom-radio active';

    $scope.classAtualizar = 'btn btn-secondary custom-radio';

    $scope.defineInputAtivo = function(input) {
        if (angular.equals(input, "visualizar")) {
            $scope.ativo = true;
            $scope.inativo = false;
            $scope.classVisualizar = 'btn btn-secondary custom-radio active';
            $scope.classAtualizar = 'btn btn-secondary custom-radio';
        }

        if (angular.equals(input, "atualizar")) {
            $scope.ativo = false;
            $scope.inativo = true;
            $scope.classVisualizar = 'btn btn-secondary custom-radio';
            $scope.classAtualizar = 'btn btn-secondary custom-radio active';
        }
    }

    $scope.resetSenha = function() {
        $scope.usuario.senha = '';
    }

    $scope.obterDadosUser = function(usuario, header) {

        UserService.obterDadosUser(usuario, header).then(function success(response) {
                if (response.status == 200) {
                    var usuarioJson = this;
                    usuarioJson = response.data;
                    $scope.usuario = angular.fromJson(usuarioJson);
                    var data = $scope.usuario.dataCadastro;
                    $scope.usuario.dataCadastro = $filter('date')(new Date(data), 'dd/MM/yyyy HH:mm:ss');
                    senhaBanco = $scope.usuario.senha;
                }
            },
            function error(response) {
                if (response.status == 401) {
                    $rootScope.mensagem = 'Erro ao tentar obter os dados do usuário!'
                    $rootScope.nivelAlerta('danger');
                } else if (response.status == 403) {
                    $rootScope.mensagem = 'Erro ao tentar obter os dados do usuário!'
                    $rootScope.nivelAlerta('danger');
                }
            });
    }


    $scope.obterDadosUser(usuarioParaObter, $rootScope.header);

    $scope.atualizarUser = function(formName) {
        $scope.submitted = true;

        if (formName.$valid) {

            UserService.atualizarUser($scope.usuario, senhaBanco, $rootScope.header)
                .then(function success(response) {
                        if (response.status == 200) {
                            $rootScope.mensagem = 'Usuário atualizado com sucesso!';
                            $rootScope.nivelAlerta('success');
                            $scope.submitted = false;
                            $scope.defineInputAtivo('visualizar');
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

                            $rootScope.mensagem = 'Erro ao tentar atualizar o usuário!'
                            $rootScope.nivelAlerta('danger');

                        } else {

                            $rootScope.mensagem = 'Erro ao tentar atualizar o usuário!!';
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