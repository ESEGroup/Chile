namespace App.Services.Http {
    "use strict";
    import IResponse = App.Interfaces.IResponse;
    import IUser = App.Interfaces.IUser;

    export class EquipmentDataService extends Base.HttpService {

        public static $inject = ['$http', '$q'];

        public constructor($http: ng.IHttpService,
                           $q: ng.IQService) {
            super('equipment', $http, $q);
        }

        public getAllEquipmentWithUnscheduledMaintenance() : ng.IPromise<IResponse> {
            return super.get('getAllEquipmentWithUnscheduledMaintenance');
        }

    }

    angular.module(App.Config.MODULE_NAME).service('EquipmentDataService', EquipmentDataService);
}