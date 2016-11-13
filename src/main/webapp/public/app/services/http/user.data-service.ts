namespace App.Services.Http {
    "use strict";
    import IResponse = App.Interfaces.IResponse;

    export class UserDataService extends Base.HttpService {

        public static $inject = ['$http', '$q'];

        public constructor($http: ng.IHttpService,
                           $q: ng.IQService) {
            super('user', $http, $q);
        }

        public getAll(): ng.IPromise<IResponse> {
            return super.get("getAll");
        }

    }

    angular.module(App.Config.MODULE_NAME).service('UserDataService', UserDataService);
}