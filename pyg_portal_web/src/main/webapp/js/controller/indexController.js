app.controller("indexController", function ($scope,contentService) {


    //根据广告的类目id查询所有已启用的广告
    $scope.findAllEnabledByCateId = function (cid) {
        $scope.adContentList = [];
        contentService.findAllEnabledByCateId(cid).success(function (data) {
            $scope.adContentList[cid] = data;
        });
    };

    //查询所有广告列表
    $scope.findAllContent = function () {
        contentService.findAll().success(function (data) {
            $scope.adContentList = data;
        });
    };
});