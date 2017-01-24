var setFilter = function ($scope,$http) {
    setPizzaFilter($scope,$http);
    setDrinkFilter($scope,$http);
    setSauceFilter($scope,$http);
};

var setPizzaFilter = function ($scope,$http) {
    $http.get(globalUrl +'filter/pizza').then(function (response) {
        $scope.allRebates=response.data.rebates;
        $scope.allIngredients=response.data.ingredients;
        $scope.allDoughTypes=response.data.doughTypes;
        $scope.allPizzaSizes=response.data.pizzaSizes;

        //$scope.filter=response.data;

        console.log('setPizzaFilter:');
        console.log(response.data);
    });
};

var setDrinkFilter = function ($scope,$http) {
    $http.get(globalUrl +'filter/drink').then(function (response) {
        $scope.allRebates=response.data.rebates;
        $scope.allLiterCounts=response.data.literCounts;

        //$scope.filter=response.data;

        console.log('setDrinkFilter:');
        console.log(response.data);
    });
};

var setSauceFilter = function ($scope,$http) {
    $http.get(globalUrl +'filter/sauce').then(function (response) {
        $scope.allRebates=response.data.rebates;
        $scope.allSeasonings=response.data.seasonings;

        //$scope.filter=response.data;

        console.log('setSauceFilter:');
        console.log(response.data);
    });
};