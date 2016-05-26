

var MusicaService = function($resource){
    var musics = [];
    var MusicResource = $resource('/postsmusic');
    var deleteMusicResource = $resource('/postsmusic/delete');

    this.getMusics = function () {
        return MusicResource.query().$promise;
       
    }
    this.newMusic = function (newMusic) {
        musics = MusicResource.query();
        new MusicResource(newMusic).$save(function (music) {
            musics.push(music);
        });
    }
    this.deleteMusic = function (music) {
        deleteMusicResource.save(music)
        musics.splice(musics.indexOf(music), 1);
    }
}
MusicaService.$inject = ["$resource"];
angular.module("app").service("musicaService", MusicaService);
