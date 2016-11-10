(() => {
	"use strict";

	routeConfig.$inject = ['$routeProvider'];

	angular.module(App.Config.MODULE_NAME).config(routeConfig);

	function routeConfig($routeProvider: ng.route.IRouteProvider) {
		$routeProvider
			.when('/', {
				templateUrl: 'public/app/components/home/home.html',
            	controller: App.Components.Home.HomeController,
            	controllerAs: 'homeCtrl'
			})
			.when('/register', {
				templateUrl: 'public/app/components/register/register.html',
				controller: App.Components.Register.RegisterController,
				controllerAs: 'regCtrl'
			})
			.when('/login', {
				templateUrl: 'public/app/components/login/login.html',
				controller: App.Components.Login.LoginController,
				controllerAs: 'loginCtrl'
			})
			.when('/organization', {
				templateUrl: 'public/app/components/organization/organization.html',
				controller: App.Components.Organization.OrganizationController,
				controllerAs: 'orgCtrl'
			})
			.when('/organization/new', {
				templateUrl: 'public/app/components/organization/new-organization.html',
				controller: App.Components.Organization.NewOrganizationController,
				controllerAs: 'orgCtrl'
			})
			.otherwise({
	            redirectTo: '/'
	        });
	}

})();