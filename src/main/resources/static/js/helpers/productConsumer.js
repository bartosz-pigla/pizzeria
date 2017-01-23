var setProducts=function ($http, $scope, pageSize) {
    $scope.pageSize=pageSize;

    $http
        .get(
            globalUrl +'product/read?pageSize='+$scope.pageSize+'&pageNumber='+$scope.pageNumber
        )
        .then(
            function successCallback(response) {
                console.log('setProducts:products');
                console.log(response.data);

                $scope.collection=response.data;


                $scope.pageCount=($scope.collection.length)/pageSize;

                var pages = [];
                for (var i = 0; i < $scope.pageCount; i++) {
                    pages.push(i + 1);
                }

                $scope.pages=pages;

                console.log('setProducts:pages');
                console.log($scope.pages);
            },
            function errorCallback(response) {
                console.log('setProducts:read products error: ');
                console.log(response);
            }
        );
};

// var setPageProperties=function ($http, $scope, pageSize) {
//     $http
//         .get(
//             globalUrl +'product/count'
//         )
//         .then(
//             function successCallback(response) {
//                 console.log('setPageProperties:product count');
//                 console.log(response.data);
//
//                 $scope.pageSize=pageSize;
//                 $scope.pageCount=(response.data)/pageSize;
//
//                 var pages = [];
//                 for (var i = 0; i < $scope.pageCount; i++) {
//                     pages.push(i + 1);
//                 }
//
//                 $scope.pages=pages;
//
//                 console.log('initialize method:pages');
//                 console.log($scope.pages);
//             },
//             function errorCallback(response) {
//                 console.log('setPageProperties:read count of product error: ');
//                 console.log(response);
//             }
//         );
// };