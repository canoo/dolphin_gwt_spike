define( [
    '$'
], function ($) {

    return function(serverUrl) {
        this.invalidateSession = function() {

			this.serverUrl = serverUrl;

            return $.ajax({
                    type: 'GET',
                    url: this.serverUrl
                })
                .done(function (response) {
                    console.log("client: session invalidated", response);
                })
                .fail(function (error) {
					console.log("client: session could not be invalidated", response);
                    console.log("send error", error);
                });
        };

    };

});
