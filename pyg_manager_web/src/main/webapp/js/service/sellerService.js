//服务层
app.service("sellerService", function ($http) {

	//读取列表数据绑定到表单中
    this.findAll = function () {
        return $http.get('../seller/findAll.do');
    };

    //查找所有未审核的商家
    this.findAllUnAudit = function (pageNum, pageSize) {
        //查找状态为未审核的商家
        return $http.get("../seller/findByStatus.do?pageNum=" + pageNum + '&pageSize=' + pageSize +"&status=0");
    };

    //分页查询所有
    this.findByPage = function (pageNum,pageSize) {
        return $http.get('../seller/findByPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize);
    };
    //根据条件分页查询
    this.findByCondition = function (pageNum, pageSize, conditionObj) {
        return $http.post("../seller/findByCondition.do?pageNum=" + pageNum + "&pageSize=" + pageSize, conditionObj);
    };
    //添加
    this.add = function (obj) {
        return $http.post('../seller/add.do', obj);
    };
    //根据id查找
    this.findOneById = function (id) {
        return $http.post("../seller/findOneById.do?id=" + id);
    };
    //修改
    this.update = function (obj) {
        return $http.post("../seller/update.do", obj);
    };
    //批量删除
    this.batchDelete = function (idsArr) {
        return $http.post("../seller/batchDelete.do", idsArr);
    };

});
