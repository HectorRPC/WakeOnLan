var TextService = function ($resource) {
    var texts = [];
    var TextResource = $resource('/poststext');
    var deleteTextResource = $resource('/poststext/delete');

    this.getTexts = function () {
        return TextResource.query().$promise;

    }
    this.newText = function (newText) {
        texts = TextResource.query();
        new TextResource(newText).$save(function (text) {
            texts.push(texts);
        });
    }
    this.deleteText = function (text) {
        deleteTextResource.save(text);
        texts.splice(texts.indexOf(text), 1);

    }
}
TextService.$inject = ["$resource"];
angular.module("app").service("textService", TextService);