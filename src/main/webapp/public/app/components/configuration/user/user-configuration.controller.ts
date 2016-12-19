namespace App.Components.Configuration.User {
    "use strict";

    import IUser = App.Interfaces.IUser;
    import UserDataService = App.Services.Http.UserDataService;
    import LogService = App.Services.Util.LogService;
    import RoleDataService = App.Services.Http.RoleDataService;
    import DepartmentDataService = App.Services.Http.DepartmentDataService;
    import IRole = App.Interfaces.IRole;
    import IDepartment = App.Interfaces.IDepartment;
    import IRouteParamsService = angular.route.IRouteParamsService;

    export class UserConfigurationController extends BaseController {

        public title: string;
        public user: IUser;
        public roles: IRole[];
        public departments: IDepartment[];

        public numbersOnlyRegex: RegExp;

        private userId: number;

        public static $inject: string[] = ['$routeParams', '$location', 'LogService', 'UserDataService', 'RoleDataService', 'DepartmentDataService'];

        constructor(private $routeParam : IRouteParamsService,
                    private $location : ng.ILocationService,
                    private logService : LogService,
                    private userDataService : UserDataService,
                    private roleDataService: RoleDataService,
                    private departmentDataService : DepartmentDataService) {
            super();

            this.title = "Criação de novo usuário";
            this.numbersOnlyRegex = /^\d+$/;

            this.userId = this.$routeParam['id'];

            this.init();
        }

        private init() {
            if (this.userId != undefined) {
                this.userDataService.getById(this.userId).then(
                    (data) => {
                        this.user = data.data;
                        this.user.birthDate = this.user.birthDate != null ? new Date(this.user.birthDate.toString()) : null;
                        this.user.creationDate = new Date(this.user.creationDate.toString());
                        this.logService.success(data.message);
                    },
                    (error) => {
                        this.logService.error(error.data.message);
                    }
                )
            }

            this.roleDataService.getAll().then(
                (data) => {
                    this.roles = data.data;
                    this.logService.success(data.message);
                },
                (error) => {
                    this.logService.error(error.data.message);
                }
            );

            this.departmentDataService.getAll().then(
                (data) => {
                    this.departments = data.data;
                    this.logService.success(data.message);
                },
                (error) => {
                    this.logService.error(error.data.message);
                }
            );
        }

        public submit() {
            if (this.userId != undefined) {
                this.userDataService.update(this.user).then(
                    (data) => {
                        this.logService.success(data.message);
                        this.$location.path('configuration/users');
                    },
                    (error) => {
                        this.logService.error(error.data.message);
                    }
                );
            }
            else {
                this.userDataService.create(this.user).then(
                    (data) => {
                        this.logService.success(data.message);
                        this.$location.path('configuration/users');
                    },
                    (error) => {
                        this.logService.error(error.data.message);
                    }
                );
            }
        }

        public back() {
            this.$location.path('/configuration/users');
        }

    }

    angular.module(App.Config.MODULE_NAME).controller('UserConfigurationController', UserConfigurationController);
}