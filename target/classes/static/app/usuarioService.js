function UsuarioService($resource) {
    var usuarios = [];
    var useractual = "";
    var logueado = false;
    var admin = false;
    var userinfo;
    var UsuarioResource = $resource('/users/', {
        update: {
            method: "PUT"
        }
    });

    var OneUsuarioResource = $resource('/user/:alias', {
        alias: "@alias"
    }, {
        update: {
            method: "PUT"
        }
    });

    var DeleteUsuarioResource = $resource('/user/delete', {
        update: {
            method: "PUT"
        }
    });

    this.getLogueado = function () {
        return this.logueado;
    }

    this.cerrarSesion = function () {
        this.logueado = false;
        this.useractual = "";
        this.admin = false;

    }
    this.updateUser = function (user) {
        user.$update();
    }
    this.getUsers = function () {
        usuarios = UsuarioResource.query().$promise;
        return usuarios;
    }

    this.getOneUser = function (username) {
        return OneUsuarioResource.get({
            alias: username
        }).$promise;
    }
    this.removeUser = function (user) {
        DeleteUsuarioResource.save(user);

    }
    this.newUsuario = function (newUser) {
        usuarios = UsuarioResource.query();
        new UsuarioResource(newUser).$save(function (user) {
            this.usuarios.push(user);
        });
    }

}

UsuarioService.$inject = ["$resource"];
angular.module("app").service("usuarioService", UsuarioService);