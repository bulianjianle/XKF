app.controller('activityawardController' ,function($scope,$controller   ,activityawardService) {

    $controller('baseController1', {$scope: $scope});//继承分页控制层

    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码  
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }


    $scope.findAll=function(){
        activityawardService.findAll().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    //分页
    $scope.findPage=function(page,rows){
        activityawardService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }

    $scope.searchEntity={};//定义搜索对象 

    //搜索 分页按条件
    $scope.search=function(page,rows){
        activityawardService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }


    //所有版块名称
    $scope.findallbelong=function(){
        activityawardService.findallbelong().success(
            function(response){
                $scope.bes=response;
            });
    }

    //所有艺术板块学分
    $scope.getysxy=function(){
        activityawardService.getysxy().success(
            function(response){
                $scope.ysxy=response;
            });
    }

    //所有竞技体育板块学分
    $scope.getjjty=function(){
        activityawardService.getjjty().success(
            function(response){
                $scope.jjty=response;
            });
    }

    //所有志愿者板块学分
    $scope.getzyzfw=function(){
        activityawardService.getzyzfw().success(
            function(response){
                $scope.zyzfw=response;
            });
    }

    //所有社团活动板块学分
    $scope.getsthd=function(){
        activityawardService.getsthd().success(
            function(response){
                $scope.sthd=response;
            });
    }
    //所有学分
    $scope.getall=function(){
        activityawardService.getall().success(
            function(response){
                $scope.all=response;
            });
    }
    
    
});