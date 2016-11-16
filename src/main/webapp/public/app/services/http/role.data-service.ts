namespace App.Services.Http {
    "use strict";
    import IResponse = App.Interfaces.IResponse;
    import IUser = App.Interfaces.IUser;

    export class RoleDataService extends Base.HttpService {

        public static $inject = ['$http', '$q'];

        public constructor($http: ng.IHttpService,
                           $q: ng.IQService) {
            super('role', $http, $q);
        }

        public getAll(): ng.IPromise<IResponse> {
            return super.get("getAll");
        }

    }

    angular.module(App.Config.MODULE_NAME).service('RoleDataService', RoleDataService);
}