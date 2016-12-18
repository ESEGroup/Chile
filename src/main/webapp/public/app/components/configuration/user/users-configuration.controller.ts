namespace App.Components.Configuration.User {
    "use strict";

    import IUser = App.Interfaces.IUser;
    import UserDataService = App.Services.Http.UserDataService;
    import LogService = App.Services.Util.LogService;
    import dxDataGridRow = DevExpress.ui.dxDataGridRow;

    export class UsersConfigurationController extends BaseController {

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
                    editFunction: this.editFunc
                },
                {
                    alignment: 'center',
                    dataField: 'id',
                    caption: 'Id',
                    visible: false,
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
                    dataType: 'date',
                    format: 'dd/MM/yyyy'
                },
                {
                    dataField: 'creationDate',
                    caption: 'Data de Criação',
                    dataType: 'date',
                    format: 'dd/MM/yyyy'
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
                    deleteFunction: this.deleteFunc
                }
            ];
        }

        public editFunc = (row) => {
            this.$location.path('/configuration/user/' + row.id);
        };

        public deleteFunc = (row) => {
            this.userDataService.delete(row.id).then(
                (data) => {
                    this.removeObjFromArrayById(this.fieldList, row.id);
                    this.logService.success(data.message);
                },
                (error) => {
                    this.logService.error(error.data.message);
                }
            )
        };

        private removeObjFromArrayById(array: any[], id: number) {
            for (var i = 0; i <= array.length; i++) {
                if (array[i].id == id) {
                    array.splice(i, 1);
                }
            }
        }

        public goToNewUser() {
            this.$location.path('/configuration/user/new');
        }

    }

    angular.module(App.Config.MODULE_NAME).controller('UsersConfigurationController', UsersConfigurationController);
}