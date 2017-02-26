var pizzaShopManagementApp = angular.module('pizzaShopManagementApp', ['ngRoute', 'checklist-model'])
    .config(
        function ($routeProvider, $httpProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'selectOption.html',
                    controller: 'selectOptionController',
                    controllerAs: 'controller',
                    requiresLogin: false
                })
                .when('/addProduct', {
                    templateUrl: 'addProduct.html',
                    controller: 'addProductController',
                    controllerAs: 'controller',
                    requiresLogin: true
                })
                .when('/editProduct', {
                    templateUrl: 'editProduct.html',
                    controller: 'editProductController',
                    controllerAs: 'controller',
                    requiresLogin: true
                })
                .when('/deleteProduct', {
                    templateUrl: 'deleteProduct.html',
                    controller: 'deleteProductController',
                    controllerAs: 'controller',
                    requiresLogin: true
                })
                .when('/readComplaint', {
                    templateUrl: 'readComplaint.html',
                    controller: 'readComplaintController',
                    controllerAs: 'controller',
                    requiresLogin: true
                })
                .when('/login', {
                    templateUrl: 'login.html',
                    controller: 'navigationController',
                    controllerAs: 'controller',
                    requiresLogin: false
                })
                .when('/register', {
                    templateUrl: 'registration.html',
                    controller: 'registrationController',
                    controllerAs: 'controller',
                    requiresLogin: false
                })
                .when('/activate/:id/:number', {
                    templateUrl: 'activation.html',
                    controller: 'activationController',
                    controllerAs: 'controller',
                    requiresLogin: false
                })
                .otherwise('/');

            $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        })
    .run(['$rootScope', 'LocationHistoryService', '$location', '$window', function ($rootScope, LocationHistoryService, $location, $window) {
        console.log('RUN: #WINDOW.AUTHENTICATED');
        console.log($window.sessionStorage.authenticated);

        if($window.sessionStorage.authenticated)
            $rootScope.authenticated=true;

        $rootScope.$on("$locationChangeStart", function (event, next, current) {
            console.log('LOCATION CHANGE START');

            console.log('current location: ');
            console.log(current);

            console.log('next location: ');
            console.log(next);

            LocationHistoryService.setLast(current, next);
            console.log('current location from LocationHistoryService: ');
            console.log(LocationHistoryService.getLastRouteBeforeLastLogin());

            console.log('END OF LOCATION CHANGE START');
        });

        $rootScope.$on('$routeChangeStart', function (e, curr, prev) {
            console.log("ROUTE CHANGE START");

            console.log('current route: ');
            console.log(curr);

            console.log('previous route: ');
            console.log(prev);

            //console.log(curr.requiresLogin);

            console.log('AUTHENTICATED: ');
            console.log($rootScope.authenticated);

            if($rootScope.authenticated==undefined){
                $rootScope.authenticated=$window.authenticated;
            }

            if (curr.requiresLogin && !$rootScope.authenticated) {
                LocationHistoryService.setRedirectionToLoginPageFlag();
                console.log('prevent');
                $location.path("/login");
                //event.preventDefault();
            }
            console.log('END OF ROUTE CHANGE START');
        });
    }])
    .service('LocationHistoryService', function () {
        var location = {};

        var flag=false;

        this.getLastRouteBeforeLastLogin = function () {
            return location;
        };
        this.setLast = function (current, next) {
            if(!flag){
                var loginPos = next.indexOf('login');

                if (loginPos != -1)
                    location = current;
                else if (loginPos == -1 && next.split('http').length == 2)
                    location = next;
            }
            else
                flag=false;
        };
        this.setRedirectionToLoginPageFlag = function () {
            flag=true;
        }
    })
    .service('AuthenticationService', ['$rootScope', '$http', '$location', '$window', 'LocationHistoryService', function ($rootScope, $http, $location, $window, LocationHistoryService) {
        this.authenticate = function (credentials, callback) {
            var headers = credentials ? {
                    authorization: "Basic "
                    + btoa(credentials.username + ":"
                        + credentials.password)
                } : {};

            $http.get('/user', {
                headers: headers
            }).then(function (response) {
                $http.get('/user').then(function (response) {
                    console.log('AUTH USER DATA');
                    console.log(response);
                    $rootScope.user = response.data;
                });

                console.log(response);

                console.log('AUTHENTICATE');
                console.log(response.data);

                if (response.data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback($rootScope.authenticated);
            }, function () {
                $rootScope.authenticated = false;
                callback && callback(false);
            });

            $http.get(globalUrl + '/user').then(function (response) {
                console.log('USER NAME AJAX');

                console.log(response);
                $window.sessionStorage.user = response.data;

                console.log("USERNAME");
                console.log($window.sessionStorage.user);

                console.log('END OF USER NAME AJAX');
                //$scope.user2=response.data;
            });
        };

        this.login = function (credentials) {
            this.authenticate(credentials, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded");
                    //$location.path("/");
                    console.log("----------------");
                    console.log(LocationHistoryService.getLastRouteBeforeLastLogin());

                    $window.location.href = LocationHistoryService.getLastRouteBeforeLastLogin();

                    //$location.path(LocationHistoryService.getLastRouteBeforeLastLogin());
                    $rootScope.error = false;
                    $rootScope.authenticated = true;

                    $window.authenticated=true;
                } else {
                    console.log("LOGIN FAILED");



                    $location.path("/login");
                    $rootScope.error = true;
                    $rootScope.authenticated = false;
                }

            })
        };

        this.logout = function () {
            $http.post('logout', {}).finally(function () {
                $rootScope.authenticated = false;
                //$location.path("/");

                var location=LocationHistoryService.getLastRouteBeforeLastLogin();

                $window.location.href = globalUrl;

                // console.log('LOCATION:');
                //
                // console.log(location);
                //
                // if(location == undefined){
                //     console.log('LOCATION UNDEFINED');
                //     location.path("/");
                // }
                // else{
                //     console.log('LOCATION: REDIRECT');
                //     $window.location.href = location;
                // }
            });
        }
    }])
    .directive('imageUrl', function () {
        return {
            require: 'ngModel',
            link: function (scope, elm, attrs, ctrl) {
                ctrl.$validators.imageUrl = function (modelValue, viewValue) {
                    var acceptedFiletypes = ['jpg', 'png', 'jpeg'];
                    var lastDot = null;

                    if (modelValue == null || (lastDot = modelValue.lastIndexOf('.')) == -1) {
                        return false;
                    }

                    var fileType = modelValue.substr(lastDot + 1, modelValue.length - 1);
                    var accepted = acceptedFiletypes.indexOf(fileType) != -1;
                    return accepted;
                }
            }
        }
    });