(function() {

	angular.module('app').controller('ProdutoComponenteController',ProdutoComponenteController);

	function ProdutoComponenteController(AlertService, $scope, $routeParams, $http, $alert, $location, $timeout){
		
		// Propriedades da pagina

		$scope.Titulo = "Cadastrar ";
		$scope.Titulo2 = "Componentes do Produto";

		$scope.IsDisabled = false;

		$scope.cadastrar = cadastrar;		
		$scope.limparCampos = limparCampos;
		$scope.configurarTela = configurarTela;
		$scope.bloquearCampos = bloquearCampos;
		$scope.adicionarComponente = adicionarComponente;
		$scope.removerComponente = removerComponente;
		$scope.Modo = "";

		if($routeParams.modo !== undefined && $routeParams.modo !== null)
			$scope.Modo = $routeParams.modo; // 1 = Cadastrar, 2 = Editar e 3 = Vizualizar

		// Objeto

		$scope.Produto = {};
		$scope.Produto.id = 0;
		$scope.Produto.nomeProduto = "";
		$scope.Produto.preco = "";
		$scope.Produto.componentes = [];
		$scope.quantidadeNecessaria = 0;
		
		$scope.componentes = [];

		if($routeParams.id !== undefined && $routeParams.id !== null)
			$scope.Produto.id = $routeParams.id;
		
		if($scope.Produto.id > 0){
			var request = $http.get('ServletProduto?id='+$scope.Produto.id).success(function(retorno) {
				$scope.Produto = retorno;
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
			});
			
			$http.get('ServletProdutoComponente').success(function(retorno) {
				$scope.componentes = retorno;
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
			});
        }

		configurarTela($scope.Modo);

		function cadastrar() {
			
			$http.post('ServletProduto', JSON.stringify($scope.Produto)).
			  success(function(data, status, headers, config) {
				  $scope.alert = $alert(AlertService.montarAlert($scope.Titulo2 ,'cadastrado com sucesso', 'success'));
			  }).
			  error(function(data, status, headers, config) {
				  $scope.alert = $alert(AlertService.montarAlert($scope.Titulo2, 'Houve um problema ao acessar o serviço. Tente mais tarde', 'danger'));
			  });			

			$location.path("/produto/listar");
			
		}
		
		function adicionarComponente(){
			if($scope.Produto.componentes === undefined){
				$scope.Produto.componentes = [];
			}
			
			var componente = JSON.parse($scope.componenteSelect);
			componente.quantidadeNecessaria = $scope.quantidadeNecessaria;
			$scope.Produto.componentes.push(componente);
		}
		
		function removerComponente(componente){
			$scope.Produto.componentes.splice(componente);
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