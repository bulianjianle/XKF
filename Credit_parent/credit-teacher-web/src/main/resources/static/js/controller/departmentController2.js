app.controller('departmentController2' ,function($scope,$controller   ,departmentService2) {

    $controller('baseController1', {$scope: $scope});//继承分页控制层

    $scope.searchEntity={};//定义搜索对象
    
    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码  
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }

    //分页
    $scope.findPage=function(page,rows){
        departmentService2.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }
    
    //搜索 分页按条件
    $scope.search=function(page,rows){
        departmentService2.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.departments=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }
    
    //所有部门名称
    $scope.findallinfo=function(){
        departmentService2.findallinfo().success(
            function(response){
                $scope.des=response;
            });
    }

    //查询单个部门成员
    $scope.findOnedepartment=function(id){
        departmentService2.findOnedepartment(id).success(
            function(response){
                $scope.entity=response;
            }
        );
    }

    //保存 
    $scope.save=function(){
        var serviceObject;//服务层对象  	
        if($scope.entity.id!=null){//如果有ID
            serviceObject=departmentService2.update( $scope.entity ); //修改  
        }else{
            serviceObject=departmentService2.add( $scope.entity  );//增加 
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
    $scope.dele2=function(){
        //获取选中的复选框			
        departmentService2.dele2( $scope.selectIds ).success(
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