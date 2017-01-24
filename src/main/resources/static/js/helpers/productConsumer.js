var setProducts=function ($http, $scope, pageSize) {
    $scope.pageSize=pageSize;

    console.log("xyz");
    console.log($scope.xyz);

    console.log("productType");
    console.log($scope.productType);

    console.log("setProducts:filter");
    console.log($scope.filter);

    var successCallback=function (response) {
        console.log('setProducts:products');
        console.log(response.data);

        $scope.collection=response.data;


    };

    var errorCallback=function (response) {
        console.log('setProducts:read products error: ');
        console.log(response);
    };

    if($scope.filter==null){
        console.log("get");
        $http
            .get(
                globalUrl + $scope.productType + '/read?pageSize='+$scope.pageSize+'&pageNumber='+$scope.pageNumber
            )
            .then(
                successCallback,
                errorCallback
            );
    }
    else{
        console.log("post");
        $http
            .post(
                globalUrl + $scope.productType + '/read?pageSize='+$scope.pageSize+'&pageNumber='+$scope.pageNumber,
                $scope.filter
            )
            .then(
                successCallback,
                errorCallback
            );
    }
};





var setProducts2=function ($http, $scope, pageSize) {
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
