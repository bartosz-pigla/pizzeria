angular.module('pizzaShopManagementApp')
    .controller('addProductController',
        function ($scope, $http) {
            var self = this;

            self.added = false;
            self.failed = false;

            self.addProduct = function () {
                var productType='';
                switch(self.selectedItem.type){
                    case 'pizza':
                        productType='pizza';
                        break;
                    case 'sos':
                        productType='sauce';
                        break;
                    case 'napoj':
                        productType='drink';
                        break;
                }
                $http
                    .post(
                        globalUrl + productType + '/create',
                        self.selectedItem
                    )
                    .then(
                        function successCallback(response) {
                            self.added = true;
                            self.failed = false;
                            self.serverValidationError=undefined;
                            newProduct(self);

                            //$scope.pizzaInfo.$setPristine();
                        },
                        function errorCallback(response) {
                            self.added = false;
                            self.failed = true;
                            console.log(response);
                            self.serverValidationError=response.data;
                        }
                    );
            };

            getAllIngredients($scope,$http);
            getAllRebates($scope,$http);
            getAllSeasonings($scope,$http);

            self.selectedItem={'ingredients':[],'rebates':[], 'seasonings':[]};


    });

var newProduct = function (self) {
    self.selectedItem = {
        type: 'pizza'
    };
};