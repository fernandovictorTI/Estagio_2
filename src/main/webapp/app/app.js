angular.module('app', [ 'ngRoute', 'mgcrea.ngStrap', 'ui.utils.masks' ])
		.config(config);

function config($routeProvider) {
	$routeProvider.when('/funcionario/cadastrar/:modo', {
		templateUrl : 'app/views/Funcionario/form.html',
		controller : 'FuncionarioController',
		controllerAs : 'Funcionario'
	}).when('/funcionario/editar/:id&:modo', {
		templateUrl : 'app/views/Funcionario/form.html',
		controller : 'FuncionarioController',
		controllerAs : 'Funcionario'
	}).when('/funcionario/vizualizar/:id&:modo', {
		templateUrl : 'app/views/Funcionario/form.html',
		controller : 'FuncionarioController',
		controllerAs : 'Funcionario'
	}).when('/Principal', {
		templateUrl : 'app/views/Principal/principal.html',
		controller : 'PrincipalController',
		controllerAs : 'Principal'
	}).when('/produto/cadastrar/:modo', {
		templateUrl : 'app/views/Produto/form.html',
		controller : 'ProdutoController',
		controllerAs : 'Produto'
	}).when('/produto/editar/:id&:modo', {
		templateUrl : 'app/views/Produto/form.html',
		controller : 'ProdutoController',
		controllerAs : 'Produto'
	}).when('/produto/listar', {
		templateUrl : 'app/views/Produto/list.html',
		controller : 'ProdutosController',
		controllerAs : 'Produto'
	}).when('/componente/cadastrar/:modo', {
		templateUrl : 'app/views/Componente/form.html',
		controller : 'ComponenteController',
		controllerAs : 'Componente'
	}).when('/', {
		templateUrl : 'app/views/Principal/principal.html',
		controller : 'PrincipalController',
		controllerAs : 'Principal'
	})
}