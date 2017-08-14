var HttpClient = {
    post: function(url,param,success,error){
        cordova.exec(success, error, "HttpClientPlugin", "post", [param,{url:url}]);
    }
}


