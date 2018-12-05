/**
 * 基础Controller
 * 有基本的分页和复选框选择功能
 */
app.controller("baseController", function ($scope) {
    //angular分页配置
    $scope.paginationConf = {
        currentPage: 1,  				//当前页
        // totalItems: 10,					//总记录数
        itemsPerPage: 10,				//每页记录数
        perPageOptions: [10, 20, 30, 40, 50], //分页选项，下拉选择一页多少条记录
        onChange: function () {			//页面变更后触发的方法
            $scope.reloadList();		//启动就会调用分页组件
        }
    };

    //记录复选框选中的id
    $scope.checkedIds = [];
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            //[] -> [x] 选中
            $scope.checkedIds.push(id);
        } else {
            //[x] -> [] 取消选中
            var index = $scope.checkedIds.indexOf(id);
            $scope.checkedIds.splice(index,1);
        }
    };

    //保证在改变页数的时候能够显示已经选中的复选框
    $scope.isChecked = function (id) {
        return $scope.checkedIds.indexOf(id) !== -1;
    };

});