//take data from server
app.controller('CityCtrl', [ '$scope', function($scope) {
	   $scope.cities = [{
		   'name' : 'Ivanov',
		   'district' : '',
	       'region' : 'Ivanov'
	   },
	   {
		   'name' : 'Pushkino',
		   'district' : 'Pushkino',
	       'region' : 'Moscow'
	   }];
   }
]);
