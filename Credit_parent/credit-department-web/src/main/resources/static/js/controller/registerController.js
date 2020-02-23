app.controller('registerController',function($scope,$controller   ,registerService){

	//提交申请表单
	$scope.add=function(entity){
		registerService.add(entity).success(
			function(response){
				if(response.success){
					alert(response.message);
				}else{
					alert(response.message);
				}
			}
		);
	}
});
