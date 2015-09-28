(function() {
	'use strict';
	angular.module('login').controller('LoginCtrl', LoginCtrl);

	LoginCtrl.$inject = [ 'logar', 'storage', '$log', '$state', '$rootScope','jwtHelper'];

	function LoginCtrl(logar, storage, $log, $state, $rootScope, jwtHelper) {
		var vm = this;
		vm.authorized = storage.get() !== null ? true : false;

		vm.auth = function() {
			logar.post(vm.user).then(function(res) {
				if (res.data) {
					storage.put(res.data);
					$rootScope.user = jwtHelper.decodeToken(res.data);
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