namespace App.Components.Organization {

    export class NewOrganizationController extends BaseController {


        constructor() {
            super();

        }

    }


    angular.module(App.Config.MODULE_NAME).controller('NewOrganizationController', NewOrganizationController);


}