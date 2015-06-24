(function() {

	angular.module('app').controller('ComponenteController',ComponenteController);

	function ComponenteController($scope, $routeParams, $http, $location, AlertService, $alert){
		
		// Propriedades da pagina

		$scope.Titulo = "Cadastrar ";
		$scope.Titulo2 = "Componente";

		$scope.IsDisabled = false;

		$scope.cadastrar = cadastrar;		
		$scope.limparCampos = limparCampos;
		$scope.configurarTela = configurarTela;
		$scope.bloquearCampos = bloquearCampos;
		$scope.Modo = "";

		if($routeParams.modo !== undefined && $routeParams.modo !== null)
			$scope.Modo = $routeParams.modo; // 1 = Cadastrar, 2 = Editar e 3 = Vizualizar

		// Objeto

		$scope.Componente = {};
		$scope.Componente.id = 0;
		$scope.Componente.nomeComponente = "";
		$scope.Componente.tipoComponente = "";
		$scope.Componente.undMedida = "";

		if($routeParams.id !== undefined && $routeParams.id !== null)
			$scope.Componente.id = $routeParams.id;
		
		if($scope.Componente.id > 0){
			
			var request = $http.get('ServletComponente?id='+$scope.Componente.id).success(function(retorno) {
				$scope.Componente = retorno;
			}).error(function(msg) {
				$scope.mensagem = "Houve um problema ao acessar o serviço. Tente mais tarde";
			});

        }

		configurarTela($scope.Modo);

		function cadastrar(){
			
			$http.post('ServletComponente', JSON.stringify($scope.Componente)).
			  success(function(data, status, headers, config) {
				  $scope.alert = $alert(AlertService.montarAlert($scope.Titulo2, $scope.Modo == "1" ? "Cadastrado com sucesso" : "Editado com sucesso", 'success'));
				  $scope.alert.show();
			  }).
			  error(function(data, status, headers, config) {
				  $scope.alert = $alert(AlertService.montarAlert($scope.Titulo2,'Houve um problema ao acessar o serviço. Tente mais tarde', 'danger'));
				  $scope.alert.show();
			  });
			
			if ($scope.Modo == "1") {
				$scope.limparCampos();
				$location.path("/componente/listar");					
			}
		}

		function limparCampos(){
			$scope.Componente = {};
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