var pizzaShopManagementApp = angular.module('pizzaShopManagementApp',['ngRoute','checklist-model'])
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
pizzaShopManagementApp.directive('imageUrl',function () {
    return{
        require:'ngModel',
        link:function (scope, elm, attrs, ctrl) {
            ctrl.$validators.imageUrl=function (modelValue,viewValue) {
                var acceptedFiletypes=['jpg','png','jpeg'];
                var lastDot=null;

                if(modelValue==null || (lastDot=modelValue.lastIndexOf('.'))==-1){
                    return false;
                }

                var fileType=modelValue.substr(lastDot+1,modelValue.length-1);
                var accepted= acceptedFiletypes.indexOf(fileType)!=-1;
                return accepted;
            }
        }
    }
});