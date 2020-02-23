app.controller('allstudentController' ,function($scope,$controller   ,allstudentService) {

    $controller('baseController1', {$scope: $scope});//继承分页控制层

    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码  
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }


    $scope.findAll=function(){
        allstudentService.findAll().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    //分页
    $scope.findPage=function(page,rows){
        allstudentService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    $scope.searchEntity={};//定义搜索对象 

    //搜索 分页按条件
    $scope.search=function(page,rows){
        allstudentService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    //所有专业名称
    $scope.findallmajor=function(){
        allstudentService.findallmajor().success(
            function(response){
                $scope.des=response;
            });
    }

    //所有班级
    $scope.findallstuclass=function(){
        allstudentService.findallstuclass().success(
            function(response){
                $scope.bes=response;
            });
    }

    //查询活动实体findOneAcitivity
    $scope.findOneAcitivity=function(id){
        allstudentService.findOneAcitivity(id).success(
            function(response){
                $scope.entity=response;
            }
        );
    }

    //保存 
    $scope.save=function(){
        var serviceObject;//服务层对象  	
        if($scope.entity.id!=null){//如果有ID
            serviceObject=allstudentService.update( $scope.entity ); //修改  
        }else{
            serviceObject=allstudentService.add( $scope.entity  );//增加 
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

    //批量删除 
    $scope.dele=function(){
        //获取选中的复选框			
        allstudentService.dele( $scope.selectIds ).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//重新加载//刷新列表
                    $scope.selectIds=[];
                    alert(response.message);
                }else{
                    alert(response.message);
                }
            }
        );
    }

});