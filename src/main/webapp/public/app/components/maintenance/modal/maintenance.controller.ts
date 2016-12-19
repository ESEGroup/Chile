namespace App.Components.Maintenance {

    import IMaintenance = App.Interfaces.IMaintenance;
    import UserDataService = App.Services.Http.UserDataService;
    import IUser = App.Interfaces.IUser;
    import LogService = App.Services.Util.LogService;

    export class MaintenanceController extends BaseController {

        public title : string;
        public maintenance : IMaintenance;

        public static $inject: string[] = ['$uibModalInstance', 'LogService', 'data'];

        constructor(private $uibModalInstance: ng.ui.bootstrap.IModalServiceInstance,
                    private logService : LogService,
                    private data : IMaintenance) {
            super();

            this.title = 'Realização da manutenção';

            this.maintenance = this.data ? this.data : <IMaintenance> {};
        }

        public submit() {
            this.$uibModalInstance.close(this.maintenance);
        }

    }

    angular.module(Config.MODULE_NAME).controller('MaintenanceController', MaintenanceController);
}