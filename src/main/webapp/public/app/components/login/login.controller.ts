namespace App.Components.Login {
    "use strict";
    import IUser = App.Interfaces.IUser;
    import LoginDataService = App.Services.Http.LoginDataService;
    import LogService = App.Services.Util.LogService;

    export class LoginController extends BaseController {

        public employeeId: string;
        public password: string;

        public user: IUser;

        public static $inject: string[] = ['$localStorage', '$location', 'LogService', 'LoginDataService'];

        constructor(private $localStorage : angular.storage.IStorageService,
                    private $location : ng.ILocationService,
                    private logService: LogService,
                    private loginDataService: LoginDataService) {
            super();
        }

        public login() {
            this.loginDataService.login(this.employeeId, this.password).then(
                (data) => {
                    this.user = data.data;
                    this.user.birthDate = new Date(this.user.birthDate.toString());
                    this.user.creationDate = new Date(this.user.creationDate.toString());
                    this.logService.success(data.message);
                    this.$localStorage['user'] = this.user;
                    this.$location.path("/");
                },
                (error) => {
                    this.logService.error(error.data.message);
                    this.password = null;
                }
            )
        }
    }

    angular.module(App.Config.MODULE_NAME).controller('LoginController', LoginController);
}