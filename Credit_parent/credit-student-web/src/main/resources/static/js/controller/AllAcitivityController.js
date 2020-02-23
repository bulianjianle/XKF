app.controller('AllAcitivityController' ,function($scope,$controller   ,AllAcitivityService) {

    $controller('baseController1', {$scope: $scope});//继承分页控制层

    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码  
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }


    $scope.findAll=function(){
        AllAcitivityService.findAll().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    //分页
    $scope.findPage=function(page,rows){
        AllAcitivityService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    $scope.searchEntity={};//定义搜索对象 

    //搜索 分页按条件
    $scope.search=function(page,rows){
        AllAcitivityService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    //所有部门名称
    $scope.findallinfo=function(){
        AllAcitivityService.findallinfo().success(
            function(response){
                $scope.des=response;
            });
    }

    //所有版块名称
    $scope.findallbelong=function(){
        AllAcitivityService.findallbelong().success(
            function(response){
                $scope.bes=response;
            });
    }

    //报名entry 
    $scope.entry=function(id){
        AllAcitivityService.entry(id).success(
            function(response) {
                if (response.success) {
                    alert(response.message);
                } else {
                    alert(response.message);
                }
            });
    }
});