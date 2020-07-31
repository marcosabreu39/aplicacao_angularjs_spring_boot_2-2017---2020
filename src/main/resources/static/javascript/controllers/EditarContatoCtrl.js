/**
 * 
 */

angular.module('myApp').controller('EditarContatoCtrl', ['$scope', '$rootScope', 'pagina', 'ContatoService', function($scope, $rootScope, pagina, ContatoService) {

    $rootScope.mensagem = "Bem vindo à página de atualização de dados do contato";

    $scope.pagina = pagina;

    $rootScope.defineAtivo(pagina);

    $rootScope.nivelAlerta('info');

    $scope.contato = $rootScope.contatoSelecionado;

    $scope.updateContato = function(formName) {
        $scope.submitted = true;

        if (formName.$valid) {
            var telComMascara = $scope.contato.telefone;
            $scope.contato.telefone = $rootScope.removerMascara(telComMascara);
            ContatoService.atualizarContato($scope.contato, $rootScope.header)
                .then(function success(response) {
                        if (response.status == 200) {
                            $rootScope.mensagem = 'Contato atualizado com sucesso!';
                            $rootScope.nivelAlerta('success');
                            $scope.submitted = false;
                            var telSemMascara = $scope.contato.telefone;
                            $scope.contato.telefone = $rootScope.adicionarMascara(telSemMascara);;
                            $rootScope.setContato($scope.contato);
                            $rootScope.redirect('detalhesContato');
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

                            $rootScope.mensagem = 'Erro ao tentar atualizar o contato!'
                            $rootScope.nivelAlerta('danger');

                        } else if (response.status == 500) {
                            var errorMessageJson = this;
                            errorMessageJson = response.data;
                            $scope.errorMessage = angular.fromJson(errorMessageJson);

                            angular.forEach($scope.errorMessage, function(message, attribute) {

                                formName[attribute].$setValidity('errorServer', false);

                            });

                            $rootScope.mensagem = 'Erro ao tentar atualizar o contato!'
                            $rootScope.nivelAlerta('danger');

                        } else {

                            var retorno = response.data;
                            $rootScope.mensagem = 'Erro ao tentar atualizar o contato!!!';
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