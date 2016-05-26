angular.module('app').controller("PostsController", ["$scope", "$mdDialog", "usuarioService", "textService", "imageService", "videoService", "musicaService", "$resource", "$rootScope","$location", function ($scope, $mdDialog, usuarioService, textService,imageService,videoService,musicaService, $resource, $rootScope,$location) {
    var project = this;
    $scope.alert = "";
    var answer = "";
    $scope.borrarPostTexto = function(post){
        textService.deleteText(post);
        alert('El post ha sido eliminado con exito.');
        $rootScope.$broadcast('posts:updated');
    }
    $scope.borrarPostMusica = function(post){
        musicaService.deleteMusic(post);
        alert('El post ha sido eliminado con exito.');
        $rootScope.$broadcast('posts:updated');
    }
    $scope.borrarPostVideo = function(post){
        videoService.deleteVideo(post);
        alert('El post ha sido eliminado con exito.');
        $rootScope.$broadcast('posts:updated');
    }
    $scope.borrarPostImagen = function(post){
        imageService.deleteImage(post);
        alert('El post ha sido eliminado con exito.');
        $rootScope.$broadcast('posts:updated');
    }
    $scope.showTextDialog = function (ev) {
        $mdDialog.show({
                controller: DialogTextController,
                templateUrl: './template/posttexttemplate.html',
                targetEvent: ev,
            })
            .then(function (answer) {
                    $scope.alert = 'You said the information was "' + answer + '".';
                },
                function () {
                    $scope.alert = 'You cancelled the dialog.';
                });
    };
    $scope.showImageDialog = function (ev) {
        $mdDialog.show({
                controller: DialogImageController,
                templateUrl: './template/postimagetemplate.html',
                targetEvent: ev,
            })
            .then(function (answer) {
                    $scope.alert = 'You said the information was "' + answer + '".';
                },
                function () {
                    $scope.alert = 'You cancelled the dialog.';
                });
    };
    $scope.showVideoDialog = function (ev) {
        $mdDialog.show({
                controller: DialogVideoController,
                templateUrl: './template/postvideotemplate.html',
                targetEvent: ev,
            })
            .then(function (answer) {
                    $scope.alert = 'You said the information was "' + answer + '".';
                },
                function () {
                    $scope.alert = 'You cancelled the dialog.';
                });
    };
    $scope.showMusicDialog = function (ev) {
        $mdDialog.show({
                controller: DialogMusicController,
                templateUrl: './template/postmusictemplate.html',
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

function DialogTextController($scope, $mdDialog, usuarioService, textService, $rootScope) {
    var formT = {
        author: usuarioService.useractual.alias,
        title: "",
        body: ""
    };
    $scope.formT = formT;
    $scope.posttextoSubmit = function () {
        textService.newText(formT);
        $rootScope.$broadcast('posts:updated', formT);
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

function DialogImageController($scope, $mdDialog, usuarioService, imageService, $rootScope) {
    var formI = {
        author: usuarioService.useractual.alias,
        title: "",
        imageUrl: "",
        description: ""
    };
    $scope.formI = formI;
    $scope.postimageSubmit = function () {
        imageService.newImage(formI);
        $rootScope.$broadcast('posts:updated', formI);
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

function DialogVideoController($scope, $mdDialog, usuarioService, videoService, $rootScope) {
    var formV = {
        author: usuarioService.useractual.alias,
        title: "",
        videoUrl: "",
        description: ""
    };
    $scope.formV = formV;
    $scope.postvideoSubmit = function () {
        formV.videoUrl = $scope.formatyoutube(formV.videoUrl);
        videoService.newVideo(formV);
        $rootScope.$broadcast('posts:updated', formV);
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
    $scope.formatyoutube= function(url){
        var array = url.split("=");
        return array[1];
    }
};

function DialogMusicController($scope, $mdDialog, usuarioService, musicaService, $rootScope) {
    var formM = {
        author: usuarioService.useractual.alias,
        title: "",
        musicUrl: "",
        description: ""
    };
    $scope.formM = formM;
    $scope.postmusicSubmit = function () {
        formM.musicUrl = $scope.formatgoear(formM.musicUrl);
        musicaService.newMusic(formM);
        $rootScope.$broadcast('posts:updated', formM);
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
    $scope.formatgoear = function(url){
        var array = url.split("/");
        return array[4];
    }
}