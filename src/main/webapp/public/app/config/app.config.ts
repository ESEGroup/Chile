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
			// Scheduling
			.when('/scheduling', {
				templateUrl: 'public/app/components/scheduling/scheduling.html',
				controller: App.Components.Scheduling.SchedulingController,
				controllerAs: 'schedulingCtrl'
			})
			// Historic
			.when('/historic/scheduled-maintenance', {
				templateUrl: 'public/app/components/historic/scheduled-maintenance/scheduled-maintenance-historic.html',
				controller: App.Components.Historic.ScheduledMaintenance.ScheduledMaintenanceHistoricController,
				controllerAs: 'scheduledMaintenanceHistoricCtrl'
			})
			.when('/historic/maintenance', {
				templateUrl: 'public/app/components/historic/maintenance/maintenance-historic.html',
				controller: App.Components.Historic.Maintenance.MaintenanceHistoricController,
				controllerAs: 'maintenanceHistoricCtrl'
			})
			.when('/historic/equipment', {
				templateUrl: 'public/app/components/historic/equipment/equipment-historic.html',
				controller: App.Components.Historic.Equipment.EquipmentHistoricController,
				controllerAs: 'equipmentHistoricCtrl'
			})
			// Maintenance
			.when('/maintenance', {
				templateUrl: 'public/app/components/maintenance/maintenances.html',
				controller: App.Components.Maintenance.MaintenancesController,
				controllerAs: 'maintenanceCtrl'
			})
			// Configuration
			.when('/configuration/users', {
				templateUrl: 'public/app/components/configuration/user/users-configuration.html',
				controller: App.Components.Configuration.User.UsersConfigurationController,
				controllerAs: 'usersConfigCtrl'
			})
			.when('/configuration/user/new', {
				templateUrl: 'public/app/components/configuration/user/user-configuration.html',
				controller: App.Components.Configuration.User.UserConfigurationController,
				controllerAs: 'userConfigCtrl'
			})
			.when('/configuration/user/:id', {
				templateUrl: 'public/app/components/configuration/user/user-configuration.html',
				controller: App.Components.Configuration.User.UserConfigurationController,
				controllerAs: 'userConfigCtrl'
			})
			.otherwise({
	            redirectTo: '/'
	        });
	}

})();