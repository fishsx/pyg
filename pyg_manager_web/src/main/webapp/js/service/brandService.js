app.service("brandService", function ($http) {
    //分页查询所有
    this.findAllByPage = function (pageNum,pageSize) {
        return $http.get('../brand/findAllByPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize);
    };
    //根据条件分页查询
    this.findByCondition = function (pageNum, pageSize, conditionObj) {
        return $http.post("../brand/findByCondition.do?pageNum=" + pageNum + "&pageSize=" + pageSize, conditionObj);
    };
    //添加
    this.add = function (obj) {
        return $http.post('../brand/add.do', obj);
    };
    //根据id查找
    this.findOneById = function (id) {
        return $http.get("../brand/findOneById.do?id=" + id);
    };
    //修改
    this.update = function (obj) {
        return $http.post("../brand/update.do", obj);
    };
    //批量删除
    this.batchDelete = function (idsArr) {
        return $http.post("../brand/batchDelete.do", idsArr);
    };

});