namespace App.Components.Configuration.User {
    "use strict";

    import IUser = App.Interfaces.IUser;
    import UserDataService = App.Services.Http.UserDataService;
    import LogService = App.Services.Util.LogService;

    export class UserConfigurationController extends BaseController {

        public user: IUser;

        public fieldList: any;
        public columnList: any;

        public static $inject: string[] = ['$location', 'LogService', 'UserDataService'];

        constructor(private $location : ng.ILocationService,
                    private logService : LogService,
                    private userDataService : UserDataService) {
            super();

            this.init();

            this.userDataService.getAll().then(
                (data) => {
                    this.fieldList = data.data;
                    this.logService.success(data.message);
                },
                (error) => {
                    this.logService.error(error.data.message);
                }
            )
        }

        private init() {
            this.columnList = [
                {
                    isEditCommand: true,
                    // editFunction: self.edit
                },
                {
                    dataField: 'name',
                    caption: 'Nome',
                },
                {
                    dataField: 'cpf',
                    caption: 'CPF',
                },
                {
                    dataField: 'rg',
                    caption: 'RG',
                },
                {
                    dataField: 'rgIssuer',
                    caption: 'Emissão',
                },
                {
                    dataField: 'employeeId',
                    caption: 'ID Funcionário',
                },
                {
                    dataField: 'birthDate',
                    caption: 'Data de Nascimento',
                },
                {
                    dataField: 'creationDate',
                    caption: 'Data de Criação',
                },
                {
                    dataField: 'role.name',
                    caption: 'Cargo',
                },
                {
                    dataField: 'department.name',
                    caption: 'Departamento',
                },
                {
                    isDeleteCommand: true,
                    // deleteFunction: self.delete
                }
            ];
        }

        public goToNewUser() {
            this.$location.path('/configuration/user/new');
        }

    }

    angular.module(App.Config.MODULE_NAME).controller('UserConfigurationController', UserConfigurationController);
}