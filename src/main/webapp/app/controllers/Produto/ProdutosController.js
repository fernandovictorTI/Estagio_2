(function() {

	angular.module('app').controller('ProdutosController', ProdutosController);

	function ProdutosController($scope, $routeParams, $http, $location) {
		$scope.Titulo1 = "Listar ";
		$scope.Titulo2 = "Produtos";
		$scope.produtos = [];
		
		$scope.editar = editar;
		$scope.listarProdutos = listarProdutos;
		$scope.excluir = excluir;
		
		function listarProdutos(){
			
			$http.get('ServletProduto').success(function(retorno) {
				$scope.produtos = retorno;
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				console.log(msg);
			});
		
		}
		
		listarProdutos();
		
		function excluir(id){

			$http.get('ServletProduto?id='+id+'&modo=4').success(function(retorno) {
				listarProdutos();
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				console.log(msg);
			});
		
		}
		
		function editar(id){
			$location.path("/produto/editar/"+id+"&2");
		}
	}

})();