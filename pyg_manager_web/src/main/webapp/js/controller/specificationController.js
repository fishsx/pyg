//规格的前端控制器
app.controller("specificationController", function ($scope, $controller, specificationService) {
    //继承基本的控制器 (共享$scope)
    $controller('baseController', {$scope: $scope});

    //重新加载数据
    $scope.reloadList = function () {
        if ($scope.queryVO === undefined) {
            //根据当前页和每页条数查询 (查询所有)
            $scope.findByPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
        } else {
            //根据条件查询
            $scope.findByCondition($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage, $scope.queryVO);
        }
    };

    //分页查询所有
    $scope.findByPage = function (pageNum, pageSize) {
        specificationService.findByPage(pageNum, pageSize).success(function (data) {
            $scope.list = data.data;
            $scope.paginationConf.totalItems = data.total;
        });
    };

    //根据条件查询
    $scope.findByCondition = function (pageNum, pageSize, conditionObj) {

        specificationService.findByCondition(pageNum, pageSize, conditionObj).success(function (data) {
            if (data.data.length < 1) {
                $scope.list = data.data;
            } else {
                $scope.list = data.data;
                $scope.paginationConf.totalItems = data.total;
            }
        })
    };

    //保存 (调用新增/修改方法)
    $scope.save = function () {
        if ($scope.obj.specification.id != null) {
            //执行修改方法
            $scope.modify($scope.obj.specification.id);
        } else {
            // 执行新增方法
            $scope.add();
        }
    };

    //添加功能的json示例
    //{
    //   "specification": {
    //     "id": "",
    //     "specName": "颜色"
    //   },
    //   "specificationOptions": [
    //     {
    //       "specId": "",
    //       "optionName": "绿色",
    //       "orders": "1"
    //     },
    //     {
    //       "specId": "",
    //       "optionName": "蓝色",
    //       "orders": "2"
    //     }
    //   ]
    // }

    //点击增加行的效果
    $scope.addTableRow = function () {
        $scope.obj.specificationOptions.push({}); //注意这里是大括弧，大括弧是对象，代表加新对象
    };

    //删除一行的效果
    $scope.delTableRow = function (index) {
        $scope.obj.specificationOptions.splice(index, 1);
    };

    //新建规格
    $scope.add = function () {
        specificationService.add($scope.obj).success(function (data) {
            if (data.success) {
                $scope.reloadList();
            } else {
                alert(data.message);
            }
        })
    };

    //回显修改页面的数据
    $scope.showData = function (id) {
        $scope.obj = {};
        specificationService.findOneById(id).success(function (data) {
            if (data != null) {
                $scope.obj = data;
            }
        });
    };
    //修改规格
    $scope.modify = function (id) {
        specificationService.update($scope.obj).success(function (data) {
            if (data.success) {
                $scope.reloadList();
            } else {
                alert(data.message);
            }
        });
    };

    //批量删除规格
    $scope.del = function () {
        if (confirm("您确定要删除吗?")) {
            specificationService.batchDelete($scope.checkedIds).success(function (data) {
                if (data.success) {
                    $scope.reloadList();
                    $scope.checkedIds = [];
                } else {
                    alert(data.message);
                }
            });
        }
    }

});