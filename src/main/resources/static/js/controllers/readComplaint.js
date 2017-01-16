angular.module('pizzaShopManagementApp')
    .controller('readComplaintController',
        function ($http) {
            var self = this;

            initializeItemsSelection(
                self,
                getComplaintsCount($http),
                3,
                getComplaints,
                $http
            );

        });