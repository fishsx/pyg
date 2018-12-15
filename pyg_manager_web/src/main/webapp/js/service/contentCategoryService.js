//服务层
app.service("contentCategoryService", function ($http) {

	//读取列表数据绑定到表单中
    this.findAll = function () {
        return $http.get('../contentCategory/findAll.do');
    };
    //分页查询所有
    this.findByPage = function (pageNum,pageSize) {
        return $http.get('../contentCategory/findByPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize);
    };
    //根据条件分页查询
    this.findByCondition = function (pageNum, pageSize, conditionObj) {
        return $http.post("../contentCategory/findByCondition.do?pageNum=" + pageNum + "&pageSize=" + pageSize, conditionObj);
    };
    //添加
    this.add = function (obj) {
        return $http.post('../contentCategory/add.do', obj);
    };
    //根据id查找
    this.findOneById = function (id) {
        return $http.get("../contentCategory/findOneById.do?id=" + id);
    };
    //修改
    this.update = function (obj) {
        return $http.post("../contentCategory/update.do", obj);
    };
    //批量删除
    this.batchDelete = function (idsArr) {
        return $http.post("../contentCategory/batchDelete.do", idsArr);
    };

});
