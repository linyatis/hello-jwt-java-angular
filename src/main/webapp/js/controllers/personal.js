(function() {
	'use strict';
	angular.module('login').controller('PersonalController', PersonalController);

	PersonalController.$inject = [ '$rootScope'];

	function PersonalController($rootScope) {
		var vm =  this;
		vm.user = $rootScope.user.user;
	}
})();