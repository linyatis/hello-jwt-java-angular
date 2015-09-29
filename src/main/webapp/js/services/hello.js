(function(){
	'use strict';
	
	angular.module('login').factory('helloService', helloService);
	
	helloService.$inject = ['$http'];
	
	function helloService($http) {
		var service = {
			get: getHello
		};
		
		return service;
		
		function getHello() {
			return $http.get("api/hello");
		}

	}
})();