angular.module("app").config(RouteConfig);
RouteConfig.$inject = ['$routeProvider'];

function RouteConfig($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: "template/posttemplate.html"
    });
    $routeProvider.when('/registro', {
        templateUrl: "template/registrotemplate.html"
    });
    $routeProvider.when('/post/:id', {
        templateUrl: "template/post.html"
    });
    $routeProvider.when('/msgprivate', {
        templateUrl: "template/msgprivatetemplate.html"
    });
    $routeProvider.when('/listmembers', {
        templateUrl: "template/listmemberstemplate.html"
    });
    $routeProvider.when('/ajustes', {
        templateUrl: "template/ajustestemplate.html"
    });
    $routeProvider.when('/admin', {
        templateUrl: "template/admintemplate.html"
    });
    $routeProvider.when('/infouser', {
        templateUrl: "template/infotemplate.html"
    });
}