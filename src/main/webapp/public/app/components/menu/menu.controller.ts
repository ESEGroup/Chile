namespace App.Components.Menu {

    import IUser = App.Interfaces.IUser;
    export class MenuController extends BaseController {

        private user: IUser;

        private GENERAL_ADMIN: number = 1;
        private DEPARTMENT_ADMIN: number = 2;
        private EMPLOYEE: number = 3;

        public static $inject: string[] = ['$localStorage', '$location'];

        constructor(private $localStorage : angular.storage.IStorageService, private $location : ng.ILocationService) {
            super();
        }

        public isLogged() {
            this.user = this.$localStorage['user'];
            return !(this.user == null || this.user == undefined);
        }

        public signOut() {
            this.$localStorage.$reset();
            this.$location.path('/login');
        }
    }

    angular.module(App.Config.MODULE_NAME).controller('MenuController', MenuController);
}