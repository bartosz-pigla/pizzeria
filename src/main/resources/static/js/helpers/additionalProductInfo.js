var getAllIngredients=function (self,$http) {
    $http.get(globalUrl +'ingredient/read/all').then(function (response) {
        self.allIngredients=[];
        for(var i=0;i<response.data.length;i++){
            self.allIngredients.push(response.data[i]);
        }
    });
};
var getAllSeasonings=function (self,$http) {
    $http.get(globalUrl +'seasoning/read/all').then(function (response) {
        self.allSeasonings=[];
        for(var i=0;i<response.data.length;i++){
            self.allSeasonings.push(response.data[i]);
        }
    });
};
var getAllRebates=function (self,$http) {
    $http.get(globalUrl +'rebate/read/all').then(function (response) {
        self.allRebates=[];
        for(var i=0;i<response.data.length;i++){
            self.allRebates.push(response.data[i]);
            console.log(self.allRebates);
        }
    });
};

function initializeCheckBoxes (self, $http) {
    self.allPizzaIngredients=getAllIngredients(self,$http);
    self.allSeasonings=getAllSeasonings(self,$http);
    self.allRebates=getAllRebates(self,$http);
}