app.service('departmentService',function($http){


    //所有部门名 findallinfo
    this.findallinfo=function(){
        return $http.get('http://localhost:9092/DepartmentController/findallinfo');
    }

    //所有版块名 findallinfo
    this.findallbelong=function(){
        return $http.get('http://localhost:9092/DepartmentController/findallbelong');
    }


    //分页搜索
    this.search=function(page,rows,searchEntity){
        return $http.post('http://localhost:9092/DepartmentController/search?page='+page+"&rows="+rows, searchEntity);
    }

    //分页 
    this.findPage=function(page,rows){
        return $http.get('http://localhost:9092/DepartmentController/findPage?page='+page+'&rows='+rows);
    }

    //活动实体
    this.findOnedepartment=function(id){
        return $http.get('http://localhost:9092/DepartmentController/findOnedepartment?id='+id);
    }

    //增加 
    this.add=function(entity){
        return  $http.post('http://localhost:9092/DepartmentController/add',entity );
    }

    //修改 
    this.update=function(entity){
        return  $http.post('http://localhost:9092/DepartmentController/update',entity );
    }

    //删除
    this.dele=function(ids){
        return $http.get('http://localhost:9092/DepartmentController/delete?ids='+ids);
    }

});