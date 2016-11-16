namespace App.Services.Http {
    "use strict";
    import IResponse = App.Interfaces.IResponse;
    import IUser = App.Interfaces.IUser;

    export class UserDataService extends Base.HttpService {

        public static $inject = ['$http', '$q'];

        public constructor($http: ng.IHttpService,
                           $q: ng.IQService) {
            super('user', $http, $q);
        }

        public getById(id: number): ng.IPromise<IResponse> {
            return super.get("get/" + id);
        }

        public getAll(): ng.IPromise<IResponse> {
            return super.get("getAll");
        }

        public create(user : IUser): ng.IPromise<IResponse> {
            return super.post("create", user);
        }

        public update(user : IUser): ng.IPromise<IResponse> {
            return super.post("update", user);
        }

        public delete(id: number): ng.IPromise<IResponse> {
            return super.get("delete/" + id);
        }

    }

    angular.module(App.Config.MODULE_NAME).service('UserDataService', UserDataService);
}