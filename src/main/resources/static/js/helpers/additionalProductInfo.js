var getAllIngredients=function ($scope,$http) {
    $http.get(globalUrl +'ingredient/read/all').then(function (response) {
        $scope.allIngredients=[];
        for(var i=0;i<response.data.length;i++){
            $scope.allIngredients.push(response.data[i]);
        }
    });
};

var getAllSeasonings=function ($scope,$http) {
    $http.get(globalUrl +'seasoning/read/all').then(function (response) {
        $scope.allSeasonings=[];
        for(var i=0;i<response.data.length;i++){
            $scope.allSeasonings.push(response.data[i]);
        }
    });
};
var getAllRebates=function ($scope,$http) {
    $http.get(globalUrl +'rebate/read/all').then(function (response) {
        $scope.allRebates=[];
        for(var i=0;i<response.data.length;i++){
            $scope.allRebates.push(response.data[i]);
        }
    });
};

function initializeCheckBoxes (self, $http) {
    self.allPizzaIngredients=getAllIngredients(self,$http);
    self.allSeasonings=getAllSeasonings(self,$http);
    self.allRebates=getAllRebates(self,$http);
}