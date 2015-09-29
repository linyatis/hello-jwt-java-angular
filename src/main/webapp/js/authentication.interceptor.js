(function() {
	'use strict';

	angular.module('login').run(authenticationInterceptor);

	authenticationInterceptor.$inject = [ '$http', '$rootScope', 'jwtHelper' ];

	function authenticationInterceptor($http, $rootScope, jwtHelper) {
		var token = localStorage.getItem('auth');
		$rootScope.$on("$locationChangeStart", function(event, next, current) {
			if (token) {
				$rootScope.user = jwtHelper.decodeToken(token);
				$http.defaults.headers.common.Authorization = token;
			}
		});
	}

})();