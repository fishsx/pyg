/**
 * 基础Controller
 * 有基本的分页和复选框选择功能
 */
app.controller("baseController", function ($scope) {
    //angular分页配置
    $scope.paginationConf = {
        currentPage: 1,  				//当前页
        // totalItems: 10,					//总记录数
        itemsPerPage: 100,				//每页记录数
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

    //指定json数组的某个key转 ,分割的字符串
    $scope.jsonKeyFormat = function (jsonArr, key ) {
        var arr = JSON.parse(jsonArr);
        var val = "";
        for (var i = 0; i < arr.length; i++) {
            if (i > 0) {
                val += "," + arr[i][key];
            } else {
                val += arr[i][key];
            }
        }
        return val;

    };

    //list:当前传入的集合，
    //key:当前要查询的key，key
    //value:这个key的值是否合keyValue相等，相同的时候返回当前list[i]，
    //该方法目的是为了能查找到goodsDesc.specificationItems中符合attributeName中的内容并把当前对象返回
    $scope.indexOfList = function (list, key, value) {
        for (var i = 0; i < list.length; i++) {
            if (list[i][key] === value) {
                return i;
            }
        }
        return -1;
    };

});