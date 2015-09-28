(function() {
	angular
	.module('login', ['ngMaterial', 'ui.router', 'angular-jwt' ])
		.config([ '$stateProvider', '$urlRouterProvider',
				function($stateProvider, $urlRouterProvider) {
						$stateProvider.state('index', {
							url : '/',
							templateUrl : 'login.html',
							controller : 'LoginCtrl'
						}).state('personal', {
							url : '/ola',
							templateUrl : 'personal.html',
							controller : 'PersonalController'
						}).state('sair', {
							url : '/out',
							templateUrl : 'out.html'
						});
						$urlRouterProvider.otherwise('/');
				}])
				.run(['$http' , '$rootScope' ,'jwtHelper',
					      function ($http ,$rootScope, jwtHelper) {
				    var token = localStorage.getItem('auth');
				    $rootScope.$on("$locationChangeStart",
				      function (event, next, current){
				      if(token){
				        $rootScope.user = jwtHelper.decodeToken(token);
				        $http.defaults.headers.common.Authorization = token;
				      }
				    });
				}]);
})();