var getComplaints = function ($http, self) {
    $http
        .get(
            globalUrl + 'complaint/read?pageSize=' + self.pageSize + '&pageNumber=' + self.pageNumber
        )
        .then(
            function successCallback(response) {
                console.log('complaints: ');
                console.log(response.data);
                return response.data;
            },
            function errorCallback(response) {
                console.log('read complaints error: ');
                console.log(response);
            }
        );
};

var getComplaintsCount = function ($http) {
    $http
        .get(
            globalUrl + 'complaint/count'
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