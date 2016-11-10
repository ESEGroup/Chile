namespace App.Components.Home {

    import HomeDataService = App.Services.Http.HomeDataService;

	export class HomeController extends BaseController {

		public exampleParam: string;

		public static $inject: string[] = ['$uibModal', 'HomeDataService'];

		constructor(private $uibModal: ng.ui.bootstrap.IModalService, private homeDataService: HomeDataService) {
			super();

			this.homeDataService.get().then((data) => {
				this.exampleParam = data.exampleParam;
			});
		}

		public openModal() {
            console.log("openModal");
            var modalOptions = {
                templateUrl: 'public/app/components/home/modals/modal.html',
                controller: App.Components.Home.Modals.ModalController,
                controllerAs: 'modalCtrl',
                backdrop: 'static'
            };

            this.$uibModal.open(modalOptions).result.then(
                (data) => {
                    console.log("Resolved: ", data);
                }, (dismissed) => {
                    console.log("Dismissed: ", dismissed);
                });
        }
	}

	angular.module(App.Config.MODULE_NAME).controller('HomeController', HomeController);
}