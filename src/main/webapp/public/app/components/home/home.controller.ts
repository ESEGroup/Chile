namespace App.Components.Home {

	import LogService = App.Services.Util.LogService;
    import IUser = App.Interfaces.IUser;

    export class HomeController extends BaseController {

        private user: IUser;

		public static $inject: string[] = ['$localStorage', '$location', 'LogService'];

		constructor(private $localStorage : angular.storage.IStorageService,
                    private $location : ng.ILocationService,
                    private logService: LogService) {
			super();

            this.checkLogin();
		}

		public checkLogin() {
            this.user = this.$localStorage['user'];
            if (this.user == null || this.user == undefined) {
                this.$location.path('/login');
            }
        }
	}

	angular.module(App.Config.MODULE_NAME).controller('HomeController', HomeController);
}