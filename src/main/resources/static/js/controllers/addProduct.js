angular.module('pizzaShopManagementApp')
    .controller('addProductController',
        function ($scope, $http) {
            var self = this;

            self.added = false;
            self.failed = false;

            self.addProduct = function () {
                $http
                    .post(
                        globalUrl + 'pizza/create',
                        self.selectedItem
                    )
                    .then(
                        function successCallback(response) {
                            self.added = true;
                            self.failed = false;
                        },
                        function errorCallback(response) {
                            self.added = false;
                            self.failed = false;
                            console.log(response);
                        }
                    );

                newProduct(self);
            };

            getAllIngredients(self,$http);
            getAllIngredients(self,$http);
            getAllRebates(self,$http);

            //self.toggle = toggleDialog;
        })
;

var newProduct = function (self) {
    self.selectedItem = {
        type: 'pizza'
    };
};