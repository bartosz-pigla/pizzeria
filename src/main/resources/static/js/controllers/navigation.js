angular.module('pizzaShopManagementApp')
    .controller('navigationController',
        function ($rootScope, $http, $location, $route, $scope, $window, AuthenticationService) {
            var self=this;

            self.tab = function (route) {
                // console.log("ROUTE CHANGE");
                // console.log(route);
                return $route.current && route === $route.current.controller;
            };

            AuthenticationService.authenticate();

            self.credentials = {};
            //$scope.error = false;

            self.login = function () {
                AuthenticationService.login(self.credentials);

                if($rootScope.authenticated)
                    $window.sessionStorage.authenticated=true;

                console.log('LOGIN: #WINDOW.AUTHENTICATED');
                console.log($window.authenticated);



                console.log('LOGIN RESULT');
                console.log($rootScope.error);

                self.user=$window.sessionStorage.user;
            };

            self.logout = function () {
                AuthenticationService.logout();
                $window.sessionStorage.authenticated=false;
            };

        });