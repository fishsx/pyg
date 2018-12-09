 //控制层
app.controller("itemCatController", function ($scope, $controller, itemCatService, typeTemplateService) {
    //继承基本的控制器 (共享$scope)
    $controller('baseController', {$scope: $scope});

    //查找所有顶级分类
    $scope.findAllTopCate = function () {
        //0代表父id ,默认情况下这些都是顶级的分类
        itemCatService.findByParentId(0).success(function (data) {
            $scope.list = data;
        })
    };

    $scope.parentId = 0;
    //查找下一级类别
    $scope.findSubCate = function (id) {
        $scope.parentId = id;
        itemCatService.findByParentId(id).success(function (data) {
            $scope.list = data;
        });
    };

    //重新加载数据
    $scope.reloadList = function () {
        $scope.findSubCate($scope.parentId);
    };

    //分页查询所有
    $scope.findByPage = function (pageNum, pageSize) {
        itemCatService.findByPage(pageNum, pageSize).success(function (data) {
            $scope.list = data.data;
            $scope.paginationConf.totalItems = data.total;
        });
    };

    //根据条件查询
    $scope.findByCondition = function (pageNum, pageSize, conditionObj) {

        itemCatService.findByCondition(pageNum, pageSize, conditionObj).success(function (data) {
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
        $scope.obj.itemCatOptions.push({}); //注意这里是大括弧，大括弧是对象，代表加新对象
    };

    //删除一行的效果
    $scope.delTableRow = function (index) {
        $scope.obj.itemCatOptions.splice(index, 1);
    };

    //新建
    $scope.add = function () {
        itemCatService.add($scope.obj).success(function (data) {
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
        itemCatService.findOneById(id).success(function (data) {
            if (data != null) {
                $scope.obj = data;
            }
        });
    };
    //修改
    $scope.modify = function (id) {
        itemCatService.update($scope.obj).success(function (data) {
            if (data.success) {
                $scope.reloadList();
            } else {
                alert(data.message);
            }
        });
    };

    //批量删除
    $scope.del = function () {
        if (confirm("您确定要删除吗?")) {
            itemCatService.batchDelete($scope.checkedIds).success(function (data) {
                if (data.success) {
                    $scope.reloadList();
                    $scope.checkedIds = [];
                } else {
                    alert(data.message);
                }
            });
        }
    };

    //面包屑导航功能
    $scope.grade = 1; // 默认显示一级类别
    //设置级别方法
    $scope.setGrade = function (grade) {
        $scope.grade = grade;
    };
    //改变导航栏的方法
    $scope.changeNaviBar = function (entity) {
        if ($scope.grade == 1) {
            $scope.entity_1 = null;
            $scope.entity_2 = null;
        }
        if ($scope.grade == 2) {
            $scope.entity_1 = entity;
            $scope.entity_2 = null;
        }
        if ($scope.grade == 3) {
            $scope.entity_2 = entity;
        }
        $scope.findSubCate(entity.id);
    };

    //加载配置
    $scope.loadOptions = function () {
        $scope.options = {};
        typeTemplateService.findAll().success(function (data) {
            $scope.options.typeTemplate = data;
        });

    }
});
