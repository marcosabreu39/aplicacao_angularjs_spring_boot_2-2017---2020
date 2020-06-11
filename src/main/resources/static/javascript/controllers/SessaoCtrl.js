angular.module('myApp').controller('SessaoCtrl', ['$scope', '$location', 'UserService', function($scope, $location, UserService) {

    $scope.atualizarSessao = function() {
        UserService.atualizarSessao().then(function success(respose) {
                if (respose.status == 200) {
                    $location.path("/");
                }
            },
            function error(response) {
                $location.path("/");
            });
    }

    // $scope.atualizarSessao();

}]);