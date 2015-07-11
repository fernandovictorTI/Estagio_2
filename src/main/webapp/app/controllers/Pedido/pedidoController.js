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
		$scope.adicionarProduto = adicionarProduto; 
		$scope.removerProduto = removerProduto;

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
		
		$scope.quantidade = 0;
		
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
				$scope.Pedido.observacao = "";
				$scope.Pedido.itens = [];
				$scope.Pedido.funcionario = "";
				if($scope.Pedido.numeroPedido == 0){
					$scope.Pedido.numeroPedido = 1;
				}
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
			});
		}
		
		carregarProdutos();
		carregarFuncionarios();
		configurarPedido();

		function cadastrar() {
			
			if($scope.Pedido.funcionario == ""){				
				$scope.alert = $alert(AlertService.montarAlert('Campos incorretos', 'Preencha os campos obrigatórios.', 'danger'));
				return;
			}
			
			if($scope.Pedido.itens.length == 0){
				$scope.alert = $alert(AlertService.montarAlert('Campos incorretos','Selecione pelo menos um Produto.', 'danger'));
				return;
			}
			
			$scope.Pedido.funcionario = JSON.parse($scope.Pedido.funcionario);
			
			$http.post('ServletPedido', $scope.Pedido).
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
		
		function adicionarProduto(){	
			if($scope.Pedido.itens === undefined){
				$scope.Pedido.itens = [];
			}
			
			var item = {};		
			
			var produto = JSON.parse($scope.produtoSelect);
			item.quantidade = $scope.quantidade;
			item.produto = produto;
			$scope.Pedido.itens.push(item);
		}
		
		function removerProduto(componente){
			$scope.Pedido.itens.splice(componente);
		}

	}

})();