var VideoService = function ($resource) {
    var videos = [];
    var VideoResource = $resource('/postsvideo');
    var deleteVideoResource = $resource('/postsvideo/delete');

    this.getVideos = function () {
        return VideoResource.query().$promise;

    }
    this.newVideo = function (newVideo) {
        videos = VideoResource.query();
        new VideoResource(newVideo).$save(function (video) {
            videos.push(video);
        });
    }
    this.deleteVideo = function (video) {
        deleteVideoResource.save(video);
        videos.splice(videos.indexOf(video), 1);
    }
}
VideoService.$inject = ["$resource"];
angular.module("app").service("videoService", VideoService);