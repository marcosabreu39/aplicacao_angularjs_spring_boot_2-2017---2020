/**
 * 
 */
angular.module('myApp', ['ngRoute', 'ngMessages', 'ngMask']);

angular.module('myApp').config(function($routeProvider, $locationProvider) {

    $locationProvider.hashPrefix('');

    $routeProvider.when('/home', {
        templateUrl: 'partials/public/home.html',
        controller: 'HomeCtrl',
        resolve: {
            pagina: function() {
                return 'home';
            }
        }
    });

    $routeProvider.when('/cadastro', {
        templateUrl: 'partials/public/cadastro-user.html',
        controller: 'CadUserCtrl',
        resolve: {
            pagina: function() {
                return 'cadastro';
            }
        }
    });

    $routeProvider.when('/login', {
        templateUrl: 'partials/public/login.html',
        controller: 'LoginCtrl',
        resolve: {
            pagina: function() {
                return 'login';
            }
        }
    });

    $routeProvider.when('/dados', {
        templateUrl: 'partials/private/dados-user.html',
        controller: 'DadosUserCtrl',
        resolve: {
            pagina: function() {
                return 'dados';
            }
        }
    });

    $routeProvider.when('/novoContato', {
        templateUrl: 'partials/private/cadastro-contato.html',
        controller: 'CadContatoCtrl',
        resolve: {
            pagina: function() {
                return 'contatos';
            }
        }
    });

    $routeProvider.when('/visualizarContatos', {
        templateUrl: 'partials/private/visualizar-contatos.html',
        controller: 'VisualizarContatosCtrl',
        resolve: {
            pagina: function() {
                return 'contatos';
            }
        }
    });

    $routeProvider.when('/detalhesContato', {
        templateUrl: 'partials/private/detalhes-contato.html',
        controller: 'DetalhesContatoCtrl',
        resolve: {
            pagina: function() {
                return 'contatos';
            }
        }
    });

    $routeProvider.when('/editarContato', {
        templateUrl: 'partials/private/editar-contato.html',
        controller: 'EditarContatoCtrl',
        resolve: {
            pagina: function() {
                return 'contatos';
            }
        }
    });

    $routeProvider.otherwise({
        redirectTo: '/home'
    });

});