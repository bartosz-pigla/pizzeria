function initializeItemsSelection($scope, $http, collectionSetter){
    // $scope.selectedIdx=-1;
    //
    // console.log('initializing item selection');
    // console.log($scope.selectedIdx);
    //
    // $scope.getCollection = function (pageNumber) {
    //     $scope.pageNumber=pageNumber;
    //     $scope.collection=collectionSetter($http, $scope);
    //
    //     console.log('initialize method:collection');
    //     console.log($scope.collection);
    // };
    //
    // $scope.getCollection(1);
    //
    // $scope.openItem= function (itemIndex) {
    //     $scope.selectedIdx=itemIndex;
    //     $scope.selectedItem=$scope.collection[$scope.selectedIdx];
    // };

}
//
// function init2($http,$scope) {
//     $http
//         .get(
//             globalUrl +'product/read?pageSize='+3+'&pageNumber='+1
//         )
//         .then(
//             function successCallback(response) {
//                 console.log('products: ');
//                 console.log(response.data);
//                 $scope.collection=response.data;
//             },
//             function errorCallback(response) {
//                 console.log('read products error: ');
//                 console.log(response);
//             }
//         );
//
//     $scope.selectedIdx=-1;
// }