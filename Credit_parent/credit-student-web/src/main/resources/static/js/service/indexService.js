app.service('indexService',function($http){

    this.loginName=function(){
        return $http.get('http://localhost:9093/indexController/name');
    }

    this.showLoginmajor=function(){
        return $http.get('http://localhost:9093/indexController/major');
    }
    
    //修改 
    this.update=function(entity){
        return  $http.post('http://localhost:9093/indexController/update',entity );
    }

    //查询实体
    this.findOne=function(){
        return $http.get('http://localhost:9093/indexController/findOne');
    }

    //查询信息
    this.messages=function(){
        return $http.get('http://localhost:9093/indexController/messages');
    }
    
    //设置已读updatestatus
    this.updatestatus=function(){
        return $http.get('http://localhost:9093/indexController/updatestatus');
    }
    
});