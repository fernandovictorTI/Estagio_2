(function() {

	angular.module('app').controller('FuncionariosController', FuncionariosController);

	function FuncionariosController($scope, $routeParams, $http, $location, $alert, AlertService) {
		
		$scope.Titulo1 = "Listar ";
		$scope.Titulo2 = "Funcionario";
		$scope.funcionarios = [];
		$scope.alert;
		
		$scope.editar = editar;
		$scope.listarFuncionarios = listarFuncionarios;
		$scope.excluir = excluir;
		
		function listarFuncionarios(){
			
			$http.get('ServletFuncionario').success(function(retorno) {
				$scope.funcionarios = retorno;
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				var alert = $alert(AlertService.montarAlert($scope.mensagem, 'danger'));
				alert.show();
			});
		
		}
		
		listarFuncionarios();
		
		function excluir(id){
			$http.get('ServletFuncionario?id='+id+'&modo=4').success(function(retorno) {
				listarFuncionarios();
				var alert = $alert(AlertService.montarAlert('excluido com sucesso', 'success'));
				alert.show();
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				var alert = $alert(AlertService.montarAlert($scope.mensagem, 'danger'));
				alert.show();
			});
		
		}
		
		function editar(id){
			$location.path("/funcionario/editar/"+id+"&2");
		}
	}

})();