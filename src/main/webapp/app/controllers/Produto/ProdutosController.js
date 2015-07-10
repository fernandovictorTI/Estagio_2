(function() {

	angular.module('app').controller('ProdutosController', ProdutosController);

	function ProdutosController($scope, $routeParams, $http, $location, AlertService, $alert, $templateCache) {
		
		$scope.Titulo1 = "Listar ";
		$scope.Titulo2 = "Produto";
		$scope.produtos = [];
		
		$scope.editar = editar;
		$scope.listarProdutos = listarProdutos;
		$scope.excluir = excluir;
		$scope.adicionarComponente = adicionarComponente; 
		
		function listarProdutos(){
			
			$http.get('ServletProduto').success(function(retorno) {
				$scope.produtos = retorno;
			}).error(function(msg) {
				$scope.alert = $alert(AlertService.montarAlert($scope.Titulo2, 'Houve um problema ao acessar o serviço. Tente mais tarde.', 'danger'));
			});
		
		}
		
		listarProdutos();
		
		function excluir(id){
			$http.get('ServletProduto?id='+id+'&modo=4').success(function(retorno) {
				listarProdutos();
				$scope.alert = $alert(AlertService.montarAlert($scope.Titulo2, 'Excluido com sucesso.', 'success'));
			}).error(function(msg) {
				$scope.alert = $alert(AlertService.montarAlert($scope.Titulo2, 'Houve um problema ao acessar o serviço. Tente mais tarde.', 'danger'));
			});
		}
		
		function editar(id){
			$location.path("/produto/editar/"+id+"&2");
		}
		
		function adicionarComponente(id){
			$location.path("/produto/adicionarComponente/"+id);
		}
	}

})();