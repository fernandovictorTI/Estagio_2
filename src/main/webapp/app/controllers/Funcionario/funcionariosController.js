(function() {

	angular.module('app').controller('FuncionariosController', FuncionariosController);

	function FuncionariosController($scope, $routeParams, $http, $location, $alert, AlertService) {
		
		$scope.Titulo1 = "Listar ";
		$scope.Titulo2 = "Funcionario";
		$scope.funcionarios = [];
		$scope.alert;
		
		$scope.editar = editar;
		$scope.listarFuncionarios = listarFuncionarios;
		$scope.inativar = inativar;
		
		function listarFuncionarios(){
			
			$http.get('ServletFuncionario').success(function(retorno) {
				$scope.funcionarios = retorno;
			}).error(function(msg) {
				console.log(msg);
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				var alert = $alert(AlertService.montarAlert($scope.mensagem, 'danger'));
			});
		
		}
		
		listarFuncionarios();
		
		function inativar(id){
			$http.post('ServletFuncionario?id='+id).success(function(retorno) {
				listarFuncionarios();
				var alert = $alert(AlertService.montarAlert('inativado com sucesso', 'success'));
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				var alert = $alert(AlertService.montarAlert($scope.mensagem, 'danger'));
			});
		
		}
		
		function editar(id){
			$location.path("/funcionario/editar/"+id+"&2");
		}
	}

})();