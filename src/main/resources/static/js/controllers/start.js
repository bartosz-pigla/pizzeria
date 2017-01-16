var pizzaShopManagementApp = angular.module('pizzaShopManagementApp',['ngRoute'])
    .config(
        function ($routeProvider, $httpProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'selectOption.html',
                    controller: 'selectOptionController',
                    controllerAs: 'controller'
                })
                .when('/addProduct', {
                    templateUrl: 'addProduct.html',
                    controller: 'addProductController',
                    controllerAs: 'controller'
                })
                .when('/editProduct', {
                    templateUrl: 'editProduct.html',
                    controller: 'editProductController',
                    controllerAs: 'controller'
                })
                .when('/deleteProduct', {
                    templateUrl: 'deleteProduct.html',
                    controller: 'deleteProductController',
                    controllerAs: 'controller'
                })
                .when('/readComplaint', {
                    templateUrl: 'readComplaint.html',
                    controller: 'readComplaintController',
                    controllerAs: 'controller'
                })
                .otherwise('/');

            $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        });
