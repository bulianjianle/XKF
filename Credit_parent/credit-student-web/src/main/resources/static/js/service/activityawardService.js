app.service('activityawardService',function($http){


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
        return $http.post('http://localhost:9093/ActivityAwardController/search?page='+page+"&rows="+rows, searchEntity);
    }

    //分页 
    this.findPage=function(page,rows){
        return $http.get('http://localhost:9093/ActivityAwardController/findPage?page='+page+'&rows='+rows);
    }

    //艺术板块学分
    this.getysxy=function(){
        return $http.get('http://localhost:9093/ActivityAwardController/getysxy');
    }

    //竞技体育学分
    this.getjjty=function(){
        return $http.get('http://localhost:9093/ActivityAwardController/getjjty');
    }
    
    //志愿者服务学分
    this.getzyzfw=function(){
        return $http.get('http://localhost:9093/ActivityAwardController/getzyzfw');
    }
    //社团活动学分
    this.getsthd=function(){
        return $http.get('http://localhost:9093/ActivityAwardController/getsthd');
    }
    //总学分
    this.getall=function(){
        return $http.get('http://localhost:9093/ActivityAwardController/getall');
    }
});