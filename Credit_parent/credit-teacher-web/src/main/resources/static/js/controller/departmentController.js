app.controller('departmentController' ,function($scope,$controller   ,departmentService) {

    $controller('baseController1', {$scope: $scope});//继承分页控制层

    //读取未审核列表数据绑定到表单中  
    $scope.findstatus=function(){
        departmentService.findstatus().success(
            function(response){
                $scope.list=response;
            }
        );
    }
    
    //查询未审核的实体
    $scope.findOneBystatus=function(id){
        departmentService.findOneBystatus(id).success(
            function(response){
                $scope.entity=response;
            }
        );
    }

    //更改审核状态
    $scope.updateStatus=function(id,status){
        departmentService.updateStatus(id,status).success(
            function(response){
                if(response.success){
                    $scope.findstatus()//重新加载
                }else{
                    alert(response.message);
                }
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
                    $scope.findall();//重新加载
                    alert(response.message);
                }else{
                    alert(response.message);
                }
            }
        );
    }
    
    //查询所有部门
    $scope.findall=function(){
        departmentService.findall().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    

    //批量删除 
    $scope.dele=function(){
        //获取选中的复选框			
        departmentService.dele( $scope.selectIds ).success(
            function(response){
                if(response.success){
                    $scope.findall();//重新加载//刷新列表
                    $scope.selectIds=[];
                    alert(response.message);
                }else{
                    alert(response.message);
                }
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

    //根据部门查询学生
    $scope.findbyinfo=function(info){
        departmentService.findbyinfo(info).success(
            function(response){
                $scope.delist=response;
            });
    }


    //查询所有部门成员
    $scope.findAllDepartment=function(){
        departmentService.findAllDepartment().success(
            function(response){
                $scope.departments=response;
            });
    }
    
    //所有部门名称
    $scope.findallinfo=function(){
        departmentService.findallinfo().success(
            function(response){
                $scope.des=response;
            });
    }
    
});