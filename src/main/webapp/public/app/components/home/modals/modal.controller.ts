namespace App.Components.Home.Modals {

    export class ModalController extends BaseController {

        private text: string;

        public static $inject: string[] = ['$uibModalInstance'];

        constructor(private $uibModalInstance: ng.ui.bootstrap.IModalServiceInstance) {
            super();

            this.text = "This is a modal!";
        }

        public closeModal() {
            var result = {
                id: 1,
                name: "close",
            };
            this.$uibModalInstance.close(result);
        }
    }

    angular.module(App.Config.MODULE_NAME).controller('ModalController', ModalController);
}