(function() {
	'use strict';
	angular.module('login').controller('HelloCtrl', HelloCtrl);

	HelloCtrl.$inject = [ 'logar' ];

	function HelloCtrl(logar) {
		var vm = this;
		vm.msg = null;
		vm.ola = function() {
			logar.get('/simple-java-angular-jwt/api/hello').then(
					function(data) {

					}, function(data) {
						vm.msg = data.statusText;

					});
		};

	}

})();