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
        controller: 'UserCtrl',
        resolve: {
            pagina: function() {
                return 'cadastro';
            }
        }
    });

    $routeProvider.otherwise({
        redirectTo: '/home'
    });



});