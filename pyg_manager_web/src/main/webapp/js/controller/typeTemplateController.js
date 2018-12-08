 //控制层
app.controller("typeTemplateController", function ($scope, $controller, typeTemplateService, brandService,specificationService) {
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
        typeTemplateService.findByPage(pageNum, pageSize).success(function (data) {
            $scope.list = data.data;
            $scope.paginationConf.totalItems = data.total;
        });
    };

    //根据条件查询
    $scope.findByCondition = function (pageNum, pageSize, conditionObj) {

        typeTemplateService.findByCondition(pageNum, pageSize, conditionObj).success(function (data) {
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
        if ($scope.obj.id != null) {
            //执行修改方法
            $scope.modify($scope.obj.id);
        } else {
            // 执行新增方法
            $scope.add();
        }
    };

    //点击增加行的效果
    $scope.addTableRow = function () {
        $scope.obj.typeTemplateOptions.push({}); //注意这里是大括弧，大括弧是对象，代表加新对象
    };

    //删除一行的效果
    $scope.delTableRow = function (index) {
        $scope.obj.typeTemplateOptions.splice(index, 1);
    };

    //新建规格
    $scope.add = function () {
        typeTemplateService.add($scope.obj).success(function (data) {
            if (data.success) {
                $scope.reloadList();
            } else {
                alert(data.message);
            }
        })
    };

    //回显修改页面的数据
    $scope.showData = function (id) {
        typeTemplateService.findOneById(id).success(function (data) {
            if (data != null) {
                $scope.obj = {id:id};
                $scope.obj.name = data.name;
                $scope.obj.brandIds = JSON.parse(data.brandIds);
                $scope.obj.specIds = JSON.parse(data.specIds);
                $scope.obj.customAttributeItems = JSON.parse(data.customAttributeItems);
            }
        });
    };
    //修改规格
    $scope.modify = function (id) {
        typeTemplateService.update($scope.obj).success(function (data) {
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
            typeTemplateService.batchDelete($scope.checkedIds).success(function (data) {
                if (data.success) {
                    $scope.reloadList();
                    $scope.checkedIds = [];
                } else {
                    alert(data.message);
                }
            });
        }
    };


    //查找品牌列表的Json列表 (用于Select2的显示)
    $scope.findBrandJsonList = function () {
        brandService.findBrandJsonList().success(function (data) {
            if (data != null) {
                $scope.brandJson = {"data": data};
            } else {
                alert("查询品牌列表失败!")
            }
        })
    };

    //查找规格列表的Json列表 (用于Select2的显示)
    $scope.findSpecJsonList = function () {
        specificationService.findSpecJsonList().success(function (data) {
            if (data != null) {
                $scope.specJson = {"data": data};
            } else {
                alert("查询规格列表失败!")
            }
        })
    };

    // 添加一行
    $scope.addRow = function () {
        $scope.obj.customAttributeItems.push({});
    };

    //删除一行
    $scope.delRow = function (index) {
        $scope.obj.customAttributeItems.splice(index, 1);
    };


});
