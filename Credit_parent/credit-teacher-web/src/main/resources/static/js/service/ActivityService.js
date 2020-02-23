//活动服务层
app.service('ActivityService',function($http){

    //未审核审核全部
    this.findstatus=function(){
        return $http.get('http://localhost:9091/Acitivity/findstatus');
    }
    //查询未审核单个实体
    this.findOneBystatus=function(id){
        return $http.get('http://localhost:9091/Acitivity/findOneBystatus?id='+id);
    }

    //更改审核状态
    this.updateStatus=function(id,status){
        return $http.get('http://localhost:9091/Acitivity/updateStatus?id='+id+'&status='+status);
    }
    
    
    
    
    
    
    
    

});
