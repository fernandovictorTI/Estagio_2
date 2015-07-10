(function() {

	angular.module('app').controller('FuncionarioController',FuncionarioController);

	function FuncionarioController($scope, $routeParams, $http, $alert, $location, AlertService){
		
		// Propriedades da pagina

		$scope.Titulo = "Cadastrar ";
		$scope.Titulo2 = "Funcionario";

		$scope.IsDisabled = false;

		$scope.cadastrar = cadastrar;
		$scope.adicionarTelefone = adicionarTelefone;
		$scope.removerTelefone = removerTelefone;
		$scope.adicionarEndereco = adicionarEndereco; 
		$scope.limparCampos = limparCampos;
		$scope.configurarTela = configurarTela;
		$scope.bloquearCampos = bloquearCampos;
		$scope.Modo = "";
		$scope.alert = "";

		if($routeParams.modo !== undefined && $routeParams.modo !== null)
			$scope.Modo = $routeParams.modo; // 1 = Cadastrar, 2 = Editar e 3 = Vizualizar

		// Objeto

		$scope.Funcionario = {};
		$scope.Funcionario.id = 0;
		$scope.Funcionario.nome = "";
		$scope.Funcionario.cpf = "";
		$scope.Funcionario.rg = "";
		$scope.Funcionario.dataNascimento = "";
		$scope.Funcionario.sexo = "";
		$scope.Funcionario.estadoCivil = "selecione";
		$scope.Funcionario.email = "";
		$scope.Funcionario.senha = "";
		$scope.Funcionario.telefones = [];
		$scope.Funcionario.enderecos = [];

		if($routeParams.id !== undefined && $routeParams.id !== null)
			$scope.Funcionario.id = $routeParams.id;
		
		if($scope.Funcionario.id > 0){
			
			var request = $http.get('ServletFuncionario?id='+$scope.Funcionario.id).success(function(retorno) {
				$scope.Funcionario = retorno;
			}).error(function(msg) {
				$scope.alert = $alert(AlertService.montarAlert('Titulo', 'Houve um problema ao acessar o serviço. Tente mais tarde', 'danger'));
			});

        }
		
		function montarAlert(titulo ,contentMsg, typeMsg){
			return {
				title : titulo,
				content : contentMsg,
				placement: 'top',
				type : typeMsg, 
				container : "main"
			}; 
		}

		configurarTela($scope.Modo);

		function cadastrar(){
			
			$http.post('ServletFuncionario', JSON.stringify($scope.Funcionario)).
			  success(function(data, status, headers, config) {
				  if(data != undefined){
					  if(data == 'ERRO'){
						  $scope.alert = $alert(AlertService.montarAlert('Campos incorretos', 'Preencha os campos obrigatórios.', 'danger'));
					  }else{
						  $location.path("/funcionario/listar");
					  }
				  }
			  }).
			  error(function(data, status, headers, config) {
				  $scope.alert = $alert(AlertService.montarAlert('Titulo', 'Houve um problema ao acessar o serviço. Tente mais tarde', 'danger'));				  
			  });
			
			if ($scope.Modo != "1") {			
				$scope.limparCampos();
				$scope.configurarTela(1);
			}
		}

		function limparCampos(){
			$scope.Funcionario = {};
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

		function adicionarTelefone(){
			$scope.Funcionario.telefones.push($scope.telefone);
			$scope.telefone = [];			
		}

		function removerTelefone(telefone){
			var telefoneRemover = $scope.Funcionario.telefones.indexOf(telefone);
			$scope.Funcionario.telefones.splice(telefoneRemover);
		}
		
		function adicionarEndereco(){
			$scope.Funcionario.enderecos.push($scope.endereco);
			$scope.endereco = [];
		}

	}

})();