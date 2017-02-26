angular.module('pizzaShopManagementApp')
    .controller('registrationController',
        function ($scope, $http) {
            var self = this;

            self.registered = false;
            self.failed = false;

            self.backEndValidationError={eMail:''};

            self.manager={
                eMail:'',
                password:'',
                passwordConfirmation:''
            };

            self.register=function () {
                console.log("MANAGER TO REGISTER:");
                console.log(self.manager);

                $http
                    .post(
                        globalUrl + '/register',
                        self.manager
                    )
                    .then(
                        function successCallback(response) {
                            console.log('REGISTRATION SUCCESS');

                            self.registered = true;
                            self.failed = false;
                            self.serverValidationError={};
                        },
                        function errorCallback(response) {
                            console.log('REGISTRATION ERROR');

                            for(var i=0;i<response.data.length;i++){
                                console.log('LOOP');
                                console.log(response.data[i]);

                                if(response.data[i].fieldName == 'eMail')
                                    self.backEndValidationError.eMail=true;
                            }

                            self.registered = false;
                            self.failed = true;
                            console.log(response);
                            self.serverValidationError=response.data;
                        }
                    );
            }
        });