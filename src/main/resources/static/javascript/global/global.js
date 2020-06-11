angular.module('myApp').run(function($rootScope, $location, UserService) {

    $rootScope.token = '';

    $rootScope.header = {};

    $rootScope.loginLogado = '';

    $rootScope.logado = false;

    $rootScope.mensagem = '';

    $rootScope.alertClass = '';

    $rootScope.classHome = 'nav-item';

    $rootScope.classLogin = 'nav-item';

    $rootScope.classCadastro = 'nav-item';

    $rootScope.classDados = 'nav-item dropdown ml-auto';

    $rootScope.classContatos = 'nav-item dropdown'

    $rootScope.nivelAlerta = function(nivel) {
        $rootScope.alertClass = 'alert alert-' + nivel;
    }

    var setClassDefault = function() {
        $rootScope.classHome = 'nav-item';
        $rootScope.classLogin = 'nav-item';
        $rootScope.classCadastro = 'nav-item';
        $rootScope.classDados = 'nav-item dropdown ml-auto';
        $rootScope.classContatos = 'nav-item dropdown'
    }

    $rootScope.defineAtivo = function(pagina) {
        switch (pagina) {
            case 'contatos':
                setClassDefault();
                $rootScope.classContatos = 'nav-item dropdown  active'
                break;

            case 'dados':
                setClassDefault();
                $rootScope.classDados = 'nav-item dropdown ml-auto active';
                break;

            case 'login':
                setClassDefault();
                $rootScope.classLogin = 'nav-item active';
                break;

            case 'cadastro':
                setClassDefault();
                $rootScope.classCadastro = 'nav-item active';
                break;

            default:
                setClassDefault();
                $rootScope.classHome = 'nav-item active';
        }
    }

    $rootScope.resetServerValidation = function(formName, attribute) {
        formName[attribute].$setValidity('errorServer', true);
    }

    $rootScope.logout = function(login) {
        var login = login;
        if (angular.equals(login, $rootScope.loginLogado)) {
            $rootScope.loginLogado = '';
            $rootScope.logado = false;
            $rootScope.token = '';
            $rootScope.mensagem = 'Logout realizado com sucesso!';
            $location("/home");
            $rootScope.nivelAlerta('info');
        } else {
            $rootScope.mensagem = 'Erro ao tentar realizar logout!';
            $rootScope.nivelAlerta('danger');
        }
    }



});