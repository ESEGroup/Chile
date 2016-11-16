namespace App.Services.Http {
    "use strict";
    import IResponse = App.Interfaces.IResponse;
    import IUser = App.Interfaces.IUser;

    export class DepartmentDataService extends Base.HttpService {

        public static $inject = ['$http', '$q'];

        public constructor($http: ng.IHttpService,
                           $q: ng.IQService) {
            super('department', $http, $q);
        }

        public getAll(): ng.IPromise<IResponse> {
            return super.get("getAll");
        }

    }

    angular.module(App.Config.MODULE_NAME).service('DepartmentDataService', DepartmentDataService);
}