(function() {

	angular.module('app').controller('ProdutoComponenteController',ProdutoComponenteController);

	function ProdutoController($scope, $routeParams, $http){
		
		// Propriedades da pagina

		$scope.Titulo = "Cadastrar ";
		$scope.Titulo2 = "Produto";

		$scope.IsDisabled = false;

		$scope.cadastrar = cadastrar;		
		$scope.limparCampos = limparCampos;
		$scope.configurarTela = configurarTela;
		$scope.bloquearCampos = bloquearCampos;
		$scope.Modo = "";

		if($routeParams.modo !== undefined && $routeParams.modo !== null)
			$scope.Modo = $routeParams.modo; // 1 = Cadastrar, 2 = Editar e 3 = Vizualizar

		// Objeto

		$scope.Produto = {};
		$scope.Produto.id = 0;
		$scope.Produto.nomeProduto = "";
		$scope.Produto.preco = "";

		if($routeParams.id !== undefined && $routeParams.id !== null)
			$scope.Produto.Id = $routeParams.id;

		configurarTela($scope.Modo);

		function cadastrar(){
			
			$.ajax({
		        url: "ServletProduto",
		        type: 'POST',
		        dataType: 'json',
		        data: JSON.stringify($scope.Produto),
		        contentType: 'application/json',
		        mimeType: 'application/json',
		 
		        success: function () {
		        	alert("Success: ");
		        },
		        error:function() {
		            alert("error: ");
		        }
		    });
			
			$scope.limparCampos();
			$scope.configurarTela(1);
		}

		function limparCampos(){
			$scope.Produto = {};
		}

		function bloquearCampos(){
			$scope.IsDisabled = true;
		}

		function configurarTela(modo){
			switch(modo){
				case "1":
				$scope.Titulo = "Cadastrar ";
				break;

				case "2":
				$scope.Titulo = "Editar ";
				break;

				case "3":
				$scope.Titulo = "Vizualizar ";
				$scope.bloquearCampos();
				break;
			}
			
		}

	}

})();