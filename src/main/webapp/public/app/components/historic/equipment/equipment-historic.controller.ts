namespace App.Components.Historic.Equipment {
    "use strict";

    import LogService = App.Services.Util.LogService;
    import dxDataGridRow = DevExpress.ui.dxDataGridRow;
    import EquipmentDataService = App.Services.Http.EquipmentDataService;
    import IEquipment = App.Interfaces.IEquipment;
    import IDepartment = App.Interfaces.IDepartment;
    import IMaintenance = App.Interfaces.IMaintenance;
    import MaintenanceDataService = App.Services.Http.MaintenanceDataService;

    export class EquipmentHistoricController extends BaseController {

        public maintenances : IMaintenance[];

        public fieldList: any;
        public columnList: any;

        public filename

        public static $inject: string[] = ['$location', '$route', '$uibModal', 'LogService', 'MaintenanceDataService'];

        constructor(private $location : ng.ILocationService,
                    private $route : angular.route.IRouteService,
                    private $uibModal: ng.ui.bootstrap.IModalService,
                    private logService : LogService,
                    private maintenanceDataService : MaintenanceDataService) {
            super();

            this.filename = 'RelatorioDeEquipamentos' + '_' + moment().format('DDMMYYYY-HHmmss');

            this.init();
            this.initColumns();
        }

        private init() {
            this.maintenanceDataService.getAll().then(
                (data) => {
                    this.maintenances = <IMaintenance[]> data.data;
                    this.maintenances.forEach((value, index, array) => {
                        value.date = new Date(value.date.toString());
                        value.finishedDate = value.finishedDate != null ? new Date(value.finishedDate.toString()) : null;
                        value.equipment.lastMaintenance = value.equipment.lastMaintenance != null ? new Date(value.equipment.lastMaintenance.toString()) : null;
                    });
                    this.logService.success(data.message);
                },
                (error) => {
                    this.logService.error(error.data.message);
                }
            )
        }

        private initColumns() {
            this.columnList = [
                {
                    alignment: 'center',
                    dataField: 'id',
                    caption: 'Id',
                    visible: false,
                },
                {
                    caption: 'Status da manutenção',
                    allowGrouping: true,
                    cellTemplate: (container, options) => {
                        var maintenance : IMaintenance = options.data;
                        $('<i/>')
                            .text(maintenance.finished ? 'Realizada' : 'Agendada')
                            .appendTo(container)
                    },
                    calculateGroupValue: (rowData) => {
                        return rowData.finished ? 'Realizada' : 'Agendada';
                    }
                },
                {
                    dataField: 'description',
                    caption: 'Descrição da manutenção',
                },
                {
                    dataField: 'date',
                    format: 'dd/MM/yyyy',
                    caption: 'Data agendada',
                },
                {
                    dataField: 'finishedDate',
                    format: 'dd/MM/yyyy',
                    caption: 'Data da manutenção',
                },
                {
                    dataField: 'employee.name',
                    caption: 'Funcionário responsável',
                },
                {
                    dataField: 'equipment.equipmentRegistry',
                    caption: 'Resgistro do equipamento',
                },
                {
                    dataField: 'equipment.description',
                    caption: 'Descrição do equipamento',
                },
                {
                    dataField: 'equipment.location',
                    caption: 'Local do equipamento',
                },
                {
                    dataField: 'equipment.lastMaintenance',
                    format: 'dd/MM/yyyy',
                    caption: 'Data da ultima manutenção',
                },
                {
                    dataField: 'equipment.maintenancePeriodicity',
                    caption: 'Período recomendado entre manutenções (em dias)',
                    dataType: 'number',
                },
                {
                    dataField: 'equipment.department.name',
                    caption: 'Departamento',
                }
            ];
        }

    }

    angular.module(App.Config.MODULE_NAME).controller('EquipmentHistoricController', EquipmentHistoricController);
}