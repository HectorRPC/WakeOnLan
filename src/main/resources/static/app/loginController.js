angular.module('app').controller("LoginController", ["$mdToast", "$scope", "$mdDialog", "usuarioService", "$location", "$resource", "$rootScope","$animate", function ($mdToast, $scope, $mdDialog, usuarioService, $location, $resource, $rootScope, $animate) {
    var project = this;
    $scope.alert = "";
    var answer = "";
    if (usuarioService.useractual) {
        var formR = {
            alias: usuarioService.useractual.alias,
            pass: usuarioService.useractual.pass,
            mail: usuarioService.useractual.mail,
            name: usuarioService.useractual.name,
            tlf: usuarioService.useractual.tlf,
            degree: usuarioService.useractual.degree,
            avatarfile: usuarioService.useractual.avatarfile
        };
        $scope.formR = formR;
    }


    $scope.showAjustes = function () {


        $location.path("/ajustes");
    };
    $scope.updateSubmit = function () {

        usuarioService.useractual.alias = $scope.formR.alias;
        usuarioService.useractual.pass = $scope.formR.pass;
        usuarioService.useractual.mail = $scope.formR.mail;
        usuarioService.useractual.name = $scope.formR.name;
        usuarioService.useractual.tlf = $scope.formR.tlf;
        usuarioService.useractual.degree = $scope.formR.degree;
        usuarioService.useractual.avatarfile = $scope.formR.avatarfile;

        usuarioService.updateUser(usuarioService.useractual);
        usuarioService.useractual = usuarioService.getOneUser($scope.formR.alias);
        $rootScope.$broadcast('quetecierres:updated');
        alert('Los cambios se han realizado con "exito", vuelva a iniciar sesion.');
    };
    $scope.showLoginDialog = function (ev) {
        $location.path("/");
        $mdDialog.show({
                controller: DialogController,
                templateUrl: './template/logindialog.html',
                targetEvent: ev,
            })
            .then(function (answer) {
                    $scope.alert = 'You said the information was "' + answer + '".';
                },
                function () {
                    $scope.alert = 'You cancelled the dialog.';
                });
    };
}]);

function DialogController($mdToast, $scope, $mdDialog, usuarioService, $rootScope,$animate) {
    var form = {
        username: "",
        password: ""
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
    $scope.form = form;

    $scope.checklogin = function () {

        usuarioService.getOneUser(form.username).then(function (data) {
            if (data.pass === form.password) {

                usuarioService.useractual = data;
                usuarioService.logueado = true;
                usuarioService.admin = data.admin;
                $mdToast.show(
                    $mdToast.simple()
                    .content('Sesion Iniciada!')
                    .position($scope.getToastPosition())
                    .hideDelay(2000)
                );
                $rootScope.$broadcast('user:updated', data);
                $rootScope.$broadcast('posts:updated');
                $scope.hide();
            } else {
                alert("Login fallido");
            }
        }, function (error) {
            alert("Login fallido");
        })
    };

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