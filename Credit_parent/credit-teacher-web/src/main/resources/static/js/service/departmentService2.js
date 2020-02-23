//部门管理服务层
app.service('departmentService2',function($http){

    //所有部门名 findallinfo
    this.findallinfo=function(){
        return $http.get('http://localhost:9091/department/findallinfo');
    }

    //所有部门成员
    this.findAllDepartment=function(){
        return $http.get('http://localhost:9091/department/findAllDepartment');
    }

    //分页搜索
    this.search=function(page,rows,searchEntity){
        return $http.post('http://localhost:9091/department/search?page='+page+"&rows="+rows, searchEntity);
    }
    //分页 
    this.findPage=function(page,rows){
        return $http.get('http://localhost:9091/department/findPage?page='+page+'&rows='+rows);
    }
    
    //部门实体
    this.findOnedepartment=function(id){
        return $http.get('http://localhost:9091/department/findOnedepartment?id='+id);
    }

    //增加 
    this.add=function(entity){
        return  $http.post('http://localhost:9091/department/add2',entity );
    }

    //修改 
    this.update=function(entity){
        return  $http.post('http://localhost:9091/department/update2',entity );
    }

    //删除
    this.dele2=function(ids){
        return $http.get('http://localhost:9091/department/delete2?ids='+ids);
    }
    
    //根据部门查询findByInfo
    this.findByInfo=function(info){
        return $http.get('http://localhost:9091/department/findByInfo?info='+info);
    }
    
});
