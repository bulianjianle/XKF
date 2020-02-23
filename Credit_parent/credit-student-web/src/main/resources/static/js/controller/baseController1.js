 //控制层 
app.controller('baseController1' ,function($scope){

	//点击隐藏
	$scope.show=true;
	$scope.fun=function () {
		$scope.show=!$scope.show;
		console.log($scope.show);
	}
	$scope.hide=true;
	$scope.func=function () {
		$scope.hide=!$scope.hide;
	}
    
	//分页控件配置 
	$scope.paginationConf = {
         currentPage: 1,
         totalItems: 10,
         itemsPerPage: 10,
         perPageOptions: [10, 20, 30, 40, 50],
         onChange: function(){
        	 $scope.reloadList();//重新加载
     	 }
	}; 
	
	$scope.selectIds=[];//选中的ID集合 

	//更新复选
	$scope.updateSelection = function($event, id) {		
		if($event.target.checked){//如果是被选中,则增加到数组
			$scope.selectIds.push( id);			
		}else{
			var index = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(index, 1);//删除 
		}
	}
	
	
	$scope.jsonToString=function(jsonString,key){
		
		var json= JSON.parse(jsonString);
		var value="";
		
		for(var i=0;i<json.length;i++){
			if(i>0){
				value+=",";
			}			
			value +=json[i][key];			
		}
				
		return value;
	}
	
});	