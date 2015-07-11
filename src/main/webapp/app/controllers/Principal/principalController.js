(function() {

	angular.module('app')
			.controller('PrincipalController', PrincipalController);

	function PrincipalController($scope, $http) {

		$scope.Principal = [];
		$scope.componentes = [];

		$scope.carregarParametros = carregarParametros;
		$scope.carregarRelatorio = carregarRelatorio;

		function carregarParametros() {
			var request = $http
					.get('ServelterPrincipal?tipo=principal')
					.success(function(retorno) {
						$scope.Principal = retorno;
					})
					.error(
							function(msg) {
								$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
							});
		}

		carregarParametros();
		
		function carregarRelatorio() {
			var request = $http
					.get('ServelterPrincipal?tipo=relatorio')
					.success(function(retorno) {
						$scope.componentes = retorno;
						console.log($scope.componentes);
					})
					.error(
							function(msg) {
								$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
							});
		}

		carregarRelatorio();

	}

})();