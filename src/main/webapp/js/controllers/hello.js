(function() {
	'use strict';
	angular.module('login').controller('HelloCtrl', HelloCtrl);

	HelloCtrl.$inject = [ 'logar', '$mdDialog' ];

	function HelloCtrl(logar , $mdDialog) {
		var vm = this;
		vm.msg = null;
		vm.ola = function(ev) {
			logar.get('/simple-java-angular-jwt/api/hello').then(
					function(data) {
						$mdDialog.show(
								$mdDialog.alert()
							        .parent(angular.element(document.querySelector('#popupContainer')))
							        .clickOutsideToClose(true)
							        .title('Bem Vindo Você esta logado"')
							        .content("Se você esta vendo essa mensagem ok vc esta logado")
							        .ariaLabel('If i got this menssagem, Ok you are loged')
							        .ok(data.statusText)
							        .targetEvent(ev)
							    );
						vm.img = img();
					}, function(data) {
						$mdDialog.show(
								$mdDialog.alert()
							        .parent(angular.element(document.querySelector('#popupContainer')))
							        .clickOutsideToClose(true)
							        .title('Existe algo errando em seu login')
							        .content("Para logar configa seus dados")
							        .ariaLabel('Any is missing, try check your creditals')
							        .ok(data.statusText)
							        .targetEvent(ev)
							    );
					});
		};
		function img(){
			return 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSKtI0nP3nJI9i4isJBUVTqOuGdOgbh7xRYVHesh2iEux8lCmRA';
		}
	}

})();