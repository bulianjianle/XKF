app.service('activitystuService',function($http){


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
        return $http.post('http://localhost:9091/ActivitystuController/search?page='+page+"&rows="+rows, searchEntity);
    }

    //分页 
    this.findPage=function(page,rows){
        return $http.get('http://localhost:9091/ActivitystuController/findPage?page='+page+'&rows='+rows);
    }

    //活动实体findOneAcitivity
    this.findOneAcitivity=function(id){
        return $http.get('http://localhost:9091/ActivitystuController/findOneAcitivity?id='+id);
    }

    //增加 
    this.add=function(entity){
        return  $http.post('http://localhost:9091/ActivitystuController/add',entity );
    }

    //修改 
    this.update=function(entity){
        return  $http.post('http://localhost:9091/ActivitystuController/update',entity );
    }

    //删除
    this.dele=function(ids){
        return $http.get('http://localhost:9091/ActivitystuController/delete?ids='+ids);
    }

});