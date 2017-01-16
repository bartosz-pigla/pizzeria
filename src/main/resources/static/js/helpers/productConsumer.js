var getProducts=function ($http, self) {
    $http
        .get(
            globalUrl +'product/read?pageSize='+self.pageSize+'&pageNumber='+self.pageNumber
        )
        .then(
            function successCallback(response) {
                console.log('products: ');
                console.log(response.data);
                return response.data;
            },
            function errorCallback(response) {
                console.log('read products error: ');
                console.log(response);
            }
        );
};

var getProductsCount=function ($http) {
    $http
        .get(
            globalUrl +'product/count'
        )
        .then(
            function successCallback(response) {
                console.log('product count');
                console.log(response.data);
                return response.data;
            },
            function errorCallback(response) {
                console.log('read count of product error: ');
                console.log(response);
            }
        );
};