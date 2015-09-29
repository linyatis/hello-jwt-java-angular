(function() {
	'use strict';

	angular.module('login').config(routesConfig);

	routesConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

	function routesConfig($stateProvider, $urlRouterProvider) {
		$stateProvider.state('index', {
			url : '/',
			templateUrl : 'login.html',
			controller : 'LoginCtrl'
		});

		$urlRouterProvider.otherwise('/');
	}
})();