angular.module('pizzaShopManagementApp')
    .controller('editProductController',
        function ($http) {
            var self = this;

            self.edited = false;
            self.operation = 'edit';
            self.toggleDialog = toggleDialog;

            initializeCheckBoxes(self, $http);

            initializeItemsSelection(
                self,
                getProductsCount($http),
                3,
                getProducts,
                $http
            );

            self.editProduct = function () {
                var url;
                switch (self.selectedItem.type) {
                    case 'pizza':
                        url = globalUrl + 'pizza/update';
                    case 'sos':
                        url = globalUrl + 'sauce/update';
                    case 'drink':
                        url = globalUrl + 'drink/update';
                }

                $http
                    .put(
                        url, self.selectedItem
                    )
                    .then(
                        function successCallback(response) {
                            self.collection[self.selectedIdx] = self.selectedItem;
                            self.edited = true;
                            self.failed = false;
                        },
                        function errorCallback(response) {
                            self.edited = false;
                            self.failed = false;
                            console.log(response);
                        }
                    );

                self.selectedIdx = -1;
            };

            console.log(self.selectedIdx);
            console.log(self.selectedItem);
        });


