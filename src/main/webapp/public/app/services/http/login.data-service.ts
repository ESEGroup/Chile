namespace App.Services.Http {
    "use strict";
    import IUser = App.Interfaces.IUser;
    import IResponse = App.Interfaces.IResponse;
    import ILoginDTO = App.Interfaces.ILoginDTO;

    export class LoginDataService extends Base.HttpService {

        public static $inject = ['$http', '$q'];

        public constructor($http: ng.IHttpService,
                           $q: ng.IQService) {
            super('login', $http, $q);
        }

        public login(employeeId: string, password: string): ng.IPromise<IResponse> {
            var loginData: ILoginDTO = {
                employeeId: employeeId,
                password: password
            };
            return super.post("login", loginData);
        }

    }

    angular.module(App.Config.MODULE_NAME).service('LoginDataService', LoginDataService);
}