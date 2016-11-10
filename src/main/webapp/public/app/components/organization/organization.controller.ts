namespace App.Components.Organization {

    export class OrganizationController extends BaseController {

        constructor() {
            super();
        }

    }


    angular.module(App.Config.MODULE_NAME).controller('OrganizationController', OrganizationController);

}