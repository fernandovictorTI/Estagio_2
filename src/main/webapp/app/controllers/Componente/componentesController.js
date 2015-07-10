(function() {

	angular.module('app').controller('ComponentesController', ComponentesController);

	function ComponentesController($scope, $routeParams, $http, $location, $alert, AlertService) {
		
		$scope.Titulo1 = "Listar ";
		$scope.Titulo2 = "Componente";
		$scope.componentes = [];
		$scope.alert;
		
		$scope.editar = editar;
		$scope.listarComponentes = listarComponentes;
		$scope.excluir = excluir;
		
		function listarComponentes(){
			
			$http.get('ServletComponente').success(function(retorno) {
				$scope.componentes = retorno;
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				$scope.alert = $alert(AlertService.montarAlert($scope.Titulo, $scope.mensagem, 'danger'));
			});
		
		}
		
		listarComponentes();
		
		function excluir(id){
			$http.get('ServletComponente?id='+id+'&modo=4').success(function(retorno) {
				listarComponentes();
				$scope.alert = $alert(AlertService.montarAlert($scope.Titulo, 'excluido com sucesso', 'success'));
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				$scope.alert = $alert(AlertService.montarAlert($scope.Titulo, $scope.mensagem, 'danger'));
			});
		
		}
		
		function editar(id){
			$location.path("/componente/editar/"+id+"&2");
		}
	}

})();