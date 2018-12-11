//服务层
app.service("goodsService", function ($http) {

	//读取列表数据绑定到表单中
    this.findAll = function () {
        return $http.get('../goods/findAll.do');
    };
    //分页查询所有
    this.findByPage = function (pageNum,pageSize) {
        return $http.get('../goods/findByPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize);
    };
    //根据条件分页查询
    this.findByCondition = function (pageNum, pageSize, conditionObj) {
        return $http.post("../goods/findByCondition.do?pageNum=" + pageNum + "&pageSize=" + pageSize, conditionObj);
    };
    this.add = function (obj) {
        return $http.post('../goods/addCombGoods.do', obj);
    };
    //根据id查找
    this.findOneById = function (id) {
        return $http.get("../goods/findOneById.do?id=" + id);
    };
    //修改
    this.update = function (obj) {
        return $http.post("../goods/update.do", obj);
    };
    //批量删除
    this.batchDelete = function (idsArr) {
        return $http.post("../goods/batchDelete.do", idsArr);
    };



});
