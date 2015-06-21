(function() {

	angular.module('app').controller('ComponenteController',ComponenteController);

	function ComponenteController($scope, $routeParams){
		
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
			$scope.Produto.Id = $routeParams.id;

		configurarTela($scope.Modo);

		function cadastrar(){
			
			$.ajax({
		        url: "ServletComponente",
		        type: 'POST',
		        dataType: 'json',
		        data: JSON.stringify($scope.Componente),
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