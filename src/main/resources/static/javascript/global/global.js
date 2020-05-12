angular.module('myApp').run(function($rootScope) {

    $rootScope.classHome = '';

    $rootScope.classCadastro = '';

    $rootScope.mensagem = '';

    $rootScope.alertClass = '';

    $rootScope.nivelAlerta = function(nivel) {
        $rootScope.alertClass = 'alert alert-' + nivel;
    }

    $rootScope.defineAtivo = function(pagina) {
        if (pagina == 'home') {
            $rootScope.classCadastro = 'nav-item';
            $rootScope.classHome = 'nav-item active';
        } else if (pagina == 'cadastro') {
            $rootScope.classHome = 'nav-item';
            $rootScope.classCadastro = 'nav-item active';
        }
    }


});