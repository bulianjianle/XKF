app.controller('departmentController' ,function($scope,$controller   ,departmentService) {

    $controller('baseController1', {$scope: $scope});//继承分页控制层

    
    

    
    //查询所有部门
    $scope.findall=function(){
        departmentService.findall().success(
            function(response){
                $scope.list=response;
            }
        );
    }


    //分页
    $scope.findPage=function(page,rows){
        departmentService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码  
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }

    $scope.searchEntity={};//定义搜索对象 

    //搜索 分页按条件
    $scope.search=function(page,rows){
        departmentService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    
    //所有部门名称
    $scope.findallinfo=function(){
        departmentService.findallinfo().success(
            function(response){
                $scope.des=response;
            });
    }

    //报名entry 
    $scope.entry=function(id){
        departmentService.entry(id).success(
            function(response) {
                if (response.success) {
                    alert(response.message);
                } else {
                    alert(response.message);
                }
            });
    }
    
});