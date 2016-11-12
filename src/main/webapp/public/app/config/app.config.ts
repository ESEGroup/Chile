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
			.when('/login', {
				templateUrl: 'public/app/components/login/login.html',
				controller: App.Components.Login.LoginController,
				controllerAs: 'loginCtrl'
			})
			.otherwise({
	            redirectTo: '/'
	        });
	}

})();