app.service('department_1Service',function($http){

    //未审核审核全部
    this.findstatus=function(){
        return $http.get('http://localhost:9092/department1/findstatus');
    }
    //查询未审核单个实体
    this.findOneBystatus=function(id){
        return $http.get('http://localhost:9092/department1/findOneBystatus?id='+id);
    }
    
    //全部
    this.findall=function(){
        return $http.get('http://localhost:9092/department1/findall');
    }
    
    //更改审核状态
    this.updateStatus=function(id,status){
        return $http.get('http://localhost:9092/department1/updateStatus?id='+id+'&status='+status);
    }
});