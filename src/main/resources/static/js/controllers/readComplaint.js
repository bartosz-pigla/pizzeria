angular.module('pizzaShopManagementApp')
    .controller('readComplaintController',
        function ($scope,$http) {
            var self = this;

            $scope.pageNumber=1;
            $scope.selectedIdx=-1;

            $scope.setComplaints=function ($http, $scope, pageSize) {

                $http
                    .get(
                        globalUrl + 'complaint/read?pageSize='+pageSize+'&pageNumber='+$scope.pageNumber
                    )
                    .then(
                        function (response) {
                            console.log('setComplaints:complaints');
                            console.log(response.data);

                            $scope.collection=response.data;


                        },
                        function (response) {
                            console.log('setComplaints:read complaints error: ');
                            console.log(response);
                        }
                    );
            };

            $scope.getCollection = function (pageNumber) {

                $http
                    .post(
                        globalUrl + 'complaint/count'
                    )
                    .then(
                        function (response) {
                            $scope.pageCount=(response.data)/10;

                            var pages = [];
                            for (var i = 0; i < $scope.pageCount; i++) {
                                pages.push(i + 1);
                            }

                            $scope.pages=pages;

                            console.log('setComplaintss:pages');
                            console.log($scope.pages);
                        },
                        function (response) {

                        }
                    );

                $scope.pageNumber=pageNumber;
                $scope.collection=$scope.setComplaints($http, $scope,3);

                console.log('getCollection method:collection');
                console.log($scope.collection);
            };

            $scope.openItem= function (itemIndex) {

                $scope.selectedIdx=itemIndex;

                console.log('openItem:idx');
                console.log($scope.selectedIdx);

                if($scope.selectedIdx!=-1)
                    $scope.selectedItem=$scope.collection[$scope.selectedIdx];
            };

            $scope.getCollection(1);
        });