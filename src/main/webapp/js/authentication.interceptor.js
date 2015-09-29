(function() {
	'use strict';

	angular.module('login').run(authenticationInterceptor);

	authenticationInterceptor.$inject = [ '$http', '$rootScope', 'jwtHelper', 'storage' ];

	function authenticationInterceptor($http, $rootScope, jwtHelper, storage) {
		var token = storage.get();
		$rootScope.$on("$locationChangeStart", function(event, next, current) {
			if (token) {
				$rootScope.user = jwtHelper.decodeToken(token);
				$http.defaults.headers.common.Authorization = token;
			}
		});
	}

})();