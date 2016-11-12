namespace App.Components.Menu {

    import IUser = App.Interfaces.IUser;
    export class MenuController extends BaseController {

        private user: IUser;

        public static $inject: string[] = ['$localStorage', '$location'];

        constructor(private $localStorage : angular.storage.IStorageService, private $location : ng.ILocationService) {
            super();
        }

        public isLogged() {
            this.user = this.$localStorage['user'];
            return !(this.user == null || this.user == undefined);
        }

        public userRegister() {
            this.$location.path('/register');
        }

        public userLogin() {
            this.$location.path('/login')
        }
        // public login() {
        //     this.$location.path('/login');
        // }

        public signOut() {
            this.$localStorage.$reset();
            this.$location.path('/login');
        }

        public goToHome() {
            this.$location.path('/');
        }

        public createOrganization() {
            this.$location.path('/organization/new');
        }
    }

    angular.module(App.Config.MODULE_NAME).controller('MenuController', MenuController);
}