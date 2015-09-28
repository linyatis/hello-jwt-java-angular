(function() {
	'use strict';
	angular.module('login').controller('LoginCtrl', LoginCtrl);

	LoginCtrl.$inject = [ 'logar', 'storage', '$log', '$state', '$rootScope' ];

	function LoginCtrl(logar, storage, $log, $state, $rootScope) {
		var vm = this;
		vm.authorized = storage.get() !== null ? true : false;

		vm.auth = function() {
			logar.post(vm.user).then(function(res) {
				if (res.data) {
					storage.put(res.data);
					$rootScope.user = res.data;
					vm.authorized = true;
					$state.go("personal");
				} else {
					$log.debug(err);
				}
			});

		}

		vm.sair = function() {
			storage.get() ? storage.out() : $log.debug("não devita te isso");
			$rootScope.user = null;
			$state.go("sair");
		}
	}

})();