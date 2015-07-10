(function() {

	angular.module('app').controller('PedidoController', PedidoController);

	function PedidoController($scope, $routeParams, $http, $alert, $location, AlertService) {
		
		// Propriedades da pagina

		$scope.Titulo = "Cadastrar ";
		$scope.Titulo2 = "Pedido";

		$scope.IsDisabled = false;

		$scope.cadastrar = cadastrar;
		$scope.limparCampos = limparCampos;
		$scope.Modo = "";
		$scope.carregarFuncionarios = carregarFuncionarios;
		$scope.carregarProdutos = carregarProdutos;
		$scope.configurarPedido = configurarPedido;

		if ($routeParams.modo !== undefined && $routeParams.modo !== null)
			$scope.Modo = $routeParams.modo; // 1 = Cadastrar, 2 = Editar e 3
		// = Vizualizar

		// Objeto

		$scope.Pedido = {};
		$scope.Pedido.id = 0;
		$scope.Pedido.numeroPedido = "";
		$scope.Pedido.observacao = "";
		$scope.Pedido.itens = [];
		$scope.Pedido.funcionario = "";
		
		$scope.funcionarios = [];
		$scope.produtos = [];
		
		function carregarFuncionarios(){			
			$http.get('ServletPedido?tipo=funcionario').success(function(retorno) {
				$scope.funcionarios = retorno;
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
			});		
		}
		
		function carregarProdutos(){
			$http.get('ServletPedido?tipo=produto').success(function(retorno) {
				$scope.produtos = retorno;
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
			});
		}
		
		function configurarPedido(){
			$http.get('ServletPedido?tipo=Pedido').success(function(retorno) {
				console.log(retorno)
				$scope.Pedido = retorno;
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
			});
		}
		
		carregarProdutos();
		carregarFuncionarios();
		configurarPedido();

		function cadastrar() {
			
			$http.post('ServletPedido', JSON.stringify($scope.Pedido)).
			  success(function(data, status, headers, config) {
				  $scope.alert = $alert(AlertService.montarAlert($scope.Titulo, 'cadastrado com sucesso', 'success'));				  
			  }).
			  error(function(data, status, headers, config) {
				  $scope.alert = $alert(AlertService.montarAlert('Titulo', 'Houve um problema ao acessar o serviço. Tente mais tarde', 'danger'));				  
			  });
			
			if ($scope.Modo == "1") {
				$scope.limparCampos();
				$location.path("/");					
			}
		}

		function limparCampos() {
			$scope.Pedido = {};
		}

	}

})();