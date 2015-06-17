angular.module('app', ['ngRoute','mgcrea.ngStrap']).config(config);

function config($routeProvider){
	$routeProvider.when('/funcionario/cadastrar/:modo',
		{
			templateUrl : 'app/views/Funcionario/form.html',
			controller : 'FuncionarioController',
			controllerAs: 'Funcionario'
		}
	).when('/funcionario/editar/:id&:modo',
		{
			templateUrl : 'app/views/Funcionario/form.html',
			controller : 'FuncionarioController',
			controllerAs: 'Funcionario'
		}
	).when('/funcionario/vizualizar/:id&:modo',
		{
			templateUrl : 'app/views/Funcionario/form.html',
			controller : 'FuncionarioController',
			controllerAs: 'Funcionario'
		}
	).when('/Principal',
		{
			templateUrl : 'app/views/Principal/principal.html',
			controller : 'PrincipalController',
			controllerAs: 'Principal'
		}
	).when('/',
		{
			templateUrl : 'app/views/Principal/principal.html',
			controller : 'PrincipalController',
			controllerAs: 'Principal'
		}
	)
}