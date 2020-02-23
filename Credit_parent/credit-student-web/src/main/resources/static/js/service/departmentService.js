//部门管理服务层
app.service('departmentService',function($http){
    

    //全部
    this.findall=function(){
        return $http.get('http://localhost:9093/department/findall');
    }


    //分页 
    this.findPage=function(page,rows){
        return $http.get('http://localhost:9093/department/findPage?page='+page+'&rows='+rows);
    }

    //搜索
    this.search=function(page,rows,searchEntity){
        return $http.post('http://localhost:9093/department/search2?page='+page+"&rows="+rows, searchEntity);
    }

    //所有部门名 findallinfo
    this.findallinfo=function(){
        return $http.get('http://localhost:9093/department/findallinfo');
    }

    //报名
    this.entry=function(id){
        return $http.get('http://localhost:9093/department/entry?id='+id);
    }
    
});