(function() {
	'use strict';
	angular.module('login').controller('HelloCtrl', HelloCtrl);

	HelloCtrl.$inject = ['helloService', '$mdDialog'];

	function HelloCtrl(helloService, $mdDialog) {
		var vm = this;
		vm.msg = null;
		vm.ola = function(ev) {
			helloService.get()
				.then(function(result) {
						$mdDialog.show(
							$mdDialog.alert()
						        .title('Acesso autorizado')
						        .content(result.data.msg)
						        .ok("Ok")
						        .targetEvent(ev)
						    );
						vm.img = img();
					})
				.catch(function(result) {
					$mdDialog.show(
						$mdDialog.alert()
					        .title('Ops! Sem autorização... =(')
					        .content(result.data.msg)
					        .ok("Ok")
					        .targetEvent(ev)
					    );
					vm.img = null;
				});
		};
		function img(){
			return 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSKtI0nP3nJI9i4isJBUVTqOuGdOgbh7xRYVHesh2iEux8lCmRA';
		}
	}

})();