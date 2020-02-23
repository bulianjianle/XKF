app.controller('indexController',function($scope,$controller   ,indexService){
    //显示当前用户名
    $scope.showLoginName=function(){
        indexService.loginName().success(
            function(response){
                $scope.loginName=response.loginName;
            });
    }


    //显示当前用户板块
    $scope.showLoginbelong=function(){
        indexService.loginbelong().success(
            function(response){
                $scope.loginbelong=response.loginbelong;
            });
    }
    
    //保存 
    $scope.save=function(){
        var serviceObject;//服务层对象  	
        if($scope.entity.id!=null){//如果有ID
            serviceObject=indexService.update( $scope.entity ); //修改  
        }else{
            serviceObject=indexService.add( $scope.entity  );//增加 
        }
        serviceObject.success(
            function(response){
                if(response.success){
                    //重新查询 
                    $scope.reloadList();//重新加载
                    alert(response.message);
                }else{
                    alert(response.message);
                }
            }
        );
    }

    //查询实体
    $scope.findOne=function(){
        indexService.findOne().success(
            function(response){
                $scope.entity=response;
            }
        );
    }
}); 