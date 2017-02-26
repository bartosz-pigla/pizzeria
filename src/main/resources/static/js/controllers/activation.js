angular.module('pizzaShopManagementApp')
    .controller('activationController',
        function ($scope, $http, $routeParams, $timeout, $location) {
            var self = this;

            self.activated=false;

            $http
                .post(
                    globalUrl + '/activate',
                    {
                        id:$routeParams.id,
                        number:$routeParams.number
                    }
                )
                .then(
                    function successCallback(response) {
                        console.log('REGISTRATION SUCCESS');

                        self.activated = true;
                    },
                    function errorCallback(response) {
                        console.log('REGISTRATION ERROR');

                        self.activated = false;
                    }
                );

            $timeout(function () {
                $location.path("/");
            }, 3000);
        });