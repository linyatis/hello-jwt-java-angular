(function() {
	'use strict';
	angular.module('login').factory('storage', storage);

	storage.$inject = [ '$window' ];

	function storage($window) {

		var service = {
			put : put,
			get : get,
			out : out
		};

		return service;

		function put(body) {
			$window.localStorage.setItem("auth", body);
		}

		function get() {
			return $window.localStorage.getItem("auth");
		}

		function out() {
			$window.localStorage.removeItem("auth");
		}

	}
})();