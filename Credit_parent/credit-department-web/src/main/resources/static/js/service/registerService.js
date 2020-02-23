app.service('registerService',function($http){

    //增加 
    this.add=function(entity){
        return  $http.post('http://localhost:9092/registerController/add',entity );
    }
    
});
