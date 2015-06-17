(function() {

	angular.module('app').factory('FuncionarioFactory',FuncionarioFactory);

	function FuncionarioFactory($scope){

		$scope.salvar = salvar;
		
		function salvar(){  
	        alert('');
	    }

	    return $resource();

	}

})();