var Controller = function ($mdToast,textService, imageService, videoService,
    musicaService, usuarioService, $mdDialog, $scope, $location,$animate) {
    var vm = this;
    var colores = ["#ED939C", "#9399ED", "#EDEC93", "#93EDA1", "#C993ED", "#A3A3A3", "#EDB07E", "#F49CF7"];
    $scope.llenarColores = function (o) {
        for (var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
        return o;
    };
    $scope.llenarColores(colores);

    var formRNO = {
        invitacion: ""
    };
    var formR = {
        alias: "",
        pass: "",

        mail: "",
        name: "",
        tlf: "",
        degree: "",
        avatarfile: ""
    };
    $scope.elegirColor = function (id) {
        return (colores[id % colores.length]);
    }

    $scope.formR = formR;
    var logged;

    $scope.$on('user:updated', function (event, data) {
        $scope.logged = usuarioService.logueado;
        $scope.admin = usuarioService.admin;
        $scope.currentuser = usuarioService.useractual;
    });
    $scope.$on('posts:updated', function (event, data) {
        vm.showHome();
    });
    $scope.$on('quetecierres:updated', function (event) {
        vm.cerrarSesion();
    });
    $scope.logged = usuarioService.logueado;

    $scope.actualizarposts = function () {
        musicaService
            .getMusics()
            .then(
                function (data) {
                    $scope.musicaposts = data;
                    textService.getTexts().then(function (data) {
                        $scope.textposts = data;
                    });
                    imageService.getImages().then(function (data) {
                        $scope.imageposts = data;
                    });
                    videoService.getVideos().then(function (data) {
                        $scope.videoposts = data;
                    });
                })
    };
    $scope.actualizarposts();
    vm.showHome = function () {
        $scope.actualizarposts();
        $location.path("/");
    };
    $scope.showAdmin = function () {
        $location.path("/admin");
    }
    vm.showMsgPrivate = function () {

        $location.path("/msgprivate");
    };
    vm.showListMembers = function () {

        $location.path("/listmembers");
    };
    $scope.toastPosition = {
        bottom: false,
        top: true,
        left: false,
        right: true
    };
    $scope.getToastPosition = function () {
        return Object.keys($scope.toastPosition)
            .filter(function (pos) {
                return $scope.toastPosition[pos];
            })
            .join(' ');
    };
    vm.registroSubmit = function () {
        usuarioService
            .getOneUser(formR.alias)
            .then(
                function (data) {
                    if (!(data.alias)) {
                        usuarioService
                            .newUsuario(formR);
                        $mdToast.show(
                            $mdToast.simple()
                            .content('Usuario registrado!')
                            .position($scope.getToastPosition())
                            .hideDelay(2000)
                        );
                        $location.path("/");
                    } else {
                        alert("Ya existe un usuario con ese alias, por favor elige otro");
                    }
                },
                function (error) {
                    alert("Error al registrar al usuario");
                });

    };
    vm.showRegister = function () {

        $location.path("/registro");
    };

    vm.cerrarSesion = function () {
        $location.path("/");
        $scope.currentuser = "";
        $scope.logged = false;
        $scope.admin = false;
        usuarioService.cerrarSesion();
    };





}
Controller.$inject = ["$mdToast","textService", "imageService", "videoService",
  "musicaService", "usuarioService", "$mdDialog", "$scope", "$location","$animate"];

angular.module("app").controller("Controller", Controller).controller(
    'AppCtrl',
    function ($scope, $timeout, $mdSidenav, $mdUtil, $log) {
        $scope.toggleLeft = buildToggler('left');
        $scope.toggleRight = buildToggler('right');
        /**
         * Build handler to open/close a SideNav; when animation finishes
         * report completion in console
         */
        function buildToggler(navID) {
            var debounceFn = $mdUtil.debounce(function () {
                $mdSidenav(navID).toggle().then(function () {
                    $log.debug("toggle " + navID + " is done");
                });
            }, 300);
            return debounceFn;
        }
    }).controller('LeftCtrl', function ($scope, $timeout, $mdSidenav, $log) {
    $scope.close = function () {
        $mdSidenav('left').close().then(function () {
            $log.debug("close LEFT is done");
        });
    };
}).controller('RightCtrl', function ($scope, $timeout, $mdSidenav, $log) {
    $scope.close = function () {
        $mdSidenav('right').close().then(function () {
            $log.debug("close RIGHT is done");
        });
    };
});

function DialogController($scope, $mdDialog) {
    $scope.hide = function () {
        $mdDialog.hide();
    };
    $scope.cancel = function () {
        $mdDialog.cancel();
    };
    $scope.answer = function (answer) {
        $mdDialog.hide(answer);
    };
}

function isEmptyObject(obj) {

    if (obj.length && obj.length > 0)
        return false;

    if (obj.length === 0)
        return true;
}