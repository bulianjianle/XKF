app.controller('ActivityController' ,function($scope,$controller   ,ActivityService) {

    $controller('baseController1', {$scope: $scope});//继承分页控制层

    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码  
        $scope.findPage2( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }

    //读取未审核列表数据绑定到表单中  
    $scope.findstatus=function(){
        ActivityService.findstatus().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    //查询未审核的实体
    $scope.findOneBystatus=function(id){
        ActivityService.findOneBystatus(id).success(
            function(response){
                $scope.entity=response;
            }
        );
    }

    //更改审核状态
    $scope.updateStatus=function(id,status){
        ActivityService.updateStatus(id,status).success(
            function(response){
                if(response.success){
                    $scope.findstatus()//重新加载
                    alert(response.message);
                }else{
                    alert(response.message);
                }
            }
        );
    }
    
    
    
    
    
    
    
    
    
    
});