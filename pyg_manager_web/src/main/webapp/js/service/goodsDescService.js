//服务层
app.service("goodsDescService", function ($http) {

	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../goodsDesc/findAll.do');
	}
    //分页查询所有
    this.findByPage = function (pageNum,pageSize) {
        return $http.get('../goodsDesc/findByPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize);
    };
    //根据条件分页查询
    this.findByCondition = function (pageNum, pageSize, conditionObj) {
        return $http.post("../goodsDesc/findByCondition.do?pageNum=" + pageNum + "&pageSize=" + pageSize, conditionObj);
    };
    //添加
    this.add = function (obj) {
        return $http.post('../goodsDesc/add.do', obj);
    };
    //根据id查找
    this.findOneById = function (id) {
        return $http.get("../goodsDesc/findOneById.do?id=" + id);
    };
    //修改
    this.update = function (obj) {
        return $http.post("../goodsDesc/update.do", obj);
    };
    //批量删除
    this.batchDelete = function (idsArr) {
        return $http.post("../goodsDesc/batchDelete.do", idsArr);
    };

});
