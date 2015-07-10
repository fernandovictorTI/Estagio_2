(function() {

	angular.module('app')
			.controller('PrincipalController', PrincipalController);

	function PrincipalController($scope, $http) {

		$scope.Principal = [];

		$scope.carregarParametros = carregarParametros;

		function carregarParametros() {
			var request = $http
					.get('ServelterPrincipal')
					.success(function(retorno) {
						$scope.Principal = retorno;
					})
					.error(
							function(msg) {
								$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
							});
		}

		carregarParametros();

	}

})();