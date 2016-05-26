

var ImageService =function($resource) {
    var images = [];
    var ImageResource = $resource('/postsimage');
    var deleteImageResource = $resource('/postsimage/delete');
                                     
    this.getImages = function () {
        return ImageResource.query().$promise;
        
    }
    this.newImage = function (newImage) {
        images = ImageResource.query();
        new ImageResource(newImage).$save(function (image) {
            images.push(image);
        });
    }
    this.deleteImage = function (image) {
        deleteImageResource.save(image);
        images.splice(images.indexOf(image), 1);
        
    }
}
ImageService.$inject = ["$resource"];
angular.module("app").service("imageService", ImageService);
