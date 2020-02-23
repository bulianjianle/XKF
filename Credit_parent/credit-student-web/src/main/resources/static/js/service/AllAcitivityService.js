app.service('AllAcitivityService',function($http){


    //所有部门名 findallinfo
    this.findallinfo=function(){
        return $http.get('http://localhost:9093/AllAcitivity/findallinfo');
    }

    //所有版块名 findallinfo
    this.findallbelong=function(){
        return $http.get('http://localhost:9093/AllAcitivity/findallbelong');
    }


    //分页搜索
    this.search=function(page,rows,searchEntity){
        return $http.post('http://localhost:9093/AllAcitivity/search?page='+page+"&rows="+rows, searchEntity);
    }

    //分页 
    this.findPage=function(page,rows){
        return $http.get('http://localhost:9093/AllAcitivity/findPage?page='+page+'&rows='+rows);
    }

    //报名
    this.entry=function(id){
        return $http.get('http://localhost:9093/AllAcitivity/entry?id='+id);
    }

    
});