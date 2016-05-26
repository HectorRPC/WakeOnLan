angular.module('app').controller("SocialController", ["$scope", "$mdDialog", "usuarioService", "msgService", "$resource", "$rootScope", "$location", function ($scope, $mdDialog, usuarioService, msgService, $resource, $rootScope, $location) {

    $scope.aliasactual = usuarioService.useractual.alias;
    if (usuarioService.useractual !== undefined) {
        var alias = usuarioService.useractual.alias;
        var target = "";
        var msgsSents;
        var msgsRecieved;
        msgService.getMsgsSent(alias).then(function (data) {
            $scope.msgsSents = data;
        });
        msgService.getMsgsRecieved(alias).then(function (data) {
            $scope.msgsRecieved = data;
        });
        usuarioService.getUsers().then(function (data) {
            $scope.listaUsers = data;
        });
    }

    $scope.actualizarUsuario = function (user) {
        usuarioService.getOneUser(user.alias).then(function (data) {
            data.pay = user.pay;
            data.admin = user.admin;
            usuarioService.updateUser(data);
            alert('El usuario ha sido actualizado con exito.');
        });

    }
    $scope.eliminarUsuario = function (user) {
        usuarioService.removeUser(user);
        alert('El usuario ha sido eliminado con exito.');

    }
    
    $scope.showInfoUser = function (ev, member) {
        usuarioService.userinfo = member;
        $mdDialog.show({
                controller: DialogInfoController,
                templateUrl: './template/infotemplate.html',
                targetEvent: ev,
            })
            .then(function (answer) {
                    $scope.alert = 'You said the information was "' + answer + '".';
                },
                function () {
                    $scope.alert = 'You cancelled the dialog.';
                });
    };
    $scope.showWriteMsgDialog = function (ev, alias) {
        msgService.target = alias;
        $mdDialog.show({
                controller: DialogWriteMsgController,
                templateUrl: './template/writemsgtemplate.html',
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

function DialogInfoController($scope, $mdDialog, usuarioService, msgService, $rootScope) {
    $scope.member = usuarioService.userinfo;
    
    $scope.hide = function () {
        $mdDialog.hide();
    };
    $scope.cancel = function () {
        $mdDialog.cancel();
    };
    $scope.answer = function (answer) {
        $mdDialog.hide(answer);
    };
};

function DialogWriteMsgController($scope, $mdDialog, usuarioService, msgService, $rootScope) {
    var formI = {
        author: usuarioService.useractual.alias,
        title: "",
        body: "",
        target: msgService.target
    };
    $scope.formI = formI;
    $scope.sendmsgSubmit = function () {
        msgService.newMsg(formI);
        $rootScope.$broadcast('msg:updated', formI);
        $scope.hide();
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
};