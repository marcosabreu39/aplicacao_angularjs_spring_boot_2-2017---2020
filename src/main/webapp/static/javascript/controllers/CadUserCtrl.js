/**
 * 
 */
	
angular.module('myApp').controller('CadUserCtrl', ['$scope', 'pagina','UserService', function($scope, pagina, UserService){
	$scope.mensagem = "Página de cadastro de usuários";
    $scope.pagina = pagina;
    
    $scope.submitted = false;
    
    /*$scope.getUser = function() {
           UserService.getUser().then(function(data) {
               $scope.usuario = data;
           });
       }*/
     
    $scope.saveUser = function() {
        $scope.submitted = true;
                
          if ($scope.formCadUser.$valid) { 	  
        	  
            UserService.saveUser($scope.usuario)
              .then (function success(response) {
                  $scope.mensagem = 'Usuário cadastrado com sucesso!';
                  /*$scope.mensagem = '';*/
                  /*$scope.getUser();*/
                  $scope.usuario = null;
                  $scope.submitted = false;     
                  
              },
              function error(response) {
                  if (response.status == 409) {   
                	var errorMessageJson = this;  
                	errorMessageJson = response.data;
                    $scope.errorMessage = angular.fromJson(errorMessageJson);
                    
                    /*$scope.formCadUser.login.$setValidity('errorServer', false);*/
                    
                    angular.forEach($scope.errorMessage, function(message, attribute) {
                    	
                    	$scope.formCadUser[attribute].$setValidity('errorServer', false);
                    	
                    });                               
                                                                                
                    $scope.mensagem = 'Erro ao tentar adicionar o usuário!'
                  }                                   
                  
                  else {             	  
                	                  	  
                    $scope.mensagem = 'Erro ao tentar adicionar o usuário!!';
                  }
                  /*$scope.mensagem = '';*/
                                    
            });              
            
          }
    }
    
   /*$scope.getUser();*/    
	
}]);