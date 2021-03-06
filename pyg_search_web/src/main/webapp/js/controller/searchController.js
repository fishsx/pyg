//控制层
app.controller("searchController", function ($scope, $controller, searchService) {
    //继承基本的控制器 (共享$scope)
    $controller('baseController', {$scope: $scope});

    //初始化搜索载体
    $scope.searchMap = {
        keywords: "",
        brand: "",
        category: "",
        price: "",
        spec: {},
        sort: "",
        sortField: "",
        pageNumber: 1,
        pageSize: 60
    };

    //搜索
    $scope.search = function () {
        searchService.search($scope.searchMap).success(function (data) {
            $scope.resultMap = data;
            buildPageLabel();
        });

    };


    //添加筛选条件
    $scope.addFilterCondition = function (key,value) {
        if (key === "brand" || key === "category" || key === "price") {
            $scope.searchMap[key] = value;
        } else {
            $scope.searchMap.spec[key] = value;
        }
        $scope.search();
    };

    //移除筛选条件
    $scope.removeSearchItem = function (key) {
        if (key === "brand" || key === "category" || key === "price") {
            $scope.searchMap[key] = "";
        } else {
            delete $scope.searchMap.spec[key];
        }
        $scope.search();
    };

    //排序搜索
    $scope.sortSearch = function (sortField, sort) {
        $scope.searchMap.sortField = sortField;
        $scope.searchMap.sort = sort;
        $scope.search();
    };


    //构建分页工具条代码
    buildPageLabel=function(){
        $scope.pageLabel = [];// 新增分页栏属性
        var maxPageNo = $scope.resultMap.totalPages;// 得到最后页码

        // 定义属性,显示省略号
        $scope.firstDot = true;
        $scope.lastDot = true;

        var firstPage = 1;// 开始页码
        var lastPage = maxPageNo;// 截止页码

        if ($scope.resultMap.totalPages > 5) { // 如果总页数大于5页,显示部分页码
            if ($scope.resultMap.pageNumber <= 3) {// 如果当前页小于等于3
                lastPage = 5; // 前5页
                // 前面没有省略号
                $scope.firstDot = false;

            } else if ($scope.searchMap.pageNumber >= lastPage - 2) {// 如果当前页大于等于最大页码-2
                firstPage = maxPageNo - 4; // 后5页
                // 后面没有省略号
                $scope.lastDot = false;
            } else {// 显示当前页为中心的5页
                firstPage = $scope.searchMap.pageNumber - 2;
                lastPage = $scope.searchMap.pageNumber + 2;
            }
        } else {
            // 页码数小于5页  前后都没有省略号
            $scope.firstDot = false;
            $scope.lastDot = false;
        }
        // 循环产生页码标签
        for (var i = firstPage; i <= lastPage; i++) {
            $scope.pageLabel.push(i);
        }
    }


    //分页查询
    $scope.queryForPage=function(pageNumber){
        $scope.searchMap.pageNumber=pageNumber;

        //执行查询操作
        $scope.searchItem();

    }

    //分页页码显示逻辑分析：
    // 1,如果页面数不足5页,展示所有页号
    // 2,如果页码数大于5页
    // 1) 如果展示最前面的5页,后面必须有省略号.....
    // 2) 如果展示是后5页,前面必须有省略号
    // 3) 如果展示是中间5页,前后都有省略号

    // 定义函数,判断是否是第一页
    $scope.isTopPage = function() {
        if ($scope.searchMap.pageNumber == 1) {
            return true;
        } else {
            return false;
        }
    }
    // 定义函数,判断是否最后一页
    $scope.isLastPage = function() {
        if ($scope.searchMap.pageNumber == $scope.resultMap.totalPages) {
            return true;
        } else {
            return false;
        }
    }
});