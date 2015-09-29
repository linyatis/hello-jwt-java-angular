(function() {
	'use strict';
	angular.module('login').factory('authenticationService', authenticationService);

	authenticationService.$inject = [ '$http' ];

	function authenticationService($http) {

		var service = {
			login : login
		};

		return service;
		
		function login(data) {
			return $http.post('/simple-java-angular-jwt/api/login', data);
		}

	}
})();