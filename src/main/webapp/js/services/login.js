(function() {
	'use strict';
	angular.module('login').factory('logar', logar);

	logar.$inject = [ '$http' ];

	function logar($http) {

		var service = {
			post : post,
			get : get
		};

		return service;
		
		function post(body) {
			return $http.post('/simple-java-angular-jwt/api/login', JSON.stringify(body));
		}
		
		function get(url) {
			return $http.get(url);
		}

	}
})();