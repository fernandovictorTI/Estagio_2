(function() {

	angular.module('app').controller('ProdutosController', ProdutosController);

	function ProdutosController($scope, $routeParams, $http, $location, $alert) {
		
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
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				var alert = $alert(montarAlert($scope.mensagem, 'danger'));
				alert.show();
			});
		
		}
		
		listarProdutos();
		
		function excluir(id){
			$http.get('ServletProduto?id='+id+'&modo=4').success(function(retorno) {
				listarProdutos();
				var alert = $alert(montarAlert('excluido com sucesso', 'success'));
				alert.show();
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
				var alert = $alert(montarAlert($scope.mensagem, 'danger'));
				alert.show();
			});
		
		}
		
		function editar(id){
			$location.path("/produto/editar/"+id+"&2");
		}
		
		function adicionarComponente(id){
			$location.path("/produto/adicionarComponente/"+id);
		}
		
		function montarAlert(contentMsg, typeMsg){
			return {
				title : $scope.Titulo2,
				content : contentMsg,
				placement: 'top',
				type : typeMsg, 
				container : "main"
			}; 
		}
	}

})();