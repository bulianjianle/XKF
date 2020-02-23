app.service('indexService',function($http){

    this.loginName=function(){
        return $http.get('http://localhost:9091/indexController/name');
    }

    this.loginbelong=function(){
        return $http.get('http://localhost:9091/indexController/belong');
    }
    
    //修改 
    this.update=function(entity){
        return  $http.post('http://localhost:9091/indexController/update',entity );
    }

    //查询未审核单个实体
    this.findOne=function(){
        return $http.get('http://localhost:9091/indexController/findOne');
    }
    
});