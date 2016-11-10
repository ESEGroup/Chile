namespace App.Components.Register {
    "use strict";
    import IUser = App.Interfaces.IUser;
    import UserDataService = App.Services.Http.UserDataService;
    import IStorageProvider = angular.storage.IStorageProvider;
    import ILocationService = angular.ILocationService;
    import LogService = App.Services.Util.LogService;
    import IStorageService = angular.storage.IStorageService;

    export class RegisterController extends BaseController {

        public user: IUser;

        public static $inject: string[] = ['$localStorage', '$location', 'LogService', 'UserDataService'];

        constructor(private $localStorage: IStorageService,
                    private $location: ILocationService,
                    private logService: LogService,
                    private UserDataService: UserDataService) {
            super();
        }

        public submit() {
            this.UserDataService.create(this.user).then(
                (data) => {
                    switch (data.status) {
                        case 1:
                            this.logService.success("Usuário criado com sucesso.");
                            this.$localStorage['user'] = this.user;
                            this.$location.path("/");
                            break;
                        case -1:
                            this.logService.error("Erro na criação do usuário.");
                            this.user.password = null;
                            break;
                        case -2:
                            this.logService.warning("Nome de usuário já está sendo utilizado.");
                            this.user.username = null;
                            this.user.password = null;
                            break;
                        case -3:
                            this.logService.warning("Este e-mail já está sendo utilizado.");
                            this.user.email = null;
                            this.user.password = null;
                            break;
                    }
                },
                (error) => {
                    this.logService.error(error);
                    this.user.password = null;
                }
            );
        }
    }

    angular.module(App.Config.MODULE_NAME).controller('RegisterController', RegisterController);
}