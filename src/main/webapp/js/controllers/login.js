(function() {
	'use strict';
	angular.module('login').controller('LoginCtrl', LoginCtrl);

	LoginCtrl.$inject = [ 'authenticationService', 'storage', '$state',
			'$rootScope', 'jwtHelper' ];

	function LoginCtrl(authenticationService, storage, $state, $rootScope,
			jwtHelper) {
		var vm = this;

		vm.user = {};

		vm.authorized = storage.get() !== null ? true : false;

		vm.auth = function() {
			authenticationService.login(vm.user).then(function(res) {
				if (res.data) {
					storage.put(res.data.token);
					$rootScope.user = jwtHelper.decodeToken(res.data.token);
					vm.authorized = true;
					location.reload();
				}

			}, function err(data) {
				vm.msg = data.statusText;
			});

		}

		vm.sair = function() {
			storage.get() ? storage.out() : $log.debug("não devita te isso");
			$rootScope.user = null;
			location.reload();
		}
	}

})();