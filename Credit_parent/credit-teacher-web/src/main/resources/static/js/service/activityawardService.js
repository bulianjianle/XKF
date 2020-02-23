app.service('activityawardService',function($http){


    //所有部门名 findallinfo
    this.findallinfo=function(){
        return $http.get('http://localhost:9091/AllAcitivity/findallinfo');
    }

    //所有版块名 findallinfo
    this.findallbelong=function(){
        return $http.get('http://localhost:9091/AllAcitivity/findallbelong');
    }


    //分页搜索
    this.search=function(page,rows,searchEntity){
        return $http.post('http://localhost:9091/ActivityAwardController/search?page='+page+"&rows="+rows, searchEntity);
    }

    //分页 
    this.findPage=function(page,rows){
        return $http.get('http://localhost:9091/ActivityAwardController/findPage?page='+page+'&rows='+rows);
    }

    //活动实体findOneAcitivity
    this.findOneAcitivity=function(id){
        return $http.get('http://localhost:9091/ActivityAwardController/findOneAcitivity?id='+id);
    }

    //增加 
    this.add=function(entity){
        return  $http.post('http://localhost:9091/ActivityAwardController/add',entity );
    }

    //修改 
    this.update=function(entity){
        return  $http.post('http://localhost:9091/ActivityAwardController/update',entity );
    }

    //删除
    this.dele=function(ids){
        return $http.get('http://localhost:9091/ActivityAwardController/delete?ids='+ids);
    }

});