/**
 * 
 */
angular.module('myApp',['ngRoute']);

angular.module('myApp').config(function($routeProvider, $locationProvider) {
	
	$locationProvider.hashPrefix('');
	
	$routeProvider
	.when('/home', {
        templateUrl : 'static/partials/public/home.html',
        controller : 'HomeCtrl',
        resolve : {
        	pagina : function() {
        		return 'home';
        	}
        }        
    });
	
	$routeProvider
	.when('/cadastro', {
        templateUrl : 'static/partials/public/cadastro-user.html',
        controller : 'CadUserCtrl',
        resolve : {
        	pagina : function() {
        		return 'cadastro';
        	}
        }        
    });
        
	$routeProvider.otherwise({redirectTo : '/home'});
					
});




