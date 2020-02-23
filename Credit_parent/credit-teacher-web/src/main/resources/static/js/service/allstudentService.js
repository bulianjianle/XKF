app.service('allstudentService',function($http){


    //所有专业名 
    this.findallmajor=function(){
        return $http.get('http://localhost:9091/AllStudentController/findallmajor');
    }

    //所有班级
    this.findallstuclass=function(){
        return $http.get('http://localhost:9091/AllStudentController/findallclass');
    }


    //分页搜索
    this.search=function(page,rows,searchEntity){
        return $http.post('http://localhost:9091/AllStudentController/search?page='+page+"&rows="+rows, searchEntity);
    }

    //分页 
    this.findPage=function(page,rows){
        return $http.get('http://localhost:9091/AllStudentController/findPage?page='+page+'&rows='+rows);
    }

    //活动实体findOneAcitivity
    this.findOneAcitivity=function(id){
        return $http.get('http://localhost:9091/AllStudentController/findOneAcitivity?id='+id);
    }

    //增加 
    this.add=function(entity){
        return  $http.post('http://localhost:9091/AllStudentController/add',entity );
    }

    //修改 
    this.update=function(entity){
        return  $http.post('http://localhost:9091/AllStudentController/update',entity );
    }

    //删除
    this.dele=function(ids){
        return $http.get('http://localhost:9091/AllStudentController/delete?ids='+ids);
    }

});