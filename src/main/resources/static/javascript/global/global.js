angular.module('myApp').run(function($rootScope, $location) {

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

    $rootScope.regexTelefone = /^\d{10,11}$|^\(\d{2}\) \d{4}-\d{4}$|^\(\d{2}\) [9]\d{4}-\d{4}$/;

    $rootScope.contatoSelecionado = {};

    $rootScope.getContato = function() {
        return $rootScope.contatoSelecionado;
    }

    $rootScope.setContato = function(contato) {
        $rootScope.contatoSelecionado = contato;
    }

    $rootScope.redirect = function(pagina) {
        return $location.path("/".concat(pagina));
    }

    $rootScope.backToPreviousPage = function() {
        window.history.back();
    };

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
            $location.path("/home");
            $rootScope.nivelAlerta('info');
        } else {
            $rootScope.mensagem = 'Erro ao tentar realizar logout!';
            $rootScope.nivelAlerta('danger');
        }
    }

    $rootScope.adicionarMascara = function(objeto) {
        var objetoComMascara = '';
        if (objeto.length == 11) {
            objetoComMascara = '('.concat(objeto.substring(0, 2)).concat(')').concat(' ').concat(objeto.substring(2, 7)).concat('-').concat(objeto.substring(7, 11));
        } else {
            objetoComMascara = '('.concat(objeto.substring(0, 2)).concat(')').concat(' ').concat(objeto.substring(2, 6)).concat('-').concat(objeto.substring(6, 10));
        }
        return objetoComMascara;
    }

    $rootScope.removerMascara = function(objeto) {
        var objetoSemMascara = objeto.replace(/[^0-9]+/g, '');
        return objetoSemMascara;
    }

});