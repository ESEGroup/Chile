namespace App.Components.Maintenance {
    "use strict";

    import LogService = App.Services.Util.LogService;
    import dxDataGridRow = DevExpress.ui.dxDataGridRow;
    import EquipmentDataService = App.Services.Http.EquipmentDataService;
    import IEquipment = App.Interfaces.IEquipment;
    import IDepartment = App.Interfaces.IDepartment;
    import IMaintenance = App.Interfaces.IMaintenance;
    import MaintenanceDataService = App.Services.Http.MaintenanceDataService;
    import IUser = App.Interfaces.IUser;

    export class MaintenancesController extends BaseController {

        public user : IUser;
        public maintenances : IMaintenance[];

        public fieldList: any;
        public columnList: any;

        public static $inject: string[] = ['$localStorage', '$location', '$route', '$uibModal', 'LogService', 'MaintenanceDataService'];

        constructor(private $localStorage : angular.storage.IStorageService,
                    private $location : ng.ILocationService,
                    private $route : angular.route.IRouteService,
                    private $uibModal: ng.ui.bootstrap.IModalService,
                    private logService : LogService,
                    private maintenanceDataService : MaintenanceDataService) {
            super();

            this.user = this.$localStorage['user'];

            this.init();
            this.initColumns();
        }

        private init() {
            this.maintenanceDataService.getAllNotFinishedByUserId(this.user.id).then(
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
                    width: 70,
                    alignment: 'center',
                    cellTemplate: (container, options) => {
                        $('<i/>')
                            .addClass('fa fa-wrench cursor-pointer')
                            .on('dxclick', () => {
                                this.makeMaintenance(options.data)
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
                    dataField: 'date',
                    format: 'dd/MM/yyyy',
                    caption: 'Data agendada',
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

        private makeMaintenance(maintenance : IMaintenance)
        {
            var modalOptions = {
                templateUrl: 'public/app/components/maintenance/modal/maintenance.modal.html',
                controller: App.Components.Scheduling.Modal.ScheduleMaintenanceController,
                controllerAs: 'maintenanceCtrl',
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
                    maintenance.finished = true;
                    maintenance.finishedDate = new Date();
                    maintenance.equipment.lastMaintenance = maintenance.finishedDate;
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

    angular.module(App.Config.MODULE_NAME).controller('MaintenancesController', MaintenancesController);
}