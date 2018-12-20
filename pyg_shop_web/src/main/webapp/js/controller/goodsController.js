 //控制层
app.controller("goodsController", function ($scope, $controller, goodsService, itemCatService,typeTemplateService,specificationService,uploadService) {
    //继承基本的控制器 (共享$scope)
    $controller('baseController', {$scope: $scope});

    $scope.status=['未审核','已审核','审核未通过','关闭'];//商品状态
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
                $scope.obj = {"goods":{},"goodsDesc":{"itemImages":[]}};
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

    //监听模板ID,动态生成
    // 1.品牌下拉列表数据
    // 2.拓展属性数据
    // 3.规格数据
    $scope.$watch("obj.goods.typeTemplateId", function (newVal, oldVal) {
        if (newVal != null && newVal !== "") {
            typeTemplateService.findOneById(newVal).success(function (data) {
                //获取品牌数据
                $scope.brandList = JSON.parse(data.brandIds);
                //获取扩展属性数据
                if ($scope.obj.goodsDesc === undefined) {
                    $scope.obj.goodsDesc = {};
                }
                $scope.obj.goodsDesc.customAttributeItems = JSON.parse(data.customAttributeItems);
                //获取指定的类型模板ID的规格及规格选项数据
                specificationService.findSpecOptionsJsonList(newVal).success(function (data) {
                    $scope.specOptionList = data;
                });
            });
        }
    });


    //更新已选择的规格选项
    $scope.updateSpecAttribute = function ($event, specName, specOption) {
        var itemsArr = $scope.obj.goodsDesc.specificationItems;

        if (itemsArr === undefined) {
            itemsArr = [];
        }
        //使用自定义查找指定key的值的索引
        var index = $scope.indexOfList(itemsArr,"attributeName",specName);
        if ($event.target.checked) {
            //勾选新的规格选项,判断实体中有无选项值
            if (index !== -1) {
                //存在此规格,则在此规格中加入一个specOption选项
                itemsArr[index].attributeValue.push(specOption);
            } else {
                //不存在此规格,新增一个
                itemsArr.push({"attributeName": specName,"attributeValue": [specOption]});
            }
        } else {
            //取消勾选
            var optionsArr = itemsArr[index].attributeValue;
            var index2 = optionsArr.indexOf(specOption);
            optionsArr.splice(index2,1);
            //规格没有勾选任何选项的时候, 删除此规格
            if (optionsArr.length <= 0) {
                itemsArr.splice(index,1);
            }
        }
        $scope.obj.goodsDesc.specificationItems = itemsArr;
    };


    //初始化需要提交的实体对象
    $scope.init = function () {
        $scope.obj = {goods:{isEnableSpec:"1"},goodsDesc:{itemImages: []}};
        $scope.uploadFileObj = {};
    };

    //上传文件
    $scope.uploadFile = function () {
        uploadService.uploadFile().success(function (data) {
            if (data.success) {
                $scope.uploadFileObj.url = data.data;
            } else {
                alert(data.message);
            }
        });
    };

    //保存上传的文件[在提交的对象中]
    $scope.saveUploadFile = function () {
        $scope.obj.goodsDesc.itemImages.push($scope.uploadFileObj);
    };

    //删除上传的文件[在提交的对象中]
    $scope.delUploadFile = function (index) {
        $scope.obj.goodsDesc.itemImages.splice(index,1);
    };


    //SKU列表动态显示
    $scope.createItemList = function () {
        $scope.obj.itemList = [{spec: {}, price: 0, num: 99999, status: '1', isDefault: '0'}];// 初始
        var items = $scope.obj.goodsDesc.specificationItems;
        for (var i = 0; i < items.length; i++) {
            $scope.obj.itemList = addColumn($scope.obj.itemList,
                items[i].attributeName, items[i].attributeValue);
        }
    };
    // 添加列值:参数1是创建的itemList集合，参数2是规格名称，参数3是规格值的集合
    var addColumn = function (list, columnName, columnValues) {
        var newList = [];// 新的集合
        for (var i = 0; i < list.length; i++) {
            var oldRow = list[i];  //获取出当前行的内容 {spec:{},price:'0.01',num:'99999',status:'0',isDefault:'0'}
            for (var j = 0; j < columnValues.length; j++) {//循环attributeValue数组的内容
                var newRow = JSON.parse(JSON.stringify(oldRow));// 深克隆,根据attributeValue的数量
                newRow.spec[columnName] = columnValues[j];//{spec:{"网络制式":"移动4G"},price:'0.01',num:'99999',status:'0',isDefault:'0'}
                newList.push(newRow);
            }
        }
        return newList;
    };

    //格式化商品分类
    $scope.itemCateList = [];
    $scope.initItemCateList = function () {
        itemCatService.findAll().success(function (data) {
            for (var i = 0; i < data.length; i++) {
                $scope.itemCateList[data[i].id] = data[i].name;
            }
        });
    };


});
