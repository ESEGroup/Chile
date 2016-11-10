namespace App.Components.Login {
    "use strict";
    import IUser = App.Interfaces.IUser;
    import LoginDataService = App.Services.Http.LoginDataService;
    import LogService = App.Services.Util.LogService;

    export class LoginController extends BaseController {

        public username: string;
        public password: string;

        public user: IUser;

        public static $inject: string[] = ['$localStorage', '$location', 'LogService', 'LoginDataService'];

        constructor(private $localStorage : angular.storage.IStorageService,
                    private $location : ng.ILocationService,
                    private logService: LogService,
                    private loginDataService: LoginDataService) {
            super();
        }

        public goToRegistration() {
            this.$location.path('/register');
        }

        public login() {
            this.loginDataService.login(this.username, this.password).then(
                (data) => {
                    switch (data.status) {
                        case 1:
                            this.user = data.user;
                            this.logService.success('Login realizado com sucesso.');
                            this.$localStorage['user'] = this.user;
                            this.$location.path("/");
                            break;
                        case -1:
                            this.logService.error('Erro no login.');
                            this.password = null;
                            break;
                        case -2:
                            this.logService.warning('Senha não confere.');
                            this.password = null;
                            break;
                        case -3:
                            this.logService.warning('Usuário não cadastrado.');
                            this.password = null;
                            break;
                    }

                },
                (error) => {
                    this.logService.error(error);
                    this.password = null;
                }
            )
        }
    }

    angular.module(App.Config.MODULE_NAME).controller('LoginController', LoginController);
}