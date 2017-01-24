angular.module('pizzaShopManagementApp')
    .controller('editProductController',
        function ($scope,$http) {
            var self = this;

            self.edited = false;
            self.failed = false;

            $scope.operation = 'edit';
            $scope.productType="product";

            $scope.toggleDialog = toggleDialogView;

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
                            $scope.pageCount=(response.data)/3;

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


            //setPageProperties($http,$scope,3);

            // initializeItemsSelection(
            //     $scope,
            //     $http,
            //     setProducts
            // );

            //init2($http,$scope);

            // $http
            //     .get(
            //         globalUrl +'product/read?pageSize='+3+'&pageNumber='+1
            //     )
            //     .then(
            //         function successCallback(response) {
            //             console.log('products: ');
            //             console.log(response.data);
            //             $scope.collection=response.data;
            //         },
            //         function errorCallback(response) {
            //             console.log('read products error: ');
            //             console.log(response);
            //         }
            //     );
            //
            // $scope.selectedIdx=-1;

            self.editProduct = function () {
                console.log('edit product');
                console.log(self.selectedItem);

                var url;
                switch (self.selectedItem.type) {
                    case 'pizza':
                        url = globalUrl + 'pizza/update';
                        break;
                    case 'sos':
                        url = globalUrl + 'sauce/update';
                        break;
                    case 'napoj':
                        url = globalUrl + 'drink/update';
                        break;
                }

                $http
                    .put(
                        url, self.selectedItem
                    )
                    .then(
                        function successCallback(response) {
                            console.log('success:');
                            console.log(self.selectedItem);
                            console.log($scope.selectedIdx);
                            console.log($scope.collection);
                            $scope.collection[$scope.selectedIdx] = self.selectedItem;
                            self.edited = true;
                            self.failed = false;
                            self.serverValidationError=undefined;
                            $scope.selectedIdx = -1;
                        },
                        function errorCallback(response) {
                            self.edited = false;
                            self.failed = false;
                            self.serverValidationError=response.data;
                            console.log(response);
                        }
                    );

            };

            console.log($scope.selectedIdx);
            console.log($scope.selectedItem);
        });


