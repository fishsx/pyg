app.controller("loginController", function ($scope,$controller,loginService) {

    //继承基本的控制器 (共享$scope)
    $controller('baseController', {$scope: $scope});
    //获取登录名
    $scope.getLoginName = function () {
        loginService.getLoginName().success(function (data) {
            $scope.loginName = data.loginName;
        });
    }
});