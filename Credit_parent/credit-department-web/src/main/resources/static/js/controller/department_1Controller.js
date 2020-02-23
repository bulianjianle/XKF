app.controller('department_1Controller' ,function($scope,$controller   ,department_1Service) {

    $controller('baseController1', {$scope: $scope});//继承分页控制层
    
    //读取未审核列表数据绑定到表单中  
    $scope.findstatus=function(){
        department_1Service.findstatus().success(
            function(response){
                $scope.list=response;
            }
        );
    }
    
    //查询未审核的实体
    $scope.findOneBystatus=function(id){
        department_1Service.findOneBystatus(id).success(
            function(response){
                $scope.entity=response;
            }
        );
    }
    
    //更改审核状态
    $scope.updateStatus=function(id,status){
        department_1Service.updateStatus(id,status).success(
            function(response){
                if(response.success){
                    $scope.findstatus()//重新加载
                }else{
                    alert(response.message);
                }
            }
        );
    }
});