namespace App.Components.Historic.ScheduledMaintenance {
    "use strict";

    import LogService = App.Services.Util.LogService;
    import dxDataGridRow = DevExpress.ui.dxDataGridRow;
    import EquipmentDataService = App.Services.Http.EquipmentDataService;
    import IEquipment = App.Interfaces.IEquipment;
    import IDepartment = App.Interfaces.IDepartment;
    import IMaintenance = App.Interfaces.IMaintenance;
    import MaintenanceDataService = App.Services.Http.MaintenanceDataService;

    export class ScheduledMaintenanceHistoricController extends BaseController {

        public maintenances : IMaintenance[];

        public fieldList: any;
        public columnList: any;

        public static $inject: string[] = ['$location', '$route', '$uibModal', 'LogService', 'MaintenanceDataService'];

        constructor(private $location : ng.ILocationService,
                    private $route : angular.route.IRouteService,
                    private $uibModal: ng.ui.bootstrap.IModalService,
                    private logService : LogService,
                    private maintenanceDataService : MaintenanceDataService) {
            super();

            this.init();
            this.initColumns();
        }

        private init() {
            this.maintenanceDataService.getAllNotFinished().then(
                (data) => {
                    this.maintenances = <IMaintenance[]> data.data;
                    this.maintenances.forEach((value, index, array) => {
                        value.date = new Date(value.date.toString());
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
                    dataField: 'date',
                    format: 'dd/MM/yyyy',
                    caption: 'Data agendada',
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
                }
            ];
        }

        private editFunc = (maintenance : IMaintenance) =>
        {
            var modalOptions = {
                templateUrl: 'public/app/components/scheduling/modal/schedule-maintenance.modal.html',
                controller: App.Components.Scheduling.Modal.ScheduleMaintenanceController,
                controllerAs: 'scheduleMaintenanceCtrl',
                backdrop: 'static',
                resolve: <{ [name: string]: Function }> {
                    data: () => {
                        return maintenance;
                    }
                }
            };

            this.$uibModal.open(modalOptions).result.then(
                (data : IMaintenance) => {
                    maintenance = data;
                    this.maintenanceDataService.update(maintenance).then(
                        (data) => {
                            this.logService.success(data.message);
                            this.$route.reload();
                        },
                        (error) => {
                            this.logService.error(error.data.message);
                        }
                    );
                });
        }

    }

    angular.module(App.Config.MODULE_NAME).controller('ScheduledMaintenanceHistoricController', ScheduledMaintenanceHistoricController);
}