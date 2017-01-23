angular.module('pizzaShopManagementApp')
    .controller('deleteProductController',
        function ($scope,$http) {
            var self = this;

            self.deleted = false;
            self.operation = 'delete';
            self.toggleDialog = toggleDialog;

            initializeItemsSelection(
                self,
                setPageProperties($http),
                3,
                setProducts,
                $http
            );

            self.deleteProduct = function () {
                $http
                    .delete(
                        globalUrl + 'product/delete/' + self.selectedItem.id
                    )
                    .then(
                        function successCallback(response) {
                            self.collection.remove(self.selectedIdx);
                            self.deleted = true;
                            self.failed = false;
                            self.selectedIdx = -1;
                        },
                        function errorCallback(response) {
                            self.deleted = false;
                            self.failed = false;
                            console.log(response);
                            self.selectedIdx = -1;
                        }
                    );
            };

            console.log(self.selectedIdx);
            console.log(self.selectedItem);
        });
