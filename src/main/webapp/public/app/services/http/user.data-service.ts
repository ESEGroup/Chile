namespace App.Services.Http {
    "use strict";
    import IUser = App.Interfaces.IUser;

    export class UserDataService extends Base.HttpService {

        public static $inject = ['$http', '$q'];

        public constructor($http: ng.IHttpService,
                           $q: ng.IQService) {
            super('user', $http, $q);
        }

        public get(): ng.IPromise<any> {
            return super.get("get");
        }

        public create(user: IUser): ng.IPromise<any> {
            return super.post("create", user);
        }

    }

    angular.module(App.Config.MODULE_NAME).service('UserDataService', UserDataService);
}