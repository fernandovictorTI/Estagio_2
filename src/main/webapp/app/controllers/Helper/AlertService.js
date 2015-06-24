(function () {
    var app = angular.module('app');
    
    app.service('AlertService', AlertService);    
    
       function AlertService ($http) {
        
       function montarAlert(titulo ,contentMsg, typeMsg){
			return {
				title : titulo,
				content : contentMsg,
				placement: 'top',
				type : typeMsg, 
				container : "main"
			}; 
		}
        
        return {
            montarAlert: montarAlert
        };
    }

})();