angular.module('pizzaShopManagementApp')
    .controller('deleteProductController',
        function ($scope,$http) {
            var self = this;

            self.deleted = false;
            self.failed = false;

            $scope.operation = 'delete';
            $scope.productType="product";

            $scope.toggleDialog = function(id){
                console.log('id');
                self.selectedIdx=id;

                console.log(self.selectedItem.id);
                toggleDialogView();
            };

            //initializeCheckBoxes(self, $http);

            $scope.pageNumber=1;
            $scope.selectedIdx=-1;

            $scope.filter={};
            //$scope.productType='product';

            $scope.getCollection = function (pageNumber) {

                $http
                    .post(
                        globalUrl + $scope.productType + '/count',
                        $scope.filter
                    )
                    .then(
                        function (response) {
                            $scope.productCount=response.data;
                            $scope.pageCount=($scope.productCount)/3;

                            var pages = [];
                            for (var i = 0; i < $scope.pageCount; i++) {
                                pages.push(i + 1);
                            }

                            $scope.pages=pages;

                            console.log('setProducts:pages');
                            console.log($scope.pages);
                        },
                        function (response) {

                        }
                    );

                console.log("getCollection:productType");
                console.log($scope.productType);

                $scope.pageNumber=pageNumber;
                $scope.collection=setProducts($http, $scope,3);

                console.log('getCollection method:collection');
                console.log($scope.collection);
            };

            $scope.changeProductType=function(productType){

                $scope.productType=productType;

                console.log('--------------');
                console.log($scope.productType);
            };

            $scope.openItem= function (itemIndex) {

                $scope.selectedIdx=itemIndex;

                console.log('openItem:idx');
                console.log($scope.selectedIdx);

                if($scope.selectedIdx!=-1)
                    self.selectedItem=$scope.collection[$scope.selectedIdx];
            };

            $scope.getCollection(1);

            setFilter($scope,$http);

            // getAllIngredients($scope,$http);
            // getAllRebates($scope,$http);
            // getAllSeasonings($scope,$http);

            self.selectedItem={'ingredients':[],'rebates':[], 'seasonings':[]};

            $scope.deleteProduct = function () {
                console.log('delete');
                console.log('selectedItem');
                console.log($scope.collection);
                console.log($scope.selectedIdx);
                $http
                    .delete(
                        globalUrl + 'product/delete/' + $scope.collection[self.selectedIdx].productId
                    )
                    .then(
                        function successCallback(response) {
                            console.log('success');
                            //self.collection.remove(self.selectedIdx);
                            // console.log($scope.collection);
                            // $scope.collection.splice(self.selectedIdx,1);
                            //
                            //

                            $scope.getCollection($scope.pageNumber);

                            self.deleted = true;
                            self.failed = false;
                            self.selectedIdx = -1;
                        },
                        function errorCallback(response) {
                            console.log('fail');
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
