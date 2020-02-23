app.controller('departmentController' ,function($scope,$controller   ,departmentService) {

    $controller('baseController1', {$scope: $scope});//继承分页控制层

    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码  
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }


    $scope.findAll=function(){
        departmentService.findAll().success(
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

    //所有版块名称
    $scope.findallbelong=function(){
        departmentService.findallbelong().success(
            function(response){
                $scope.bes=response;
            });
    }

    //查询活动实体findOneAcitivity
    $scope.findOnedepartment=function(id){
        departmentService.findOnedepartment(id).success(
            function(response){
                $scope.entity=response;
            }
        );
    }

    //保存 
    $scope.save=function(){
        var serviceObject;//服务层对象  	
        if($scope.entity.id!=null){//如果有ID
            serviceObject=departmentService.update( $scope.entity ); //修改  
        }else{
            serviceObject=departmentService.add( $scope.entity  );//增加 
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
        departmentService.dele( $scope.selectIds ).success(
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