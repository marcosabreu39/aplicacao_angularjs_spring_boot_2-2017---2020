/**
 * 
 */
angular.module('myApp', ['ngRoute', 'ngMessages']);

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

    $routeProvider.otherwise({
        redirectTo: '/home'
    });

});