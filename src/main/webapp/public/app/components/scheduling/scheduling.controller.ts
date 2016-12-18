namespace App.Components.Scheduling {
    "use strict";

    import LogService = App.Services.Util.LogService;
    import dxDataGridRow = DevExpress.ui.dxDataGridRow;
    import EquipmentDataService = App.Services.Http.EquipmentDataService;
    import IEquipment = App.Interfaces.IEquipment;
    import IDepartment = App.Interfaces.IDepartment;
    import IMaintenance = App.Interfaces.IMaintenance;
    import MaintenanceDataService = App.Services.Http.MaintenanceDataService;

    export class SchedulingController extends BaseController {

        public equipments : IEquipment[];

        public fieldList: any;
        public columnList: any;

        public maintenance : IMaintenance;

        public static $inject: string[] = ['$location', '$route', '$uibModal', 'LogService', 'EquipmentDataService', 'MaintenanceDataService'];

        constructor(private $location : ng.ILocationService,
                    private $route : angular.route.IRouteService,
                    private $uibModal: ng.ui.bootstrap.IModalService,
                    private logService : LogService,
                    private equipmentDataService : EquipmentDataService,
                    private maintenanceDataService : MaintenanceDataService) {
            super();

            this.init();
            this.initColumns();
        }

        private init() {
            this.equipmentDataService.getAllEquipmentWithUnscheduledMaintenance().then(
                (data) => {
                    this.equipments = <IEquipment[]> data.data;
                    this.equipments.forEach((value, index, array) => {
                        value.lastMaintenance = value.lastMaintenance != null ? new Date(value.lastMaintenance.toString()) : null;
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
                    width: 70,
                    alignment: 'center',
                    cellTemplate: (container, options) => {
                        $('<i/>')
                            .addClass('fa fa-calendar cursor-pointer')
                            .on('dxclick', () => {
                                this.scheduleMaintenance(options.data)
                            })
                            .appendTo(container)
                    },
                },
                {
                    alignment: 'center',
                    dataField: 'id',
                    caption: 'Id',
                    visible: false,
                },
                {
                    dataField: 'equipmentRegistry',
                    caption: 'Registro do equipamento',
                },
                {
                    dataField: 'description',
                    caption: 'Descrição',
                },
                {
                    dataField: 'location',
                    caption: 'Local',
                },
                {
                    dataField: 'lastMaintenance',
                    caption: 'Última manutenção',
                    dataType: 'date',
                    format: 'dd/MM/yyyy'
                },
                {
                    dataField: 'department.name',
                    caption: 'Departamento',
                }
            ];
        }

        private scheduleMaintenance(equipment : IEquipment) {
            var modalOptions = {
                templateUrl: 'public/app/components/scheduling/modal/schedule-maintenance.modal.html',
                controller: App.Components.Scheduling.Modal.ScheduleMaintenanceController,
                controllerAs: 'scheduleMaintenanceCtrl',
                backdrop: 'static'
            };

            this.$uibModal.open(modalOptions).result.then(
                (data : IMaintenance) => {
                    this.maintenance = data;
                    this.maintenance.equipment = equipment;
                    this.maintenanceDataService.create(this.maintenance).then(
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

    angular.module(App.Config.MODULE_NAME).controller('SchedulingController', SchedulingController);
}