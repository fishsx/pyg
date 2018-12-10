app.controller("loginController", function ($scope,$controller,loginService) {
    // $controller('baseController', {scope: $scope});

    //获取当前用户的登录名
    $scope.getLoginName = function () {
        loginService.getLoginName().success(function (data) {
            $scope.loginName = data.loginName;
        });
    };

});