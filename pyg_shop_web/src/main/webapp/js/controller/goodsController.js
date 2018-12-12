 //控制层
app.controller("goodsController", function ($scope, $controller, goodsService, itemCatService,typeTemplateService) {
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
        goodsService.findByPage(pageNum, pageSize).success(function (data) {
            $scope.list = data.data;
            $scope.paginationConf.totalItems = data.total;
        });
    };

    //根据条件查询
    $scope.findByCondition = function (pageNum, pageSize, conditionObj) {

        goodsService.findByCondition(pageNum, pageSize, conditionObj).success(function (data) {
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
        if ($scope.obj.goods.id != null) {
            //执行修改方法
            $scope.modify($scope.obj.goods.id);
        } else {
            // 执行新增方法
            $scope.add();
        }
    };

    //点击增加行的效果
    $scope.addTableRow = function () {
        $scope.obj.goodsOptions.push({}); //注意这里是大括弧，大括弧是对象，代表加新对象
    };

    //删除一行的效果
    $scope.delTableRow = function (index) {
        $scope.obj.goodsOptions.splice(index, 1);
    };

    //新建
    $scope.add = function () {
        $scope.obj.goodsDesc.introduction = editor.html();

        goodsService.add($scope.obj).success(function (data) {
            if (data.success) {
                $scope.obj = {};
                editor.html("");
            } else {
                alert(data.message);
            }
        })
    };

    //回显修改页面的数据
    $scope.showData = function (id) {
        $scope.obj = {};
        goodsService.findOneById(id).success(function (data) {
            if (data != null) {
                $scope.obj = data;
            }
        });
    };
    //修改
    $scope.modify = function (id) {
        goodsService.update($scope.obj).success(function (data) {
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
            goodsService.batchDelete($scope.checkedIds).success(function (data) {
                if (data.success) {
                    $scope.reloadList();
                    $scope.checkedIds = [];
                } else {
                    alert(data.message);
                }
            });
        }
    };

    //查找分类列表
    $scope.findCate1List = function () {
        itemCatService.findByParentId(0).success(function (data) {
            $scope.cate1List = data;
        });
    };

    //监听一级下拉选的改变事件 (注意第一个参数为string,里面直接写,不需要写$scope)
    $scope.$watch("obj.goods.category1Id", function (newVal, oldVal) {
        if (newVal !== undefined && newVal !== "") {
            $scope.cate3List = {};
            itemCatService.findByParentId(newVal).success(function (data) {
                $scope.obj.goods.typeTemplateId = "";
                $scope.obj.goods.category2Id = "";
                $scope.obj.goods.category3Id = "";
                $scope.cate2List = data;
            });
        }
    });

    //监听二级下拉选的改变事件
    $scope.$watch("obj.goods.category2Id", function (newVal, oldVal) {
        if (newVal != null && newVal !== "") {
            itemCatService.findByParentId(newVal).success(function (data) {
                $scope.obj.goods.typeTemplateId = "";
                $scope.obj.goods.category3Id = "";
                $scope.cate3List = data;
            });
        }
    });

    //监听三级下拉选, 动态显示模板ID
    $scope.$watch("obj.goods.category3Id", function (newVal, oldVal) {
        if (newVal != null && newVal !== "") {
            itemCatService.findOneById(newVal).success(function (data) {
                $scope.obj.goods.typeTemplateId = data.typeId;
            });
        }
    });

    //监听模板ID,动态生成品牌下拉列表数据和拓展属性
    $scope.$watch("obj.goods.typeTemplateId", function (newVal, oldVal) {
        if (newVal != null && newVal !== "") {
            typeTemplateService.findOneById(newVal).success(function (data) {
                $scope.brandList = JSON.parse(data.brandIds);
                if ($scope.obj.goodsDesc === undefined) {
                    $scope.obj.goodsDesc = {};
                }
                $scope.obj.goodsDesc.customAttributeItems = JSON.parse(data.customAttributeItems);
            });
        }
    });




});
