/**
 * 
 */
angular.module('myApp').service('UserService',['$http', function ($http) {
     
    this.saveUser = function saveUser(usuario){
        return $http({
          method: 'POST',
          url: 'usuario',
          params: {nome:usuario.nome ,email:usuario.email,login:usuario.login , senha:usuario.senha},
          headers: 'Accept:application/json'
        });
    }
     
    /*this.getUser = function getUser(){
        return $http({
          method: 'POST',
          url: 'usuario',
          headers:'Accept:application/json'
        }).then( function(response){
            return response.data;
        } );
    }*/
 
}]);