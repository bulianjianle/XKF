//部门管理服务层
app.service('departmentService',function($http){
    
    //未审核审核全部
    this.findstatus=function(){
        return $http.get('http://localhost:9091/department/findstatus');
    }
    //查询未审核单个实体
    this.findOneBystatus=function(id){
        return $http.get('http://localhost:9091/department/findOneBystatus?id='+id);
    }

    //全部
    this.findall=function(){
        return $http.get('http://localhost:9091/department/findall');
    }

    //更改审核状态
    this.updateStatus=function(id,status){
        return $http.get('http://localhost:9091/department/updateStatus?id='+id+'&status='+status);
    }

    //分页 
    this.findPage=function(page,rows){
        return $http.get('http://localhost:9091/department/findPage?page='+page+'&rows='+rows);
    }

    //搜索
    this.search=function(page,rows,searchEntity){
        return $http.post('http://localhost:9091/department/search2?page='+page+"&rows="+rows, searchEntity);
    }


    //增加 
    this.add=function(entity){
        return  $http.post('http://localhost:9091/department/add',entity );
    }
    
    //修改 
    this.update=function(entity){
        return  $http.post('http://localhost:9091/department/update',entity );
    }

    //删除
    this.dele=function(ids){
        return $http.get('http://localhost:9091/department/delete?ids='+ids);
    }

    //根据部门分类查询学生分类列表
    this.findByParentId=function(info){
        return $http.get('http://localhost:9091/department/findByinfo?info='+info);
    }

    //所有部门名 findallinfo
    this.findallinfo=function(){
        return $http.get('http://localhost:9091/department/findallinfo');
    }
    
});