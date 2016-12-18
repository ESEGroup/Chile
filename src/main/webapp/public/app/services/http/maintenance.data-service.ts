namespace App.Services.Http {
    "use strict";
    import IResponse = App.Interfaces.IResponse;
    import IUser = App.Interfaces.IUser;
    import IMaintenance = App.Interfaces.IMaintenance;

    export class MaintenanceDataService extends Base.HttpService {

        public static $inject = ['$http', '$q'];

        public constructor($http: ng.IHttpService,
                           $q: ng.IQService) {
            super('maintenance', $http, $q);
        }

        public create(maintenance : IMaintenance) : ng.IPromise<IResponse> {
            return super.post('create', maintenance);
        }

    }

    angular.module(App.Config.MODULE_NAME).service('MaintenanceDataService', MaintenanceDataService);
}