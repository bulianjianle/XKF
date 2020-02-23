app.service('indexService',function($http){

    this.loginName=function(){
        return $http.get('http://localhost:9092/indexController/name');
    }

    this.logininfo=function(){
        return $http.get('http://localhost:9092/indexController/info');
    }
    
    //修改 
    this.update=function(entity){
        return  $http.post('http://localhost:9092/indexController/update',entity );
    }

    //查询未审核单个实体
    this.findOne=function(){
        return $http.get('http://localhost:9092/indexController/findOne');
    }

    //查询信息
    this.messages=function(){
        return $http.get('http://localhost:9092/indexController/messages');
    }
    
    //设置已读updatestatus
    this.updatestatus=function(){
        return $http.get('http://localhost:9092/indexController/updatestatus');
    }
});