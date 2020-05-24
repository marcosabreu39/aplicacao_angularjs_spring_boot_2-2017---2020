angular.module('myApp').run(function($rootScope, $location, UserService) {

    $rootScope.usuario = { login: '' };
    $rootScope.loginLogado = '';
    $rootScope.logado = false;
    $rootScope.classHome = '';
    $rootScope.classCadastro = '';
    $rootScope.classLogin = '';
    $rootScope.classContatos = '';
    $rootScope.classLogado = '';
    $rootScope.mensagem = '';
    $rootScope.alertClass = '';

    $rootScope.nivelAlerta = function(nivel) {
        $rootScope.alertClass = 'alert alert-' + nivel;
    }

    $rootScope.defineAtivo = function(pagina) {
        switch (pagina) {
            case 'contatos':
                $rootScope.classLogado = 'nav-item dropdown ml-auto';
                $rootScope.classLogin = 'nav-item';
                $rootScope.classHome = 'nav-item';
                $rootScope.classCadastro = 'nav-item';
                $rootScope.classContatos = 'nav-item dropdown  active'
                break;
            case 'cadastro':
                $rootScope.classLogado = 'nav-item dropdown ml-auto';
                $rootScope.classContatos = 'nav-item dropdown'
                $rootScope.classLogin = 'nav-item';
                $rootScope.classHome = 'nav-item';
                $rootScope.classCadastro = 'nav-item active';
                break;
            case 'login':
                $rootScope.classLogado = 'nav-item dropdown ml-auto';
                $rootScope.classContatos = 'nav-item dropdown'
                $rootScope.classHome = 'nav-item';
                $rootScope.classCadastro = 'nav-item';
                $rootScope.classLogin = 'nav-item active';
                break;
            case 'logado':
                $rootScope.classLogado = 'nav-item dropdown ml-auto';
                $rootScope.classContatos = 'nav-item dropdown'
                $rootScope.classHome = 'nav-item';
                $rootScope.classCadastro = 'nav-item';
                $rootScope.classLogin = 'nav-item';
                $rootScope.classLogado = 'nav-item dropdown ml-auto active';
                break;
            default:
                $rootScope.classLogado = 'nav-item dropdown ml-auto';
                $rootScope.classContatos = 'nav-item dropdown'
                $rootScope.classLogin = 'nav-item';
                $rootScope.classCadastro = 'nav-item';
                $rootScope.classHome = 'nav-item active';
        }
    }

    $rootScope.resetServerValidation = function(formName, attribute) {
        formName[attribute].$setValidity('errorServer', true);
    }

    $rootScope.logout = function(login) {
        $rootScope.usuario.login = login;
        UserService.logout($rootScope.usuario).then(function success(response) {
                $rootScope.loginLogado = '';
                // $rootScope.usuario = null;
                $rootScope.logado = false;
                $rootScope.mensagem = 'Logout realizado com sucesso';
                $rootScope.nivelAlerta('warning');
                // $location.path("/home");
            },
            function error(response) {
                if (response.status == 409) {
                    $rootScope.loginLogado = '';
                    $rootScope.usuario = null;
                    $rootScope.logado = false;
                    $rootScope.mensagem = 'Logout realizado, sessão finalizada';
                    $rootScope.nivelAlerta('warning');
                    $location.path("/");
                }
            });
    }

    /* $rootScope.usuarioExpirado = function() {
        UserService.usuarioExpirado().then(function success(response) {
                if (response.status == 200) {
                    var varJson = this;
                    var logado = angular.fromJson(varJson);
                    if (!logado) {
                        $rootScope.logado = logado;
                        $rootScope.loginLogado = '';
                        $rootScope.usuario = null;
                        $rootScope.mensagem = '';
                        $location.path("/");
                    }
                }
            },
            function error() {
                $rootScope.mensagem = 'Erro para realizar o logout!';
                $rootScope.nivelAlerta('danger');
            });
    } */


});