namespace App.Components.Menu {

    export class MenuController extends BaseController {

        public static $inject: string[] = ['$localStorage', '$location'];

        constructor(private $localStorage : angular.storage.IStorageService, private $location : ng.ILocationService) {
            super();
        }

        public isLogged() {
            var user: any = this.$localStorage['user'];
            return !(user == null || user == undefined);
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
            this.$location.path('/');
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