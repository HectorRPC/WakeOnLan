var MsgService = function($resource){
    var msgs = [];
    var target="";
    var MsgResource = $resource('/privatemsg');
    var MsgSentResource = $resource('/privatemsg/sent/:author', {
        author: "@author"
    },
    {update:{method: "PUT"}});
    
    var MsgRecievedResource = $resource('/privatemsg/recieved/:target', {
        target: "@target"
    },
    {update:{method: "PUT"}});

    this.getMsgsSent = function (author) {
        return MsgSentResource.query({author:author}).$promise;
       
    }
    this.getMsgsRecieved = function (target) {
        return MsgRecievedResource.query({target:target}).$promise;
       
    }
    this.newMsg = function (newMsg) {
        msgs = MsgResource.query();
        new MsgResource(newMsg).$save(function (msg) {
            msgs.push(msg);
        });
    }
    this.deleteMsg = function (msg) {
        msg.$remove(function () {
            msgs.splice(msgs.indexOf(msg), 1);
        });
    }
}
MsgService.$inject = ["$resource"];
angular.module("app").service("msgService", MsgService);
