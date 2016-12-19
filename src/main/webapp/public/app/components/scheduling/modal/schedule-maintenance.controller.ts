namespace App.Components.Scheduling.Modal {

    import IMaintenance = App.Interfaces.IMaintenance;
    import UserDataService = App.Services.Http.UserDataService;
    import IUser = App.Interfaces.IUser;
    import LogService = App.Services.Util.LogService;

    export class ScheduleMaintenanceController extends BaseController {

        public title : string;
        public maintenance : IMaintenance;

        public employees : IUser[];

        public static $inject: string[] = ['$uibModalInstance', 'LogService', 'UserDataService', 'data'];

        constructor(private $uibModalInstance: ng.ui.bootstrap.IModalServiceInstance,
                    private logService : LogService,
                    private userDataService : UserDataService,
                    private data : IMaintenance) {
            super();

            this.title = 'Agendamento de manutenção';

            this.maintenance = this.data ? this.data : <IMaintenance> {};

            this.userDataService.getAll().then(
                (data) => {
                    this.employees = <IUser[]> data.data;
                    this.logService.success(data.message);
                },
                (error) => {
                    this.logService.error(error.data.message);
                }
            );
        }

        public submit() {
            this.$uibModalInstance.close(this.maintenance);
        }

    }

    angular.module(Config.MODULE_NAME).controller('ScheduleMaintenanceController', ScheduleMaintenanceController);
}